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
package org.eclipse.vorto.middleware.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.eclipse.vorto.mapping.engine.MappingEngine;
import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.middleware.service.IMappingService;
import org.eclipse.vorto.model.ModelId;
import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class DefaultMappingService implements IMappingService {

	private static Logger logger = LoggerFactory.getLogger(DefaultMappingService.class);

	private Map<String, IMappingSpecification> cache = new HashMap<>();

	public InfomodelValue map(ModelId modelId, Object payload) {
		logger.debug("Mapping device data for model ID " + modelId.getPrettyFormat());

		MappingEngine engine = MappingEngine.create(cache.get(modelId.getPrettyFormat()));

		return engine.mapSource(payload);
	}

	@Override
	public Collection<IMappingSpecification> listMappings() {
		return cache.values();
	}

	@PostConstruct
	private void loadMappingSpecifications() throws Exception {
		File[] mappingFiles = new ClassPathResource("specs").getFile().listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".json");
			}
		});

		for (File file : mappingFiles) {
			IMappingSpecification spec = loadMappingFromFile(file);
			this.cache.put(spec.getInfoModel().getId().getPrettyFormat(), spec);
		}
	}
	
	private IMappingSpecification loadMappingFromFile(File file) {
		try {
			return IMappingSpecification.newBuilder().fromInputStream(new FileInputStream(file)).build();
		} catch (IOException e) {
			logger.error("Problem reading mapping specification from classpath", e);
			return null;
		}
	}

	@Override
	public Optional<IMappingSpecification> getMappingForModelId(ModelId modelId) {
		return Optional.of(cache.get(modelId.getPrettyFormat()));

	}

}
