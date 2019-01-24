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
import java.util.Map;
import java.util.Objects;

import org.eclipse.vorto.mapping.engine.twin.TwinPayloadFactory;
import org.eclipse.vorto.model.runtime.InfomodelValue;

import com.google.gson.JsonObject;

/**
 * Data Client Implementation that sends device data to Eclipse Hono via MQTT Endpoint
 *
 */
public class HonoDataService {
	
	private String mqttHostUrl;
	private String honoTenant;
	private String password;
	private Map<String, HonoMqttClient> deviceClients = new HashMap<String, HonoMqttClient>();
	
	public HonoDataService(String mqttHostUrl, String honoTenant, String password) {
		this.mqttHostUrl = Objects.requireNonNull(mqttHostUrl);
		this.honoTenant = Objects.requireNonNull(honoTenant);
		this.password = Objects.requireNonNull(password);
	}
	
	public void publishToHono(String deviceId, InfomodelValue value) {
		HonoMqttClient client = getConnectedHonoClient(deviceId);
		String[] dittoTopic = createTopic(deviceId);
		for (String featureId : value.getProperties().keySet()) {
			JsonObject payload = TwinPayloadFactory.toDittoProtocol(value.get(featureId), featureId, dittoTopic[0], dittoTopic[1]);
			client.send("telemetry/" + honoTenant + "/" + deviceId,payload.toString());
		}
	}
	
	private String[] createTopic(String deviceId) {
		final String dittoNamespace = deviceId.substring(0,deviceId.indexOf(":"));
		final String dittoSuffix = deviceId.substring(deviceId.indexOf(":")+1);
		return new String[] {dittoNamespace,dittoSuffix};
	}

	private HonoMqttClient getConnectedHonoClient(String deviceId) {
		HonoMqttClient client = deviceClients.get(deviceId);
		if (client == null) {
			client = new HonoMqttClient(mqttHostUrl, deviceId, createAuthId(deviceId), password);
			deviceClients.put(deviceId, client);
		}
		
		if (!client.isConnected()) {
			client.connect();
		}
		
		return client;
	}

	private String createAuthId(String deviceId) {
		return createTopic(deviceId)[1]+"@"+this.honoTenant;
	}
}
