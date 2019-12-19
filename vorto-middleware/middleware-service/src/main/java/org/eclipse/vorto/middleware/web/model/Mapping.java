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
package org.eclipse.vorto.middleware.web.model;

import org.eclipse.vorto.model.ModelId;

public class Mapping {

	private boolean installed;
	private ModelId modelId;
	private String description;
	private boolean unresolved;
	
	public Mapping(boolean installed, ModelId modelId, String description, boolean unresolved) {
		super();
		this.installed = installed;
		this.modelId = modelId;
		this.description = description;
		this.unresolved = unresolved;
	}
	
	public Mapping() {
		
	}
	
	public boolean isInstalled() {
		return installed;
	}
	public void setInstalled(boolean installed) {
		this.installed = installed;
	}
	public ModelId getModelId() {
		return modelId;
	}
	public void setModelId(ModelId modelId) {
		this.modelId = modelId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isUnresolved() {
		return unresolved;
	}

	public void setUnresolved(boolean unresolved) {
		this.unresolved = unresolved;
	}
	
	
	
	
}
