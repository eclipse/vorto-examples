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
package org.eclipse.vorto.middleware.config;

import org.eclipse.vorto.middleware.plugins.IPlugin;
import org.eclipse.vorto.middleware.plugins.impl.EclipseDittoPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PluginsConfiguration {

	@Value(value = "${amqp.url:null}")
	private String amqpUrl;
	
	@Value(value = "${amqp.topic.ditto:telemetry/vorto/ditto}")
	private String topic;
	
	@Value(value = "${amqp.username:null}")
	private String username;
	
	@Value(value = "${amqp.password:null}")
	private String password;
	
	@Bean
	public IPlugin dittoPlugin() {
		EclipseDittoPlugin dittoPlugin = new EclipseDittoPlugin();
		dittoPlugin.setAmqpUrl(amqpUrl);
		dittoPlugin.setTopic(topic);
		dittoPlugin.setUsername(username);
		dittoPlugin.setPassword(password);
		dittoPlugin.init();
		
		return dittoPlugin;
	}
}
