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
package org.eclipse.vorto.middleware.monitoring;

import java.util.Date;

public class MonitorMessage {

	private String deviceId;
	private String correlationId;	
	private Date timestamp;	
	private String text;
	private Severity severity;
	private Direction direction;
<<<<<<< HEAD
<<<<<<< HEAD
	private String outboundPluginId;
=======
>>>>>>> modified monitor message for correlation
=======
	private String outboundPluginId;
>>>>>>> added info about plugin ID for outbound messages
	
	private MonitorMessage(String correlationId, Date timestamp, String deviceId, String text, Severity severity, Direction direction) {
		super();
		this.correlationId = correlationId;
		this.text = text;
		this.deviceId = deviceId;
		this.timestamp = new Date();
		this.severity = severity;
		this.direction = direction;
	}
	
	private MonitorMessage() {
		
	}
	
	/**
	 * Creates an inbound monitoring message
	 * @param correlationId
	 * @param deviceId
	 * @param text
	 * @param severity
	 * @return
	 */
	public static MonitorMessage inboundMessage(String correlationId, String deviceId, String text, Severity severity) {
		return new MonitorMessage(correlationId,new Date(),deviceId, text,severity, Direction.INBOUND);
	}
	
	/**
	 * Creates an outbound monitoring message
	 * @param correlationId
	 * @param deviceId
	 * @param text
	 * @param severity
	 * @return
	 */
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> added info about plugin ID for outbound messages
	public static MonitorMessage outboundMessage(String correlationId, String deviceId, String text, Severity severity, String pluginId) {
		MonitorMessage msg = new MonitorMessage(correlationId,new Date(),deviceId, text,severity, Direction.OUTBOUND);
		msg.setOutboundPluginId(pluginId);
		return msg;
<<<<<<< HEAD
=======
	public static MonitorMessage outboundMessage(String correlationId, String deviceId, String text, Severity severity) {
		return new MonitorMessage(correlationId,new Date(),deviceId, text,severity, Direction.OUTBOUND);
>>>>>>> modified monitor message for correlation
=======
>>>>>>> added info about plugin ID for outbound messages
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correlationId == null) ? 0 : correlationId.hashCode());
		result = prime * result + ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((severity == null) ? 0 : severity.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MonitorMessage other = (MonitorMessage) obj;
		if (correlationId == null) {
			if (other.correlationId != null)
				return false;
		} else if (!correlationId.equals(other.correlationId))
			return false;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (direction != other.direction)
			return false;
		if (severity != other.severity)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}


	

	@Override
	public String toString() {
		return "MonitorMessage [deviceId=" + deviceId + ", correlationId=" + correlationId + ", timestamp=" + timestamp
				+ ", text=" + text + ", severity=" + severity + ", direction=" + direction + "]";
	}

<<<<<<< HEAD

	public String getOutboundPluginId() {
		return outboundPluginId;
	}

	public void setOutboundPluginId(String outboundPluginId) {
		this.outboundPluginId = outboundPluginId;
	}


=======
>>>>>>> modified monitor message for correlation

	public String getOutboundPluginId() {
		return outboundPluginId;
	}

	public void setOutboundPluginId(String outboundPluginId) {
		this.outboundPluginId = outboundPluginId;
	}





	public enum Severity {
		INFO,WARNING,ERROR
	}
	
	public enum Direction {
		INBOUND, OUTBOUND
	}
}
