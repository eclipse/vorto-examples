/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional information regarding copyright
 * ownership.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which is available at https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.middleware.mappings;

import java.util.Collection;

import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.model.ModelId;

public interface IMappingConfigDao {

	/**
	 * Saves the given mapping specification and configures the middleware with it
	 * @param specification
	 */
	void save(IMappingSpecification specification);
	
	/**
	 * Removes the mapping specification from the middleware
	 * @param specificationId
	 */
	void remove(ModelId specificationId);
	
	/**
	 * Gives a list of all installed mapping specifications, currently available.
	 * @return
	 */
	Collection<IMappingSpecification> list();
}
