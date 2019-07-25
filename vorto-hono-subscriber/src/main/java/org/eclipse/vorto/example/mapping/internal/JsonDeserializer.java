package org.eclipse.vorto.example.mapping.internal;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonDeserializer implements Deserializer {

	private static final Logger logger = LoggerFactory.getLogger(JsonDeserializer.class);
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
