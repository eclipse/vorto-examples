package org.eclipse.vorto.middleware.web;

import org.eclipse.vorto.middleware.monitoring.IPayloadMonitor;
import org.eclipse.vorto.middleware.monitoring.IPayloadMonitorCallback;
import org.eclipse.vorto.middleware.monitoring.MonitorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class DataSocket {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private IPayloadMonitor messageLogger;
	
	private static Gson gson = new GsonBuilder().create();

	@MessageMapping("/endpoint/subscribe")
	public void subscribe() throws Exception {
		
		messageLogger.registerCallback(new IPayloadMonitorCallback() {
			
			@Override
			public void onMessage(MonitorMessage message) {
				messagingTemplate.convertAndSend( "/topic/device/", gson.toJson(message));	
			}
		});
	}
<<<<<<< HEAD
	
	@MessageMapping("/endpoint/unsubscribe")
	public void unsubscribe() throws Exception {
		messageLogger.unregisterCallback();
	}
=======
>>>>>>> added heartbeat for web socket detection
}
