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
package org.eclipse.vorto.example.mapping.handler.amq;

import javax.jms.ConnectionFactory;

import org.eclipse.vorto.example.mapping.handler.Context;
import org.eclipse.vorto.example.mapping.handler.IPayloadHandler;
import org.eclipse.vorto.mapping.engine.twin.TwinPayloadFactory;
import org.eclipse.vorto.model.runtime.FunctionblockValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class AMQPHandler implements IPayloadHandler {

	private static final Logger logger = LoggerFactory.getLogger(AMQPHandler.class);

	private JmsTemplate jmsTemplate;
	
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Value(value = "${amqp.topic}")
	private String topic;

	public AMQPHandler(ConnectionFactory connectionFactory) {
		this.jmsTemplate = new JmsTemplate(connectionFactory);
	}
	
	@Override
	public void handlePayload(JsonObject normalizedPayload, Context context) {
		for (String fbProperty : context.getNormalizedModel().getProperties().keySet()) {
			FunctionblockValue value = context.getNormalizedModel().get(fbProperty);
			JsonObject updateCommand = TwinPayloadFactory.toDittoProtocol(value, fbProperty, context.getNamespace(),
					context.getDeviceId());
			
			jmsTemplate.convertAndSend(topic, gson.toJson(updateCommand));
		}

	}
}
