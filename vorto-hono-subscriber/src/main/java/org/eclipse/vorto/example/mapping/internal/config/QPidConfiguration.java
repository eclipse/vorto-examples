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

import javax.annotation.Resource;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;
@Configuration
public class QPidConfiguration {
	
	@Bean
	  public PropertiesFactoryBean qpidProperties() {
	    PropertiesFactoryBean pfb = new PropertiesFactoryBean();
	    pfb.setLocation(new ClassPathResource("qpid.properties"));
	    return pfb;
	  }

	  @Resource(name = "qpidProperties")
	  private java.util.Properties properties;

	  @Bean
	  public JndiTemplate jndiTemplate() {
	    JndiTemplate jndiTemplate = new JndiTemplate();
	    jndiTemplate.setEnvironment(properties);
	    return jndiTemplate;
	  }

	  @Bean
	  public JndiObjectFactoryBean qpidConnectionFactory() {
	    JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
	    jndiObjectFactoryBean.setJndiTemplate(jndiTemplate());
	    jndiObjectFactoryBean.setJndiName("localhost");
	    
	    return jndiObjectFactoryBean;
	  }
}
