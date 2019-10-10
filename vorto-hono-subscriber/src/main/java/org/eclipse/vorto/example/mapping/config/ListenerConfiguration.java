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
package org.eclipse.vorto.example.mapping.config;

import javax.jms.ConnectionFactory;

import org.eclipse.vorto.example.mapping.service.EclipseHonoPayloadListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.SimpleMessageListenerContainer;

@Configuration
public class ListenerConfiguration {

	@Value("${msg.queue}")
	private String msgQueue;
	@Value("${session.cache.size:1}")
	private int sessionCacheSize;
	@Value("#{honoConnectionFactory}")
	private ConnectionFactory qpidConnectionFactory;

	@Bean
	public CachingConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setTargetConnectionFactory(qpidConnectionFactory);
		cachingConnectionFactory.setSessionCacheSize(sessionCacheSize);
		return cachingConnectionFactory;
	}

	@Bean
	EclipseHonoPayloadListener receiver() {
		return new EclipseHonoPayloadListener();
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setMessageListener(receiver());
		container.setConnectionFactory(connectionFactory);
		container.setDestinationName(msgQueue);
		return container;
	}

}
