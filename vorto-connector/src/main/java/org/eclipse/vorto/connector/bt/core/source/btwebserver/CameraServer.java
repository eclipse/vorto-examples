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
package org.eclipse.vorto.connector.bt.core.source.btwebserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.eclipse.vorto.connector.bt.core.FatalIntegrationException;
import org.eclipse.vorto.connector.bt.core.Source;
import org.eclipse.vorto.connector.bt.model.DeviceTelemetryData;
import org.eclipse.vorto.connector.bt.model.EndpointBinding;
import org.eclipse.vorto.connector.bt.model.SourceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CameraServer implements Source {
	
	@Autowired
	private CameraServerRestClient restClient;
	
	private static final String PLATFORM_KEY = "bt";
	
	private static Logger logger = LoggerFactory.getLogger(CameraServer.class);


	@Override
	public Optional<DeviceTelemetryData> readTelemetryData(SourceContext context, EndpointBinding binding, String deviceId) {
		
		final String _endpointUrl = binding.getEndpointUrl().replace("${cameraIP}", "http://"+context.getCameraIPAddress());
		logger.info("Reading telemetry state data from "+_endpointUrl);

		String jsonResponse;
		try {
			jsonResponse = restClient.getData(_endpointUrl, binding.getMediaType()).get();
			
		} catch (InterruptedException | ExecutionException e) {
			throw new FatalIntegrationException(e);
		}
		
		return Optional.of(new DeviceTelemetryData(PLATFORM_KEY, jsonResponse));
	}

	@Override
	public List<String> getDevices(SourceContext context) {
		List<String> connectedDevices = new ArrayList<String>(1);
		connectedDevices.add(context.getCameraIPAddress()); // a single source is the device itself
		return connectedDevices;
	}

	@Override
	public String getPlatformKey() {
		return PLATFORM_KEY;
	}
}
