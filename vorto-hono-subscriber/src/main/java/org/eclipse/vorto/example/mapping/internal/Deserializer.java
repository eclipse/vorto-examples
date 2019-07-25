package org.eclipse.vorto.example.mapping.internal;

import javax.jms.Message;

public interface Deserializer {
	public Object deserialize(Message message);
}
