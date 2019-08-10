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
 * SPDX-License-Identifier: EPL-2.0null
 */
package org.eclipse.vorto.example.mapping.internal.config;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.eclipse.vorto.example.mapping.handler.IPayloadHandler;
import org.eclipse.vorto.example.mapping.handler.amqp.AMQPDittoHandler;
import org.eclipse.vorto.example.mapping.handler.amqp.AMQPNativeHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {

	@Value(value = "${amqp.username:#{null}}")
	private String username;
	@Value(value = "${amqp.password:#{null}}")
	private String password;
	@Value(value = "${amqp.url:#{null}}")
	private String amqpUrl;
	
	@Bean
	@ConditionalOnProperty(name="amqp.url", matchIfMissing=false)
	public ConnectionFactory createConnectionFactory() {
	  final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(amqpUrl);

      // Pass the username and password.
      connectionFactory.setUserName(username);
      connectionFactory.setPassword(password);
      // Create a pooled connection factory.
      final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
      pooledConnectionFactory.setConnectionFactory(connectionFactory);
      pooledConnectionFactory.setMaxConnections(10);
      return connectionFactory;
	}
	
	@Bean
	@ConditionalOnProperty(name="amqp.topic.ditto", matchIfMissing=false)
	public IPayloadHandler dittoAMQPHandler() {
		return new AMQPDittoHandler(createConnectionFactory());
	}
	
	@Bean
	@ConditionalOnProperty(name="amqp.topic.native", matchIfMissing=false)
    public IPayloadHandler nativeAMQPHandler() {
        return new AMQPNativeHandler(createConnectionFactory());
    }
}
