/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
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
package org.eclipse.vorto.middleware.web;

import org.eclipse.vorto.middleware.monitoring.IPayloadMonitor;
import org.eclipse.vorto.middleware.monitoring.IPayloadMonitorCallback;
import org.eclipse.vorto.middleware.monitoring.MonitorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class DataSocket {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private IPayloadMonitor messageLogger;
	
	private static Gson gson = new GsonBuilder().create();

	@MessageMapping("/endpoint/subscribe")
	public void subscribe() throws Exception {
		
		messageLogger.registerCallback(new IPayloadMonitorCallback() {
			
			@Override
			public void onMessage(MonitorMessage message) {
				messagingTemplate.convertAndSend( "/topic/device/", gson.toJson(message));	
			}
		});
	}
	
	@MessageMapping("/endpoint/unsubscribe")
	public void unsubscribe() throws Exception {
		messageLogger.unregisterCallback();
	}
}
