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
package org.eclipse.vorto.middleware.config;

import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.middleware.service.impl.DefaultMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class MappingSpecsConfiguration {

	@Autowired
	private DefaultMappingService mappingService;
	
	
	@PostConstruct
	public void addMappingSpecs() throws Exception {
		mappingService.addMappingSpec(loadMappingFromFile(new ClassPathResource("specs/org.eclipse.vorto.tutorial_PMSMotor_1.0.0-mappingspec.json").getInputStream()));
		mappingService.addMappingSpec(loadMappingFromFile(new ClassPathResource("specs/vorto.private.somesh_ACMEWaterSensor_1.0.0-mappingspec.json").getInputStream()));
		mappingService.addMappingSpec(loadMappingFromFile(new ClassPathResource("specs/vorto.private.timgrossmann_Plantect_1.0.0-mappingspec.json").getInputStream()));

	}
	
	private IMappingSpecification loadMappingFromFile(InputStream is) {
		return IMappingSpecification.newBuilder().fromInputStream(is).build();
	}
}
