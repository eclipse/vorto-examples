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

public class HubRegistration {
	private String deviceId;
	private String thingId;
	private String modelId;
	private boolean enabled;

	public static HubRegistration fromRegistrationRequest(DeviceIdData deviceData) {
		if (deviceData == null)
			return null;

		HubRegistration registration = new HubRegistration();
		registration.setDeviceId(deviceData.getDeviceId());
		registration.setModelId(deviceData.getModelId());
		registration.setThingId(deviceData.getThingId());
		return registration;
	}

	public static HubRegistration from(String deviceId, String thingType, String thingId) {
		HubRegistration registration = new HubRegistration();
		registration.setDeviceId(deviceId);
		registration.setEnabled(true);
		registration.setModelId(thingType);
		registration.setThingId(thingId);
		return registration;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getThingId() {
		return thingId;
	}

	public void setThingId(String thingId) {
		this.thingId = thingId;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "HubRegistration [deviceId=" + deviceId + ", thingId=" + thingId + ", modelId=" + modelId + ", enabled="
				+ enabled + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((modelId == null) ? 0 : modelId.hashCode());
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
		HubRegistration other = (HubRegistration) obj;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (enabled != other.enabled)
			return false;
		if (modelId == null) {
			if (other.modelId != null)
				return false;
		} else if (!modelId.equals(other.modelId))
			return false;
		if (thingId == null) {
			if (other.thingId != null)
				return false;
		} else if (!thingId.equals(other.thingId))
			return false;
		return true;
	}
}
