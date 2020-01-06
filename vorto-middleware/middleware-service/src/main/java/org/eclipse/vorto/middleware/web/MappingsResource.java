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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.vorto.mapping.engine.model.spec.MappingSpecification;
import org.eclipse.vorto.middleware.VortoMiddleware;
import org.eclipse.vorto.middleware.internal.service.IVortoRepository;
import org.eclipse.vorto.middleware.mappings.IMappingConfigDao;
import org.eclipse.vorto.middleware.web.model.Mapping;
import org.eclipse.vorto.model.ModelId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/mappings")
public class MappingsResource {

	private static final Logger LOG = LoggerFactory.getLogger(VortoMiddleware.class);

	@Autowired
	@Qualifier("mappingConfigPersistence")
	private IMappingConfigDao mappingConfigDao;
	
	@Autowired
	private IVortoRepository repository = null;

	@RequestMapping(value = "/{modelId}/resolve", method = RequestMethod.GET)
	public Mapping resolveMapping(@PathVariable String modelId, @RequestParam String description) {
		Optional<MappingSpecification> spec = repository.getById(modelId);
		
		if (spec.isPresent()) {
			return new Mapping(false, spec.get().getInfoModel().getId(), spec.get().getInfoModel().getDescription(),false);
		} else {
			return new Mapping(false, ModelId.fromPrettyFormat(modelId), description,true);
		}
	}
	
	@RequestMapping(value = "/discovered",method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public Collection<Mapping> getDiscoveredPossibleMappings() {
		return this.repository.discoverUserInfomodels(
					getInstalledMappings().stream().map(m -> m.getModelId()).collect(Collectors.toList()),SecurityContextHolder.getContext().getAuthentication().getName())
					.stream().map(modelInfo ->new Mapping(false,modelInfo.getId(),modelInfo.getDescription(),true)).collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/installed",method = RequestMethod.GET)
	public Collection<Mapping> getInstalledMappings() {
		List<Mapping> mappings = new ArrayList<>();
		
		/**
		 * First add all mappings that are already installed in the system
		 */
		mappings.addAll(mappingConfigDao.list().stream()
				.map(config -> new Mapping(true, config.getInfoModel().getId(), config.getInfoModel().getDescription(),false))
				.collect(Collectors.toList()));
				
		return mappings;
	}

	/**	 
	 * Installs the mapping specification for the given Vorto Information Model
	 * @param modelId
	 */
	@RequestMapping(value = "/{modelId}/install", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('USER')")
	public void installMapping(@PathVariable String modelId) {
		LOG.info("Installing " + modelId + "...");
		Optional<MappingSpecification> spec = this.repository.getById(modelId);
		if (!spec.isPresent()) {
			throw new IllegalArgumentException("Could not find mapping specification with the provided ID.");
		}
		
		this.mappingConfigDao.save(spec.get());
		
	}

	/**
	 * FIXME: needs access to Vorto Repository to get mapping spec
	 * 
	 * @param modelId
	 */
	@RequestMapping(value = "/{modelId}/uninstall", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('USER')")
	public void uninstallMapping(@PathVariable String modelId) {
		LOG.info("Uninstalling " + modelId + "...");
		this.mappingConfigDao.remove(ModelId.fromPrettyFormat(modelId));
	}
}
