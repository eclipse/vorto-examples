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

public class SourceContext {
	
	private String registeredDeviceId;
	private int cameraPort;
	
	/**
	 * Creates a new Camera Source that can communicate via the connector
	 * @param deviceId , e.g. <dittoNamespace>:<cameraIP>
	 * @param port port of the camera
	 * @return
	 */
	public static SourceContext newSource(String deviceId, int port) {
		return new SourceContext(deviceId,port);
	}
	
	/**
	 * Creates a new Camera Source that can communicate via the connector
	 * @param dittoNamespace
	 * @param cameraIPAddress
	 * @param cameraPort
	 * @return
	 */
	public static SourceContext newSource(String dittoNamespace, String cameraIPAddress, int port) {
		return new SourceContext(dittoNamespace+":"+cameraIPAddress,port);
	}

	private SourceContext(String registeredDeviceId, int port) {
		this.registeredDeviceId = registeredDeviceId;
		this.cameraPort = port;
	}

	public String getDeviceId() {
		return registeredDeviceId;
	}
	
	public String getCameraIPAddress() {
		return registeredDeviceId.substring(registeredDeviceId.indexOf(":")+1) + ":"+cameraPort;
	}
	
}
