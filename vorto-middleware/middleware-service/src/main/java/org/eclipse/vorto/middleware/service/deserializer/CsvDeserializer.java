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
package org.eclipse.vorto.middleware.service.deserializer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.eclipse.vorto.middleware.monitoring.IPayloadMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvDeserializer extends AbstractDeserializer {
	
	@Override
	public Object deserialize(Message message, IPayloadMonitor monitor) {
		
		String textMessage;
		try {			
			textMessage = ((TextMessage) message).getText();
			monitor.info(getDeviceId(message),textMessage);
			final String[] payload = textMessage.split(",");

			return payload;
		} catch (JMSException e) {
			monitor.error(getDeviceId(message),String.format("Unable to deserialize CSV payload to Array. Could not get text from message."));
			e.printStackTrace();
			return null;
		}
	}
}
