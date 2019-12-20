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
import org.eclipse.vorto.middleware.plugins.IPlugin.CannotStartPluginException;
import org.eclipse.vorto.middleware.plugins.amqp.EclipseVortoAMQPPlugin;
import org.eclipse.vorto.middleware.plugins.ditto.EclipseDittoPlugin;
import org.eclipse.vorto.middleware.plugins.kinesis.AWSKinesisPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PluginsConfiguration {
	
	protected static final Logger logger = LoggerFactory.getLogger(PluginsConfiguration.class);

	@Value(value = "${amqp.url:#{null}}")
	private String amqpUrl;
	
	@Value(value = "${amqp.topic.ditto:#{null}}")
	private String dittoTopic;
	
	@Value(value = "${amqp.topic.vorto:#{null}}")
	private String vortoTopic;
	
	@Value(value = "${amqp.username:#{null}}")
	private String username;
	
	@Value(value = "${amqp.password:#{null}}")
	private String password;
	
	@Value(value = "${kinesis.accessKey:#{null}}")
	private String kinesisAccessKey;
	
	@Value(value = "${kinesis.secretKey:#{null}}")
	private String kinesisSecretKey;
	
	@Value(value = "${kinesis.streamName:#{null}}")
	private String kinesisStreamName;
	
	@Value(value = "${kinesis.partitionKey:#{null}}")
	private String kinesisPartitionKey;
	
	@Bean
	public IPlugin vortoPlugin() {
		EclipseVortoAMQPPlugin vortoPlugin = new EclipseVortoAMQPPlugin();
		vortoPlugin.setAmqpUrl(amqpUrl);
		vortoPlugin.setTopic(vortoTopic);
		vortoPlugin.setUsername(username);
		vortoPlugin.setPassword(password);
		
		try {
			vortoPlugin.start();	
		} catch(CannotStartPluginException ex) {
			logger.warn("Could not start plugin. Reason:"+ex.getMessage());
		}
		
		return vortoPlugin;
	}
	
	@Bean
	public IPlugin dittoPlugin() {
		EclipseDittoPlugin dittoPlugin = new EclipseDittoPlugin();
		dittoPlugin.setAmqpUrl(amqpUrl);
		dittoPlugin.setTopic(dittoTopic);
		dittoPlugin.setUsername(username);
		dittoPlugin.setPassword(password);
		
		try {
			dittoPlugin.start();	
		} catch(CannotStartPluginException ex) {
			logger.warn("Could not start plugin. Reason:"+ex.getMessage());
		}
		
		return dittoPlugin;
	}
	
	@Bean
	public IPlugin kinesisPlugin() {
		AWSKinesisPlugin kinesisPlugin = new AWSKinesisPlugin();
		kinesisPlugin.setAccessKey(kinesisAccessKey);
		kinesisPlugin.setSecretKey(kinesisSecretKey);
		kinesisPlugin.setPartitionKey(kinesisPartitionKey);
		kinesisPlugin.setStreamName(kinesisStreamName);
		try {
			kinesisPlugin.start();	
		} catch(CannotStartPluginException ex) {
			logger.warn("Could not start plugin. Reason:"+ex.getMessage());
		}
		return kinesisPlugin;
	}
}
