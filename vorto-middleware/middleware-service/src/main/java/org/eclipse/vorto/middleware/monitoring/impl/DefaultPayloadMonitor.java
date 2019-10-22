package org.eclipse.vorto.middleware.monitoring.impl;

import org.eclipse.vorto.middleware.monitoring.IPayloadMonitor;
import org.eclipse.vorto.middleware.monitoring.IPayloadMonitorCallback;
import org.eclipse.vorto.middleware.monitoring.MonitorMessage;
import org.eclipse.vorto.middleware.monitoring.MonitorMessage.Severity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultPayloadMonitor implements IPayloadMonitor {
	
	private IPayloadMonitorCallback callback = null;
	
	private static int activeCallbacks = 0;
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultPayloadMonitor.class);

	@Override
	public void info(String deviceId, String message) {
		logger.info(deviceId + "---> "+message);
		this.doLog(new MonitorMessage(deviceId, message,Severity.INFO));
	}

	@Override
	public void warn(String deviceId, String message) {
		logger.warn(deviceId + "---> "+message);
		this.doLog(new MonitorMessage(deviceId, message,Severity.WARNING));
		
	}

	@Override
	public void error(String deviceId, String message) {
		logger.error(deviceId + "---> "+message);
		this.doLog(new MonitorMessage(deviceId, message,Severity.ERROR));
		
	}
	
	private void doLog(MonitorMessage message) {
		if (callback != null) {
			callback.onMessage(message);
		}
		
	}

	@Override
	public void registerCallback(IPayloadMonitorCallback callback) {
		this.callback = callback;
		activeCallbacks++;
		
	}

	@Override
	public void unregisterCallback() {
		 activeCallbacks--;
		 if (activeCallbacks == 0) {
			 callback = null;
		 }
		
	}

}
