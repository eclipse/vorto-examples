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
package org.eclipse.vorto.example.mapping.service.deserializer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonDeserializer implements IDeserializer {

	private static final Logger logger = LoggerFactory.getLogger(JsonDeserializer.class);
	protected static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public Object deserialize(Message message) {
		String textMessage;
		try {
			textMessage = ((TextMessage) message).getText();
			final Object payload = gson.fromJson(textMessage, Object.class);

			return payload;
		} catch (JMSException e) {
			logger.error(
					String.format("Unable to deserialize JSON payload to Object. Could not get text from message."));
			e.printStackTrace();
			return null;
		}

	}

}
