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
package org.eclipse.vorto.example.mapping.handler;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.vorto.model.runtime.InfomodelValue;

public class Context {

	private String deviceId;
	private String namespace;
	private InfomodelValue normalizedModel;

	public Context(String deviceId, String namespace, InfomodelValue normalizedModel) {
		super();
		this.deviceId = getDeviceId(deviceId);
		this.namespace = getNamespace(deviceId, namespace);
		this.normalizedModel = normalizedModel;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public String getNamespace() {
		return namespace;
	}

	public InfomodelValue getNormalizedModel() {
		return normalizedModel;
	}

	private String getDeviceId(String deviceId) {
		if (!deviceId.contains(":")) {
			return deviceId;
		}

		Pattern pattern = Pattern.compile(".*:(.*)");
		Matcher matcher = pattern.matcher(deviceId);

		if (matcher.matches()) {
			return matcher.group(1);
		}

		throw new NoSuchElementException("No deviceID was provided for your device. Please use the deviceId header.");
	}

	private String getNamespace(String deviceId, String namespace) {
		if (namespace != null) {
			return namespace;
		}

		Pattern pattern = Pattern.compile("(.*):.*");
		Matcher matcher = pattern.matcher(deviceId);

		if (matcher.matches()) {
			return matcher.group(1);
		}

		throw new NoSuchElementException(
				"No namespace was provided for your device. Please use the namespace header or prefix your deviceId with the according namespace");
	}
}
