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
package org.eclipse.vorto.connector.bt.core.sink.boschiotsuite.hono;

import com.google.gson.annotations.SerializedName;

public class DeviceIdData {

	@SerializedName("device-id")
	private String deviceId = null;
	
	private String modelId = null;
	
	private String thingId = null;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	@Override
	public String toString() {
		return "DeviceIdData [deviceId=" + deviceId + ", modelId=" + modelId + ", thingId=" + thingId + "]";
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getThingId() {
		return thingId;
	}

	public void setThingId(String thingId) {
		this.thingId = thingId;
	}

}
