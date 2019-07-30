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

import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

@Configuration
public class QPidConfiguration {

	@Value(value = "${hono.tenantId}")
	private String tenantId;

	@Value(value = "${hono.password}")
	private String password;

	@Bean
	public PropertiesFactoryBean qpidProperties() throws Exception {
		PropertiesFactoryBean pfb = new PropertiesFactoryBean();
		String qpidConfiguration = IOUtils.toString(new ClassPathResource("qpid.properties").getInputStream());
		Properties props = new Properties();
		props.load(IOUtils.toInputStream(qpidConfiguration));
		pfb.setProperties(props);
		return pfb;
	}

	@Resource(name = "qpidProperties")
	private java.util.Properties properties;

	@Bean
	public JndiTemplate jndiTemplate() {
		JndiTemplate jndiTemplate = new JndiTemplate();
		String qpidConfiguration = (String)properties.get("connectionfactory.hono");
		qpidConfiguration = qpidConfiguration.replace("${hono.tenantId}", tenantId);
		qpidConfiguration = qpidConfiguration.replace("${hono.password}", password);
		properties.put("connectionfactory.hono",qpidConfiguration);
		jndiTemplate.setEnvironment(properties);
		return jndiTemplate;
	}

	@Bean
	public JndiObjectFactoryBean honoConnectionFactory() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiTemplate(jndiTemplate());
		jndiObjectFactoryBean.setJndiName("hono");
		return jndiObjectFactoryBean;
	}

}
