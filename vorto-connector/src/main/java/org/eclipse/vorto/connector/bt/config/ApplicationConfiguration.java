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
package org.eclipse.vorto.connector.bt.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.eclipse.vorto.connector.bt.core.SourceTracker;
import org.eclipse.vorto.connector.bt.core.sink.boschiotsuite.HonoDataService;
import org.eclipse.vorto.connector.bt.core.sink.boschiotsuite.hono.HonoClient;
import org.eclipse.vorto.connector.bt.core.sink.boschiotsuite.hono.HonoClientBuilder;
import org.eclipse.vorto.connector.bt.model.SourceContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

	@Value(value = "${hono.mqttEndpoint}")
	private String mqttEndpoint;
	
	@Value(value = "${hono.devicePassword}")
	private String devicePassword;
	
	@Value(value = "${hono.registryEndpoint}")
	private String registryEndpoint;

	@Value(value = "${hono.registryUsername}")
	private String honoUsername;
	
	@Value(value = "${hono.registryPassword}")
	private String honoPassword;
	
	@Value(value = "${hono.tenant}")
	private String honoTenant;

	@Value("${http.proxyHost:#{null}}")
	private String proxyHost;

	@Value("${http.proxyPort:8080}")
	private int proxyPort;

	@Value("${http.proxyUser:#{null}}")
	private String proxyUser;

	@Value("${http.proxyPassword:#{null}}")
	private String proxyPassword;

	@Bean
	public HonoClient honoManagementClient() {
		HonoClientBuilder builder = HonoClient.newBuilder().withEndpointUrl(registryEndpoint)
				.withCredential(honoUsername, honoPassword);
		if (this.proxyHost != null && !"".equals(this.proxyHost)) {
			builder.withProxy(this.proxyHost, this.proxyPort);
		}
		return builder.build();

	}
	
	@Bean
	public HonoDataService honoDataClient() {
		return new HonoDataService(mqttEndpoint, honoTenant, devicePassword);
	}

	@Bean
	public CloseableHttpClient httpClient() {
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();

		clientBuilder.useSystemProperties();

		if (proxyHost != null && !"".equals(this.proxyHost)) {
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(new AuthScope(proxyHost, proxyPort),
					new UsernamePasswordCredentials(proxyUser, proxyPassword));

			clientBuilder.setProxy(new HttpHost(proxyHost, proxyPort));
			clientBuilder.setDefaultCredentialsProvider(credsProvider);
			clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());
		}

		return clientBuilder.build();
	}

	@Bean
	public SourceTracker sourceTracker() {
		SourceTracker sourceTracker = new SourceTracker();
		sourceTracker.addToTracker(SourceContext.newSource("com.bosch.sgp.aedelmann:223.25.65.193",10082));
//		sourceTracker.addToTracker(SourceContext.newSource("INSERT CAMERA IP ADDRESS HERE",port));
		return sourceTracker;
	}

}
