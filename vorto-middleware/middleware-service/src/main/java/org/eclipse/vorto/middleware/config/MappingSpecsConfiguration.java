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

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.middleware.internal.service.impl.DefaultMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class MappingSpecsConfiguration {

	@Autowired
	private DefaultMappingService mappingService;

	@Value(value = "${mapping_spec_dir:null}")
	private String mappingSpecDirectory;

	private static final Logger logger = LoggerFactory.getLogger(MappingSpecsConfiguration.class);

	@PostConstruct
	public void addMappingSpecs() throws Exception {
		mappingService.addMappingSpec(loadMappingFromFile(
				new ClassPathResource("specs/org.eclipse.vorto.tutorial_PMSMotor_1.0.0-mappingspec.json")
						.getInputStream()));
		mappingService.addMappingSpec(loadMappingFromFile(
				new ClassPathResource("specs/vorto.private.somesh_ACMEWaterSensor_1.0.0-mappingspec.json")
						.getInputStream()));
		mappingService.addMappingSpec(loadMappingFromFile(
				new ClassPathResource("specs/vorto.private.timgrossmann_Plantect_1.0.0-mappingspec.json")
						.getInputStream()));

		if (mappingSpecDirectory != null) {
			File externalDirectory = new File(mappingSpecDirectory);
			if (!externalDirectory.exists()) {
				logger.warn("The provided mapping spec directory " + externalDirectory.getPath() + " does not exist");
			} else {
				File[] mappingSpecFiles = externalDirectory.listFiles(new FilenameFilter() {

					@Override
					public boolean accept(File dir, String name) {
						return name.endsWith(".json");
					}
				});

				logger.info("Found " + mappingSpecFiles.length + " mapping specifications in mapping spec directory.");
				for (File specFile : mappingSpecFiles) {
					logger.info(
							"Configuring middleware with mapping file " + specFile.getName());
					try {
						IMappingSpecification specification = loadMappingFromFile(
								new FileInputStream(specFile));
						logger.info("Added mapping successfully.");
						mappingService.addMappingSpec(specification);
					} catch (Throwable ex) {
						logger.error("Mapping file could not be loaded", ex);
					}
				}
			}
		}
	}

	private IMappingSpecification loadMappingFromFile(InputStream is) {
		return IMappingSpecification.newBuilder().fromInputStream(is).build();
	}
}
