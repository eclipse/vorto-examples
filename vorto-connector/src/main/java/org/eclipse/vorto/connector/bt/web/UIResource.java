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
package org.eclipse.vorto.connector.bt.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.vorto.connector.bt.core.Sink;
import org.eclipse.vorto.connector.bt.core.SourceTracker;
import org.eclipse.vorto.connector.bt.model.RegisteredDeviceId;
import org.eclipse.vorto.connector.bt.service.SynchronizationService;
import org.eclipse.vorto.connector.bt.web.model.ConnectedDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class UIResource {
	private static Logger logger = LoggerFactory.getLogger(UIResource.class);

	@Autowired
	private SynchronizationService syncService;
	
	@Autowired
	private SourceTracker sourceTracker;
	
	@Autowired
	private Sink sink;
		
	@RequestMapping(value = "/devices",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ConnectedDevice> getDevices() {
		logger.info("Fetching device list");
		List<ConnectedDevice> devices = new ArrayList<ConnectedDevice>();
		
		sourceTracker.getTrackedSources().forEach(source -> {
			ConnectedDevice connectedDevice = new ConnectedDevice();
			connectedDevice.setBrand("Bosch BT");
			connectedDevice.setDeviceId(source.getCameraIPAddress());
			Optional<RegisteredDeviceId> registeredDevice = sink.getRegisteredDeviceId(source);
			if (registeredDevice.isPresent()) {
				connectedDevice.setThingId(registeredDevice.get().getThingId());
				connectedDevice.setName(registeredDevice.get().getModelId().getName());
			}
			devices.add(connectedDevice);
		});
		
		return devices;
	}
	
	@RequestMapping(value = "/startSync",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Scheduled(fixedRate=5000)
	public void startSync() {
		sourceTracker.getTrackedSources().forEach(sourceContext -> {
			syncService.synchronize(sourceContext).forEach(syncResult -> logger.info(syncResult.toString()));
		});
	}

}

	