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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.middleware.mappings.IMappingConfigDao;
import org.eclipse.vorto.model.ModelId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class MappingSpecsConfiguration implements IMappingConfigDao {

	@Value(value = "${mapping_spec_dir:null}")
	private String mappingSpecDirectory;
	
	private List<IMappingSpecification> mappingSpecifications = new ArrayList<IMappingSpecification>();

	private static final Logger logger = LoggerFactory.getLogger(MappingSpecsConfiguration.class);

	@PostConstruct
	public void addMappingSpecs() throws Exception {
		/**
		 * THESE ARE EXAMPLE MAPPINGS THAT ARE REGISTERED IN THE MIDDLEWARE
		 */	
		mappingSpecifications.add(loadMappingFromFile(
				new ClassPathResource("specs/org.eclipse.vorto.tutorial_PMSMotor_1.0.0-mappingspec.json")
				.getInputStream()));
		
		mappingSpecifications.add(loadMappingFromFile(
				new ClassPathResource("specs/vorto.private.somesh_ACMEWaterSensor_1.0.0-mappingspec.json")
						.getInputStream()));
		mappingSpecifications.add(loadMappingFromFile(
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
						mappingSpecifications.add(specification);
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
	}

	
	private void saveFile(IMappingSpecification specification) {
		File mappingFile = new File(mappingSpecDirectory,specification.getInfoModel().getId().getPrettyFormat().replace(":", "_")+"-mappingspec.json");
		
		/**
		 * Delete existing mapping file before we can save the new mapping file
		 */
		if (mappingFile.exists()) {
			mappingFile.delete();
		} else {
			try {
				mappingFile.createNewFile();
			} catch (IOException e) {
				logger.error("Problem creating mapping file in filesystem.",e);
				throw new RuntimeException("Problem occured when saving mapping specification",e);
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();		
		try {
			byte[] mappingSpecContent = mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(specification);
			FileUtils.writeByteArrayToFile(mappingFile, mappingSpecContent);
		} catch (JsonProcessingException e) {
			logger.error("Could not serialize mapping spec ",e);
			throw new RuntimeException("Problem occured when installing mapping specification",e);
		} catch (IOException e) {
			logger.error("Could not write file filesystem",e);
			throw new RuntimeException("Problem occured when installing mapping specification",e);
		} 
 
		
	}

	@Override
	public Collection<IMappingSpecification> list() {
		return this.mappingSpecifications;
	}

	/**
	 * Does not delete the mapping spec from the mapping directory on the Filesystem. Instead it merely deletes it from the mapping engine
	 * ATTENTION: On next restart of th system, the mapping is installed in the middleware, as it still exists in the mapping directory
	 */
	@Override
	public void remove(ModelId specificationId) {
		for (Iterator<IMappingSpecification> iter = this.mappingSpecifications.iterator();iter.hasNext();) {
			IMappingSpecification spec = iter.next();
			if (spec.getInfoModel().getId().equals(specificationId)) {
				iter.remove();
			}
		}
	}

	@Override
	public Optional<IMappingSpecification> get(ModelId modelId) {
		return this.mappingSpecifications.stream().filter(spec -> spec.getInfoModel().getId().equals(modelId)).findAny();
	}
}
