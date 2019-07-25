package org.eclipse.vorto.example.mapping.internal;

import java.nio.charset.StandardCharsets;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;

import org.eclipse.vorto.mapping.engine.model.binary.BinaryData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class BytesDeserializer implements Deserializer {

	private static final Logger logger = LoggerFactory.getLogger(BytesDeserializer.class);
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public Object deserialize(Message message) {
		if (message instanceof BytesMessage) {
			final BytesMessage byteMessage = (BytesMessage) message;
			BinaryData binary;
			try {
				binary = convertBytesMessageToString(byteMessage);
				final Object payload = gson.fromJson(new String(binary.getData(), StandardCharsets.UTF_8),
						Object.class);

				return payload;
			} catch (JMSException e) {
				logger.error(String
						.format("Unable to deserialize Binary payload to Object. Could not get text from message."));
				e.printStackTrace();
			} catch (JsonSyntaxException invalidJson) {
				logger.error(String.format("Unable to create GSON object from binary payload."));
				invalidJson.printStackTrace();
			}
		}

		return null;
	}

	private static BinaryData convertBytesMessageToString(BytesMessage message) throws JMSException {
		byte[] byteData = null;
		byteData = new byte[(int) message.getBodyLength()];
		message.readBytes(byteData);
		message.reset();
		return new BinaryData(byteData);
	}

}
