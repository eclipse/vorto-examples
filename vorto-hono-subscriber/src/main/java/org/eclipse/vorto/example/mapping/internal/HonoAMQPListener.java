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
package org.eclipse.vorto.example.mapping.internal;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.eclipse.vorto.example.mapping.handler.Context;
import org.eclipse.vorto.example.mapping.handler.IPayloadHandler;
import org.eclipse.vorto.model.ModelId;
import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * AMQP Queue listener for any telemetry data coming in for the given tenant
 *
 */
public class HonoAMQPListener implements MessageListener {
	private static final Logger logger = LoggerFactory.getLogger(HonoAMQPListener.class);

	@Value(value = "${hono.tenantId}")
	private String tenantId;

	@Autowired
	private MappingService mappingService;

	@Autowired
	private List<IPayloadHandler> payloadHandlers;

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private static final String HEADER_DEVICE_ID = "device_id";
	private static final String HEADER_VORTO_ID = "vorto";
	private static final String HEADER_CONTENT_TYPE = "JMS_AMQP_CONTENT_TYPE";

	@Override
	public void onMessage(Message message) {
		logger.debug("Received AMQP message from Hono ...");
		try {
			final String deviceId = message.getStringProperty(HEADER_DEVICE_ID);
			final String modelId = message.getStringProperty(HEADER_VORTO_ID);
			final String contentType = message.getStringProperty(HEADER_CONTENT_TYPE);

			if (modelId == null) {
				logger.error(
						"No vorto model id found in message. Please add a field 'vorto' as a custom field during device registration.");
				return;
			}

			final Deserializer deserializer = DeserializerFactory.getDeserializer(contentType);
			final Object deserializedPayload = deserializer.deserialize(message);

			final InfomodelValue normalizedData = mappingService.map(ModelId.fromPrettyFormat(modelId),
					deserializedPayload);

			final String json = gson.toJson(normalizedData.serialize());
			final JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

			payloadHandlers.stream().forEach(handler -> {
				logger.info("Invoking payload handler " + handler.getClass().getName());
				try {
					handler.handlePayload(jsonObject, new Context(deviceId, normalizedData));
				} catch (Throwable t) {
					logger.error("Problem during handler invocation", t);
				}
			});

		} catch (JMSException e) {
			logger.error("Problem with consuming AMQP message", e);
		}
	}
}
