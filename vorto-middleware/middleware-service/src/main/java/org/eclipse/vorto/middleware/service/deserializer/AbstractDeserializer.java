package org.eclipse.vorto.middleware.service.deserializer;

import javax.jms.JMSException;
import javax.jms.Message;

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
<<<<<<< HEAD
			return message.getJMSMessageID();
=======
			return message.getJMSCorrelationID();
>>>>>>> modified monitor message for correlation
		} catch (JMSException e) {
			return "unknown";		
		}
	}
	
}
