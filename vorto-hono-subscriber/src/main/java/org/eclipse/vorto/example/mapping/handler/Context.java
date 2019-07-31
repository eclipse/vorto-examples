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
import org.eclipse.vorto.example.mapping.internal.deserializer.IDeserializer;
import org.eclipse.vorto.example.mapping.internal.deserializer.MimeType;

public class Context {

	private String deviceId;
	private String namespace;
	private MimeType mimeType;
	private Object rawPayload;

	public Context(String deviceId, String namespace, MimeType mimeType, Object rawPayload) {
		super();
		this.deviceId = getDeviceId(deviceId);
		this.namespace = getNamespace(deviceId, namespace);
		this.mimeType = mimeType;
		this.rawPayload = rawPayload;
	}
	

	public String getDeviceId() {
		return deviceId;
	}

	public String getNamespace() {
		return namespace;
	}
	
	public MimeType getMimeType() {
	  return mimeType;
	}

	public Object getRawPayload() {
	  return this.rawPayload;
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
