package org.eclipse.vorto.middleware.config;

import org.eclipse.vorto.middleware.plugins.IPlugin;
import org.eclipse.vorto.middleware.plugins.impl.EclipseDittoPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PluginsConfiguration {

	@Value(value = "${amqp.url:null}")
	private String amqpUrl;
	
	@Value(value = "${amqp.topic.ditto:telemetry/vorto/ditto}")
	private String topic;
	
	@Value(value = "${amqp.username:null}")
	private String username;
	
	@Value(value = "${amqp.password:null}")
	private String password;
	
	@Bean
	public IPlugin dittoPlugin() {
		EclipseDittoPlugin dittoPlugin = new EclipseDittoPlugin();
		dittoPlugin.setAmqpUrl(amqpUrl);
		dittoPlugin.setTopic(topic);
		dittoPlugin.setUsername(username);
		dittoPlugin.setPassword(password);
		dittoPlugin.init();
		
		return dittoPlugin;
	}
}
