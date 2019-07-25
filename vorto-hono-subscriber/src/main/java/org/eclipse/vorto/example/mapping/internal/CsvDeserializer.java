package org.eclipse.vorto.example.mapping.internal;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvDeserializer implements Deserializer {

	private static final Logger logger = LoggerFactory.getLogger(CsvDeserializer.class);

	@Override
	public Object deserialize(Message message) {
		String textMessage;
		try {
			textMessage = ((TextMessage) message).getText();
			final String[] payload = textMessage.split(",");

			return payload;
		} catch (JMSException e) {
			logger.error(String.format("Unable to deserialize CSV payload to Array. Could not get text from message."));
			e.printStackTrace();
			return null;
		}
	}
}
