/**
 * Copyright (c) 2019 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.connector.bt.core.sink.boschiotsuite;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.eclipse.vorto.connector.bt.core.FatalIntegrationException;
import org.eclipse.vorto.connector.bt.core.Sink;
import org.eclipse.vorto.connector.bt.core.sink.boschiotsuite.hono.HonoClient;
import org.eclipse.vorto.connector.bt.core.sink.boschiotsuite.hono.HubRegistration;
import org.eclipse.vorto.connector.bt.model.DeviceTelemetryData;
import org.eclipse.vorto.connector.bt.model.RegisteredDeviceId;
import org.eclipse.vorto.connector.bt.model.SourceContext;
import org.eclipse.vorto.connector.bt.service.MappingStore;
import org.eclipse.vorto.mapping.engine.MappingEngine;
import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.GsonBuilder;

@Component
public class BoschIoTSuitePlatform implements Sink {

	@Autowired
	private MappingStore mappingStore;

	@Autowired
	private HonoDataService dataService;

	@Autowired
	private HonoClient honoClient;

	@Value(value = "${hono.tenant}")
	private String hubTenant;

	private static Logger logger = LoggerFactory.getLogger(BoschIoTSuitePlatform.class);

	@Override
	public void sendTelemetryData(final RegisteredDeviceId deviceId, final DeviceTelemetryData payload) {

		IMappingSpecification spec = mappingStore.getMappingSpecification(deviceId.getModelId());
		MappingEngine engine = mappingStore.createEngine(spec);

		InfomodelValue mappedOutput = engine
				.mapSource(new GsonBuilder().create().fromJson(payload.getJsonPayload(), HashMap.class));

		logger.debug("Device payload was mapped to Vorto successfully");
		this.updateFeaturesForMappedData(deviceId, mappedOutput);
	}

	protected void updateFeaturesForMappedData(final RegisteredDeviceId deviceId, final InfomodelValue payload) {
		logger.debug("Sending payload to Hono via MQTT for " + deviceId);
		dataService.publishToHono(deviceId.getDeviceId(), payload);
	}
	
	@Override
	public Optional<RegisteredDeviceId> getRegisteredDeviceId(SourceContext context) {
		try {
			final String deviceId = context.getDeviceId();
			logger.debug("Reading device from Bosch IoT Hub Device Registry using tenant '" + hubTenant + "' with ID '"
					+ deviceId + "'");
			HubRegistration result = honoClient.getHubRegistration(hubTenant, deviceId).get();
			if (isEnabledAndValid(result)) {
				return Optional
						.of(RegisteredDeviceId.create(result.getDeviceId(), result.getThingId(), result.getModelId()));
			} else {
				logger.debug("Not valid response from Hub" + result);
			}
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Problem occured when calling hub", e);
			throw new FatalIntegrationException(e);
		}
		return Optional.empty();
	}

	private boolean isEnabledAndValid(final HubRegistration registrationResult) {
		return registrationResult != null && registrationResult.getThingId() != null
				&& registrationResult.getModelId() != null;
	}
}
