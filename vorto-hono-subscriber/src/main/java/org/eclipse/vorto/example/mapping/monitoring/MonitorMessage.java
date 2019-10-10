package org.eclipse.vorto.example.mapping.monitoring;

import java.util.Date;

public class MonitorMessage {

	private String deviceId;
	private Date timestamp;
	
	private String text;
	private Severity severity;
	
	public MonitorMessage(String deviceId, String text, Severity severity) {
		super();
		this.text = text;
		this.deviceId = deviceId;
		this.timestamp = new Date();
		this.severity = severity;
	}
	
	@SuppressWarnings("unused")
	private MonitorMessage() {
		
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




	public enum Severity {
		INFO,WARNING,ERROR
	}
}
