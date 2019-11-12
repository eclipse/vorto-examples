/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional information regarding copyright
 * ownership.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which is available at https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.middleware.plugins.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.eclipse.vorto.mapping.targetplatform.ditto.TwinPayloadFactory;
import org.eclipse.vorto.middleware.monitoring.MonitorMessage;
import org.eclipse.vorto.middleware.monitoring.MonitorMessage.Severity;
import org.eclipse.vorto.middleware.plugins.AbstractPlugin;
import org.eclipse.vorto.middleware.plugins.DittoUtils;
import org.eclipse.vorto.middleware.plugins.ExecutionContext;
import org.eclipse.vorto.middleware.plugins.config.TextConfigurationItem;
import org.eclipse.vorto.middleware.service.deserializer.MimeType;
import org.eclipse.vorto.model.runtime.FunctionblockValue;
import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class EclipseDittoPlugin extends AbstractPlugin {

	private JmsTemplate jmsTemplate;

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private String topic;
	private String username;
	private String password;
	private String amqpUrl;

	@Override
	public void doExecute(InfomodelValue infomodelValue, ExecutionContext context) {
<<<<<<< HEAD
<<<<<<< HEAD
		if (context.getMimeType() == MimeType.ECLIPSE_DITTO) {
			context.getLogger().monitor(MonitorMessage.outboundMessage(context.getCorrelationId(), context.getDeviceId(), (String) context.getRawPayload(), Severity.INFO,getId()));
=======
		context.getLogger().monitor(MonitorMessage.outboundMessage(context.getCorrelationId(), context.getDeviceId(), "Publishing ditto protocol payload to AMQP endpoint", Severity.INFO));
		if (context.getMimeType() == MimeType.ECLIPSE_DITTO) {
			context.getLogger().monitor(MonitorMessage.outboundMessage(context.getCorrelationId(), context.getDeviceId(), (String) context.getRawPayload(), Severity.INFO));
>>>>>>> modified monitor message for correlation
=======
		if (context.getMimeType() == MimeType.ECLIPSE_DITTO) {
			context.getLogger().monitor(MonitorMessage.outboundMessage(context.getCorrelationId(), context.getDeviceId(), (String) context.getRawPayload(), Severity.INFO,getId()));
>>>>>>> added info about plugin ID for outbound messages
			try {
				jmsTemplate.convertAndSend(topic, (String) context.getRawPayload());
			} catch(JmsException exception) {
				throw new ExecutionProblem("Could not send data to AMQP", exception);
			}
		} else {
			for (String fbProperty : infomodelValue.getProperties().keySet()) {
				FunctionblockValue value = infomodelValue.get(fbProperty);
				JsonObject updateCommand = TwinPayloadFactory.toDittoProtocol(value, fbProperty, DittoUtils.getDittoNamespaceFromDeviceId(context.getDeviceId()),
						DittoUtils.getDittoSuffixFromDeviceId(context.getDeviceId()));
				String updateCommandJson = gson.toJson(updateCommand);
<<<<<<< HEAD
<<<<<<< HEAD
				context.getLogger().monitor(MonitorMessage.outboundMessage(context.getCorrelationId(), context.getDeviceId(), updateCommandJson, Severity.INFO,getId()));
=======
				context.getLogger().monitor(MonitorMessage.outboundMessage(context.getCorrelationId(), context.getDeviceId(), updateCommandJson, Severity.INFO));
>>>>>>> modified monitor message for correlation
=======
				context.getLogger().monitor(MonitorMessage.outboundMessage(context.getCorrelationId(), context.getDeviceId(), updateCommandJson, Severity.INFO,getId()));
>>>>>>> added info about plugin ID for outbound messages
				try {
					jmsTemplate.convertAndSend(topic, updateCommandJson);
				} catch(JmsException exception) {
					throw new ExecutionProblem("Could not send data to AMQP", exception);
				}
			}
		}

	}

	@Override
	public String getId() {
		return "AMQP_DITTO";
	}

	@Override
	public String getName() {
		return "Eclipse Ditto - Digital Twin API";
	}

	@Override
	public String getDescription() {
		return "Publishes harmonized device telemetry data to Eclipse Ditto Service.";
	}

	@Override
	public String getImageUrl() {
		return "https://www.eclipse.org/ditto/images/ditto.svg";
	}

	@Override
	public Map<String, TextConfigurationItem> getConfiguration() {
		Map<String, TextConfigurationItem> configuration = new HashMap<>();
		configuration.put("topic", new TextConfigurationItem("topic", "topic", this.topic));
		configuration.put("url", new TextConfigurationItem("url", "url", this.amqpUrl));
		configuration.put("username", new TextConfigurationItem("username", "username", this.username));
		configuration.put("password", new TextConfigurationItem("password", "password", this.password));
		return configuration;
	}

	@Override
	public void setConfiguration(Map<String, TextConfigurationItem> configuration) {
		this.amqpUrl = ((TextConfigurationItem) configuration.get("url")).getValue();
		this.topic = ((TextConfigurationItem) configuration.get("topic")).getValue();
		this.username = ((TextConfigurationItem) configuration.get("username")).getValue();
		this.password = ((TextConfigurationItem) configuration.get("password")).getValue();
	}

	@Override
	public void init() {
		if (jmsTemplate != null) {
			destroy();
		}

		final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(amqpUrl);

		// Pass the username and password.
		connectionFactory.setUserName(username);
		connectionFactory.setPassword(password);
		// Create a pooled connection factory.
		final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		pooledConnectionFactory.setConnectionFactory(connectionFactory);
		pooledConnectionFactory.setMaxConnections(10);

		this.jmsTemplate = new JmsTemplate(connectionFactory);
		this.jmsTemplate.setExplicitQosEnabled(true);
		this.jmsTemplate.setDeliveryPersistent(false);
		this.jmsTemplate.setTimeToLive(1000 * 60);
		
		this.setIsStarted(true);

	}

	@Override
	public void destroy() {
		this.jmsTemplate = null;
		this.setIsStarted(false);

	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAmqpUrl() {
		return amqpUrl;
	}

	public void setAmqpUrl(String amqpUrl) {
		this.amqpUrl = amqpUrl;
	}
	
	
}
