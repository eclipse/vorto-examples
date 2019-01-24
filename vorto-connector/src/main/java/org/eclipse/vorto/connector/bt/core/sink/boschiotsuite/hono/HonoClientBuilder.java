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

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;

public class HonoClientBuilder {
	private String endpointUrl;
	private String username;
	private String password;
	private RequestConfig config;
	
	public HonoClientBuilder withEndpointUrl(final String url) {
		this.endpointUrl = url;
		return this;
	}
	
	public HonoClientBuilder withCredential(final String username, final String password) {
		this.username = username;
		this.password = password;
		return this;
	}
	
	public HonoClientBuilder withProxy(final String proxyHost, final int proxyPort) {
		this.config = RequestConfig.custom().setProxy(new HttpHost(proxyHost, proxyPort)).build();
		return this;
	}
	
	public HonoClient build() {
		return new DefaultHonoClient(endpointUrl, config, username, password);
	}
}
