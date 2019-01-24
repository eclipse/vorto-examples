/**
 * Copyright (c) 2019 Contributors to the Eclipse Foundation
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
package org.eclipse.vorto.connector.bt.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.vorto.connector.bt.service.MappingStore;
import org.eclipse.vorto.mapping.engine.MappingEngine;
import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.model.ModelId;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class CachedMappingStore implements MappingStore {

	private static Map<ModelId, MappingEngine> MAPPING_ENGINE_CACHE = new HashMap<ModelId, MappingEngine>();

	public static void clearCache() {
		MAPPING_ENGINE_CACHE.clear();
	}

	public static void removeCacheItem(String modelId) {
		MAPPING_ENGINE_CACHE.remove(ModelId.fromPrettyFormat(modelId));
	}
	
	public IMappingSpecification getMappingSpecification(ModelId modelId) {
		try {
			return IMappingSpecification.newBuilder().fromInputStream(new ClassPathResource("mappings/"+modelId.getName()+".json").getInputStream()).build();
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot find mapping spec for model "+modelId);
		}
	}

	@Override
	public MappingEngine createEngine(IMappingSpecification spec) {
		MappingEngine engine = MAPPING_ENGINE_CACHE.get(spec.getInfoModel().getId());

		if (engine == null) {
			engine = MappingEngine.create(spec);
			MAPPING_ENGINE_CACHE.put(spec.getInfoModel().getId(), engine);
		}

		return engine;
	}
}
