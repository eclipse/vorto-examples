package org.eclipse.vorto.example.mapping.web;

import org.eclipse.vorto.example.mapping.monitoring.IPayloadMonitor;
import org.eclipse.vorto.example.mapping.monitoring.IPayloadMonitorCallback;
import org.eclipse.vorto.example.mapping.monitoring.MonitorMessage;
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
	
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	
	@MessageMapping("/endpoint/subscribe")
	public void subscribe(String thingId) throws Exception {
		
		messageLogger.registerCallback(new IPayloadMonitorCallback() {
			
			@Override
			public void onMessage(MonitorMessage message) {
				messagingTemplate.convertAndSend( "/topic/device/" + thingId, gson.toJson(message));	
			}
		});
	}
	
	@MessageMapping("/endpoint/unsubscribe")
	public void unsubscribe() throws Exception {
		messageLogger.unregisterCallback();
	}
	
}
