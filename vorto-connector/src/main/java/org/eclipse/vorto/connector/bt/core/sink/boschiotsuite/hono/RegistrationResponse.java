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
package org.eclipse.vorto.connector.bt.core.sink.boschiotsuite.hono;

public class RegistrationResponse {
	private boolean created;
	private String location;

	public static RegistrationResponse created(String location) {
		return new RegistrationResponse(true, location);
	}
	
	public static RegistrationResponse notCreated() {
		return new RegistrationResponse(false, null);
	}
	
	public static RegistrationResponse alreadyExists() {
		return new RegistrationResponse(true, null);
	}
	
	private RegistrationResponse(boolean created, String location) {
		this.created = created;
		this.location = location;
	}
	
	public boolean isCreated() {
		return created;
	}

	public void setCreated(boolean created) {
		this.created = created;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
