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
package org.eclipse.vorto.middleware.plugins.amqp;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.eclipse.vorto.middleware.monitoring.MonitorMessage;
import org.eclipse.vorto.middleware.monitoring.MonitorMessage.Severity;
import org.eclipse.vorto.middleware.plugins.AbstractPlugin;
import org.eclipse.vorto.middleware.plugins.ExecutionContext;
import org.eclipse.vorto.middleware.plugins.TextConfigurationItem;
import org.eclipse.vorto.model.runtime.FunctionblockValue;
import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EclipseVortoAMQPPlugin extends AbstractPlugin {

	protected JmsTemplate jmsTemplate;

	protected static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	protected String topic;
	protected String username;
	protected String password;
	protected String amqpUrl;

	@Override
	public void doExecute(InfomodelValue infomodelValue, ExecutionContext context) {
		for (String fbProperty : infomodelValue.getProperties().keySet()) {
			FunctionblockValue value = infomodelValue.get(fbProperty);
			String functionblockCommand = gson.toJson(value.serialize());
			context.getLogger().monitor(MonitorMessage.outboundMessage(context.getCorrelationId(),
					context.getDeviceId(), functionblockCommand, Severity.INFO, getId()));
			try {
				jmsTemplate.convertAndSend(topic, functionblockCommand, new MessagePostProcessor() {
					@Override
					public Message postProcessMessage(Message message) throws JMSException {
						message.setStringProperty("tag", fbProperty);
						message.setStringProperty("modelId", value.getMeta().getId().getPrettyFormat());
						message.setStringProperty("deviceId", context.getDeviceId());
						return message;
					}
				});
			} catch (JmsException exception) {
				throw new ExecutionProblem("Could not send vorto data to AMQP", exception);
			}
		}

	}

	@Override
	public String getId() {
		return "AMQP_VORTO";
	}

	@Override
	public String getName() {
		return "Eclipse Vorto - AMQP 1.0";
	}

	@Override
	public String getDescription() {
		return "Publishes harmonized device telemetry data as AMQP topic 'telemetry/vorto'.";
	}

	@Override
	public String getImageUrl() {
		return "https://www.eclipse.org/vorto/images/vorto.png";
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
		this.username = ((TextConfigurationItem) configuration.get("username")).getValue();
		this.password = ((TextConfigurationItem) configuration.get("password")).getValue();
	}

	@Override
	public void start() {
		logger.info("Trying to start plugin: "+getId());
		
		if (this.topic == null) {
			throw new CannotStartPluginException("Topic is not configured.");
		}

		try {

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
			
			logger.info("Started successfully");
		} catch (Throwable t) {
			throw new CannotStartPluginException(t);
		}

	}

	@Override
	public void stop() {
		logger.info("Stopping plugin: "+getId());
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
