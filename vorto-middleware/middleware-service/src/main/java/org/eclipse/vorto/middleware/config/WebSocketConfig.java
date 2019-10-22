package org.eclipse.vorto.middleware.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Value(value = "${cors}")
	private String crossOrigin;
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry)  {
		registry.addEndpoint("/endpoint").setAllowedOrigins(crossOrigin).withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/middleware");
		registry.enableSimpleBroker("/topic/device");
	}

}
