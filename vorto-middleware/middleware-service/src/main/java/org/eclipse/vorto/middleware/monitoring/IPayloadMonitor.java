package org.eclipse.vorto.middleware.monitoring;

public interface IPayloadMonitor {

	void info(String deviceId, String message);
	
	void warn(String deviceId, String message);
	
	void error(String deviceId, String message);
	
	void registerCallback(IPayloadMonitorCallback callback);
	
	void unregisterCallback();
	
}



