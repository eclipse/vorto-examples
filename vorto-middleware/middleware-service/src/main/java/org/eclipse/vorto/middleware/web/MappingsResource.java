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
package org.eclipse.vorto.middleware.web;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.mapping.engine.model.spec.MappingSpecification;
import org.eclipse.vorto.middleware.VortoMiddleware;
import org.eclipse.vorto.middleware.mappings.IMappingConfigDao;
import org.eclipse.vorto.middleware.mappings.MappingConfig;
import org.eclipse.vorto.middleware.mappings.impl.MappingSpecsConfiguration;
import org.eclipse.vorto.middleware.plugins.IPlugin;
import org.eclipse.vorto.middleware.web.model.Mapping;
import org.eclipse.vorto.middleware.web.model.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;

@RestController
@RequestMapping(value = "/api/v1/mappings")
public class MappingsResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(VortoMiddleware.class);

	
	@Autowired
	private IMappingConfigDao mappingConfigDao;
	
	private Collection<Mapping> mappingList = Collections.EMPTY_LIST;
	
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Mapping> getMappings() {
		if(mappingList.isEmpty()) {
		mappingList = mappingConfigDao.list().stream().map(config -> new Mapping(true,config.getInfoModel().getId(),config.getInfoModel().getDescription())).collect(Collectors.toList());
		}
		return mappingList;
	}
	
	/**
	 * FIXME: needs access to Vorto Repository to get mapping spec
	 * @param modelId
	 */
	@RequestMapping(value = "/{modelId}/install", method = RequestMethod.PUT)
	public void installMapping(@PathVariable String modelId) {
        LOG.info("Received "+ modelId +"...");     
       mappingList.stream().filter(x-> x.getModelId().toString().equals(modelId)).forEach(x -> x.setInstalled(true));
       
}
	
	/**
	 * FIXME: needs access to Vorto Repository to get mapping spec
	 * @param modelId
	 */
	@RequestMapping(value = "/{modelId}/uninstall", method = RequestMethod.PUT)
	public void uninstallMapping(@PathVariable String modelId) {
       LOG.info("Received "+ modelId +"...");     
       mappingList.stream().filter(x-> x.getModelId().toString().equals(modelId)).forEach(x -> x.setInstalled(false));
	}
	}

