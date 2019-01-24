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
package org.eclipse.vorto.connector.bt.service;

import org.eclipse.vorto.mapping.engine.MappingEngine;
import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.model.ModelId;

public interface MappingStore {
	
	/**
	 * loads the mapping engine for the given Vorto Information model ID
	 * @param infoModelId
	 * @param platformKey
	 * @return
	 */
	IMappingSpecification getMappingSpecification(ModelId infoModelId);
	
	MappingEngine createEngine(IMappingSpecification spec);
	
}
