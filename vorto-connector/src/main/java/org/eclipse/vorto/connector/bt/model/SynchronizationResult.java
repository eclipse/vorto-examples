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

public class SynchronizationResult {
	enum Status {
		deviceNotRegistered, syncSucceed, syncFailed
	}

	private Status status;
	private String endpointUrl;
	private String deviceId;

	public static SynchronizationResult notRegistered(String deviceId) {
		return new SynchronizationResult(Status.deviceNotRegistered, deviceId, null);
	}
	
	public static SynchronizationResult succeeded(String deviceId, String endpointUrl) {
		return new SynchronizationResult(Status.syncSucceed, deviceId, endpointUrl);
	}
	
	public static SynchronizationResult failed(String deviceId, String endpointUrl) {
		return new SynchronizationResult(Status.syncFailed, deviceId, endpointUrl);
	}
	
	private SynchronizationResult(Status status, String deviceId, String endpointUrl) {
		this.status = status;
		this.deviceId = deviceId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getEndpointUrl() {
		return endpointUrl;
	}

	public void setEndpointUrl(String endpointUrl) {
		this.endpointUrl = endpointUrl;
	}

	@Override
	public String toString() {
		return "SynchronizationResult [status=" + status + ", endpointUrl=" + endpointUrl + ", deviceId=" + deviceId
				+ "]";
	}
}
