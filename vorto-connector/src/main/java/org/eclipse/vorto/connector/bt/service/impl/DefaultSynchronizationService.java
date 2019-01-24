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
package org.eclipse.vorto.connector.bt.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.vorto.connector.bt.core.Sink;
import org.eclipse.vorto.connector.bt.core.Source;
import org.eclipse.vorto.connector.bt.model.DeviceTelemetryData;
import org.eclipse.vorto.connector.bt.model.EndpointBinding;
import org.eclipse.vorto.connector.bt.model.RegisteredDeviceId;
import org.eclipse.vorto.connector.bt.model.SourceContext;
import org.eclipse.vorto.connector.bt.model.SynchronizationResult;
import org.eclipse.vorto.connector.bt.service.MappingStore;
import org.eclipse.vorto.connector.bt.service.SynchronizationService;
import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.model.FunctionblockModel;
import org.eclipse.vorto.model.ModelId;
import org.eclipse.vorto.model.ModelProperty;
import org.eclipse.vorto.model.Stereotype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class DefaultSynchronizationService implements SynchronizationService {

	@Autowired
	private Sink sink;

	@Autowired
	private Source source;
	
	@Autowired
	private MappingStore mappingStore;

	private static Logger logger = LoggerFactory.getLogger(DefaultSynchronizationService.class);

	@Override
	public Collection<SynchronizationResult> synchronize(SourceContext context) {
		logger.info("Starting synchronization for " + context);
		
		Collection<SynchronizationResult> syncResults = new ArrayList<>();
		
		for(String deviceId : source.getDevices(context)) {
			Optional<RegisteredDeviceId> optionalRegisteredDevice = sink.getRegisteredDeviceId(context);
			if (optionalRegisteredDevice.isPresent()) {
				RegisteredDeviceId registeredDevice = optionalRegisteredDevice.get();
				List<EndpointBinding> requestEndpoints = resolveEndpointBindingForModelId(registeredDevice.getModelId());
				for(EndpointBinding binding : requestEndpoints) {
					Optional<DeviceTelemetryData> devicePayloadResponse = source.readTelemetryData(context, binding, registeredDevice.getDeviceId());
					if (devicePayloadResponse.isPresent()) {
						sink.sendTelemetryData(registeredDevice, devicePayloadResponse.get());
						logger.info("Sending data to sink succeeded - [" + registeredDevice.getDeviceId() + ", " + binding + "]");
						syncResults.add(SynchronizationResult.succeeded(registeredDevice.getDeviceId(), binding.getEndpointUrl()));
					} else {
						logger.info("No reply when reading telemetry data of source [" + registeredDevice.getDeviceId() + ", " + binding + "]");
						syncResults.add(SynchronizationResult.failed(registeredDevice.getDeviceId(), binding.getEndpointUrl()));
					}
				}
			} else {
				syncResults.add(SynchronizationResult.notRegistered(deviceId));
			}
		}
		
		return syncResults;
	}
	
	public List<EndpointBinding> resolveEndpointBindingForModelId(final ModelId modelId) {
		List<EndpointBinding> bindings = new ArrayList<EndpointBinding>();
		IMappingSpecification spec = mappingStore.getMappingSpecification(modelId);
		for (ModelProperty property : spec.getInfoModel().getFunctionblocks()) {
			FunctionblockModel fbm = spec.getFunctionBlock(property.getName());
			if (fbm.getStereotype("binding").isPresent()) {
				Stereotype binding = fbm.getStereotype("binding").get();
				final String endpointUrl = binding.getAttributes().get("endpoint");							
				final String method = binding.getAttributes().getOrDefault("method", "get");
				final String mediaType = binding.getAttributes().getOrDefault("mediaType", MediaType.APPLICATION_JSON_VALUE.toString());
				bindings.add(EndpointBinding.create(method, endpointUrl, mediaType));
			}
		}
		
		return bindings;
	}
	
}
