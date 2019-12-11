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
package org.eclipse.vorto.middleware.mappings.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.middleware.mappings.IMappingConfigDao;
import org.eclipse.vorto.middleware.service.impl.DefaultMappingService;
import org.eclipse.vorto.model.ModelId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class MappingSpecsConfiguration implements IMappingConfigDao {

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

	/**
	 * Saves the mapping specification in the configured mapping directory as well as caches it in the mapping engine
	 */
	@Override
	public void save(IMappingSpecification specification) {
		saveFile(specification);
		this.mappingService.addMappingSpec(specification);
	}

	
	private void saveFile(IMappingSpecification specification) {
		//TODO: Implement saving the specification in the configured mapping directory
		
	}

	@Override
	public Collection<IMappingSpecification> list() {
		return this.mappingService.list();
	}

	/**
	 * Does not delete the mapping spec from the mapping directory on the Filesystem. Instead it merely deletes it from the mapping engine
	 * ATTENTION: On next restart of th system, the mapping is installed in the middleware, as it still exists in the mapping directory
	 */
	@Override
	public void remove(ModelId specificationId) {
		this.mappingService.removeMappingSpec(specificationId);
	}
}
