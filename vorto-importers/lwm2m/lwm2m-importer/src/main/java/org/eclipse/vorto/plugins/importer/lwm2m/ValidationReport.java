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
package org.eclipse.vorto.plugins.importer.lwm2m;

import org.eclipse.vorto.model.ModelId;

public class ValidationReport {

	private boolean valid;
	private String message;
	private ModelId modelId;
	
	protected ValidationReport() {
		
	}
	
	public static ValidationReport valid(ModelId modelId, String message) {
		return new ValidationReport(modelId, true, message);
	}
	
	public static ValidationReport invalid(String message) {
		return new ValidationReport(null,false, message);
	}

	private ValidationReport(ModelId modelId, boolean valid, String message) {
		super();
		this.valid = valid;
		this.message = message;
		this.modelId = modelId;
	}

	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public ModelId getModelId() {
		return modelId;
	}

	public void setModelId(ModelId modelId) {
		this.modelId = modelId;
	}
	
	
	
	
}
