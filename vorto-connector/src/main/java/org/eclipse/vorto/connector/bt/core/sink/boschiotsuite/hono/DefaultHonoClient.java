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

import java.util.Base64;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.eclipse.vorto.connector.bt.core.sink.boschiotsuite.utils.AsyncInvocationTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class DefaultHonoClient implements HonoClient {

	private String honoUrl;

	private String username;

	private String password;

	private AsyncInvocationTemplate restTemplate;

	private RequestConfig config;

	private Gson gson;

	public DefaultHonoClient(String honoUrl, RequestConfig config, String username, String password) {
		this.honoUrl = honoUrl;
		this.username = username;
		this.password = password;
		this.config = config;
		this.gson = new GsonBuilder().create();
		this.restTemplate = new AsyncInvocationTemplate(this.gson);
	}

	private CompletableFuture<DeviceIdData> get(String tenant, String deviceId) {
		HttpGet getDeviceIdData = new HttpGet(String.format("%s/registration/%s/%s", this.honoUrl, tenant, deviceId));

		if (config != null) {
			getDeviceIdData.setConfig(config);
		}

		if (!StringUtils.isBlank(this.username)) {
			getDeviceIdData.addHeader(HttpHeaders.AUTHORIZATION,
					"Basic " + Base64.getEncoder().encodeToString((this.username + ":" + this.password).getBytes()));
		}
		return restTemplate.execute(getDeviceIdData, new TypeToken<DeviceIdData>() {
		}.getType());
	}

	public CompletableFuture<HubRegistration> getHubRegistration(String tenant, String deviceId) {
		return get(tenant, deviceId).thenApply(deviceIdData -> HubRegistration.fromRegistrationRequest(deviceIdData));
	}

	
}
