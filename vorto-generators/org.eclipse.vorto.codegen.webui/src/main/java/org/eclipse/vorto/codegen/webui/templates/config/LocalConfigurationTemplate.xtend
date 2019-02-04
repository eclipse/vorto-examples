/**
 * Copyright (c) 2015-2016 Bosch Software Innovations GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * The Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 * Bosch Software Innovations GmbH - Please refer to git log
 */
package org.eclipse.vorto.codegen.webui.templates.config

import org.eclipse.vorto.codegen.api.IFileTemplate
import org.eclipse.vorto.codegen.api.InvocationContext
import org.eclipse.vorto.codegen.webui.templates.TemplateUtils
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel

class LocalConfigurationTemplate implements IFileTemplate<InformationModel> {
	
	override getFileName(InformationModel context) {
		'''LocalConfiguration.java'''
	}
	
	override getPath(InformationModel context) {
		'''«TemplateUtils.getBaseApplicationPath(context)»/config'''
	}
	
	override getContent(InformationModel element, InvocationContext context) {
		'''
		package com.example.iot.«element.name.toLowerCase».config;
		
		import java.util.concurrent.TimeUnit;
		
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.beans.factory.annotation.Value;
		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.Configuration;
		import org.springframework.security.oauth2.client.OAuth2ClientContext;
		import com.example.iot.«element.name.toLowerCase».service.DataService;
		
		«IF context.configurationProperties.getOrDefault("boschcloud","false").equalsIgnoreCase("true")»
		import com.bosch.iot.things.client.ThingsClientFactory;
		import com.bosch.iot.things.client.configuration.CommonConfiguration;
		import com.bosch.iot.things.client.configuration.PublicKeyAuthenticationConfiguration;
		import com.bosch.iot.things.client.messaging.MessagingProviders;
		import com.bosch.iot.things.client.messaging.ThingsWsMessagingProviderConfiguration;
		import com.bosch.iot.things.clientapi.ThingsClient;
		import com.bosch.iot.things.clientapi.twin.Twin;
		import com.example.iot.«element.name.toLowerCase».service.bosch.BoschThingsDataService;
		import com.example.iot.«element.name.toLowerCase».service.bosch.ThingClient;
		«ELSE»
		import com.example.iot.«element.name.toLowerCase».service.sample.SampleDataService;
		«ENDIF»
				
		@Configuration
		public class LocalConfiguration {
			
			«IF context.configurationProperties.getOrDefault("boschcloud","false").equalsIgnoreCase("true")»
			@Value("${bosch.things.endpointUrl}")
			private String thingEndpointUrl;
			
			@Value("${bosch.things.apiToken}")
			private String thingApiToken;
				
			@Value("${bosch.things.solutionid}")
			private String thingsSolutionId;
			
			@Value("${bosch.things.keystore.password}")
			private String keystorePassword;
			
			@Value("${bosch.things.alias}")
			private String alias;
			
			@Value("${bosch.things.alias.password}")
			private String aliasPassword;
			
			@Value("${bosch.things.keystoreLocation}")
			private String keystoreLocation;
			
			@Value("${bosch.things.wsEndpointUrl}")
			private String wsEndpointUrl;
			
			@Autowired
			private OAuth2ClientContext oauth2context;
			
			private static final String WEB_APP_ID = "mywebapp";
			
			@Bean
			public DataService dataService() throws Exception {
				return new BoschThingsDataService(thingClient(),getThingsWSClient());
			}
				
			@Bean
			public ThingClient thingClient() {
				return ThingClient.newBuilder()
						.withEndpointUrl(thingEndpointUrl)
						.withApiToken(thingApiToken)
						.withOAuth2ClientContext(oauth2context)
						.build();
			}
				
			@Bean
			public Twin getThingsWSClient() throws IOException {
				ThingsClient client = ThingsClientFactory.newInstance(createTwinConfiguration(thingsSolutionId+":"+WEB_APP_ID));
				
				Twin twin = client.twin();
			
			    try {
			        // and start consuming events from Things Service
			            twin.startConsumption().get(10, TimeUnit.SECONDS);
			    } catch (InterruptedException | ExecutionException | TimeoutException e) {
			            throw new IllegalStateException("Error creating Things Client.", e);
			    }
			        
				return twin;
			}
				
			private CommonConfiguration createTwinConfiguration(final String clientId) throws IOException {
			
				final PublicKeyAuthenticationConfiguration publicKeyAuthenticationConfiguration =
			                PublicKeyAuthenticationConfiguration.newBuilder()
			                        .clientId(clientId)
			                        .keyStoreLocation(new ClassPathResource(this.keystoreLocation).getURL())
			                        .keyStorePassword(keystorePassword)
			                        .alias(this.alias)
			                        .aliasPassword(this.aliasPassword)
			                        .build();
			
				final ThingsWsMessagingProviderConfiguration<?> thingsWsMessagingProviderConfiguration = MessagingProviders
			                .thingsWebsocketProviderBuilder()
			                .authenticationConfiguration(publicKeyAuthenticationConfiguration)
			                .endpoint(this.wsEndpointUrl)
			                .build();
			
			    final CommonConfiguration.OptionalConfigurationStep configuration =
			                ThingsClientFactory.configurationBuilder()
			                        .apiToken(this.thingApiToken)
			                        .providerConfiguration(thingsWsMessagingProviderConfiguration);
			
			    return configuration.build();
			}
			«ELSE»
			@Bean
			public DataService dataService() {
				return new SampleDataService();
			}
			«ENDIF»
		}
		'''
	}
	
}
