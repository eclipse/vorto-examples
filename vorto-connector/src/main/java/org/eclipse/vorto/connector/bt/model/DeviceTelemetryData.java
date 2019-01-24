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
package org.eclipse.vorto.connector.bt.model;

public class DeviceTelemetryData {
	
	private String platformKey;
	
	private String jsonPayload;

	public DeviceTelemetryData(String platformKey, String jsonPayload) {
		super();
		this.platformKey = platformKey;
		this.jsonPayload = jsonPayload;
	}

	public String getSourcePlatformKey() {
		return platformKey;
	}

	public String getJsonPayload() {
		return jsonPayload;
	}

	@Override
	public String toString() {
		return "DevicePayload [platformKey=" + platformKey + ", jsonPayload=" + jsonPayload + "]";
	}
	
	

}
