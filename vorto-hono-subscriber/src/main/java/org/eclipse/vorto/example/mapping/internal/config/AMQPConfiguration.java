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
package org.eclipse.vorto.example.mapping.internal.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.eclipse.vorto.example.mapping.handler.IPayloadHandler;
import org.eclipse.vorto.example.mapping.handler.amqp.AMQPHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {

	@Value(value = "${amqp.username}")
	private String username;
	@Value(value = "${amqp.password}")
	private String password;
	@Value(value = "${amqp.url}")
	private String amqpUrl;

	@Bean
	public IPayloadHandler customHandler() {
		final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(amqpUrl);

		// Pass the username and password.
		connectionFactory.setUserName(username);
		connectionFactory.setPassword(password);
		// Create a pooled connection factory.
		final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		pooledConnectionFactory.setConnectionFactory(connectionFactory);
		pooledConnectionFactory.setMaxConnections(10);
		
		return new AMQPHandler(connectionFactory);
	}
}
