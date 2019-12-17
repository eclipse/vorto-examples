package org.eclipse.vorto.middleware.internal.service.deserializer;

import javax.jms.JMSException;
import javax.jms.Message;

import org.eclipse.vorto.middleware.deserializer.IDeserializer;

public abstract class AbstractDeserializer implements IDeserializer {

	protected String getDeviceId(Message message) {
		try {
			return message.getStringProperty("device_id");
		} catch (JMSException e) {
			return "unknown";		
		}
	}
	
	protected String getCorrelationId(Message message) {
		try {
			return message.getJMSMessageID();
		} catch (JMSException e) {
			return "unknown";		
		}
	}
	
}
