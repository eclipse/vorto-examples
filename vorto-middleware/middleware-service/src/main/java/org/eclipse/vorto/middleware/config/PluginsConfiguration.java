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

import java.text.SimpleDateFormat;

import org.eclipse.vorto.middleware.plugins.IPlugin;
import org.eclipse.vorto.middleware.plugins.impl.EclipseDittoPlugin;
import org.eclipse.vorto.model.IModel;
import org.eclipse.vorto.model.IPropertyAttribute;
import org.eclipse.vorto.model.IReferenceType;
import org.eclipse.vorto.plugin.generator.adapter.ObjectMapperFactory.ModelDeserializer;
import org.eclipse.vorto.plugin.generator.adapter.ObjectMapperFactory.ModelReferenceDeserializer;
import org.eclipse.vorto.plugin.generator.adapter.ObjectMapperFactory.PropertyAttributeDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

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
