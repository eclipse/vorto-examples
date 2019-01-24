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

import org.eclipse.vorto.model.ModelId;

public class RegisteredDeviceId {

	private String deviceId;
	private String informationModelId;
	private String thingId;
	
	public static RegisteredDeviceId create(String deviceId, String thingId, String modelId) {
		RegisteredDeviceId attributes = new RegisteredDeviceId(deviceId);
		attributes.thingId = thingId;
		attributes.informationModelId = modelId;
		return attributes;
	}
	
	private RegisteredDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public ModelId getModelId() {
		return ModelId.fromPrettyFormat(this.informationModelId);
	}
	
	public String getThingId() {
		return thingId;
	}
	
	public String getDeviceId() {
		return this.deviceId;
	}
	
	public boolean hasInformationModelId() {
		return this.informationModelId != null;
	}
	
	public boolean hasThingId() {
		return this.thingId != null;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((informationModelId == null) ? 0 : informationModelId.hashCode());
		result = prime * result + ((thingId == null) ? 0 : thingId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisteredDeviceId other = (RegisteredDeviceId) obj;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (informationModelId == null) {
			if (other.informationModelId != null)
				return false;
		} else if (!informationModelId.equals(other.informationModelId))
			return false;
		if (thingId == null) {
			if (other.thingId != null)
				return false;
		} else if (!thingId.equals(other.thingId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegisteredDeviceId [deviceId=" + deviceId + ", informationModelId=" + informationModelId + ", thingId="
				+ thingId + "]";
	}
	
	
	
}
