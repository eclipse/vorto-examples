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
package org.eclipse.vorto.example.mapping;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableJpaRepositories
public class VortoMiddleware {

	private static final Logger LOG = LoggerFactory.getLogger(VortoMiddleware.class);
	
	@Value(value = "${hono.tenantId}")
	private String tenantId;
	
	@PostConstruct
    private void start() throws Exception {
        LOG.info("Starting Eclipse Hono Listener for tenantId "+tenantId+"...");
    }
	
	public static void main(final String[] args) {
        SpringApplication.run(VortoMiddleware.class, args);
    }
}
