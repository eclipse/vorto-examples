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

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

public class EndpointBinding {

	private HttpMethod method;
	
	private String endpointUrl;
	
	private MediaType mediaType;
	
	public static EndpointBinding create(String method, String endpointUrl, String mediaType) {
		return new EndpointBinding(HttpMethod.valueOf(method), endpointUrl, MediaType.valueOf(mediaType));
	}
	
	private EndpointBinding(HttpMethod method, String endpointUrl, MediaType mediaType) {
		this.method = method;
		this.endpointUrl = endpointUrl;
		this.mediaType = mediaType;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public String getEndpointUrl() {
		return endpointUrl;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	@Override
	public String toString() {
		return "EndpointBinding [method=" + method + ", endpointUrl=" + endpointUrl + ", mediaType=" + mediaType + "]";
	}
	
	
}
