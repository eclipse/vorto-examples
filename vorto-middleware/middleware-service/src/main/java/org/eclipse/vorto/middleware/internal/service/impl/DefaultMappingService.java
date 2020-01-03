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
package org.eclipse.vorto.middleware.internal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.vorto.mapping.engine.MappingEngine;
import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.middleware.internal.service.IMappingService;
import org.eclipse.vorto.middleware.mappings.IMappingConfigDao;
import org.eclipse.vorto.model.ModelId;
import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DefaultMappingService implements IMappingService {

	private static Logger logger = LoggerFactory.getLogger(DefaultMappingService.class);

	private Map<String, IMappingSpecification> cache = new HashMap<>();
	
	@Autowired
	@Qualifier("mappingConfigPersistence")
	private IMappingConfigDao dao;

	public InfomodelValue map(ModelId modelId, Object payload) {
		logger.debug("Mapping device data for model ID " + modelId.getPrettyFormat());

		IMappingSpecification specification = null;
		if (cache.containsKey(modelId.getPrettyFormat())) {
			specification = cache.get(modelId.getPrettyFormat());
		} else {
			specification = dao.get(modelId).get();
		} 
		
		MappingEngine engine = MappingEngine.create(specification);

		return engine.mapSource(payload);
	}
	
	public void removeMappingSpec(ModelId mappingId) {
		this.cache.remove(mappingId.getPrettyFormat());
	}

}
