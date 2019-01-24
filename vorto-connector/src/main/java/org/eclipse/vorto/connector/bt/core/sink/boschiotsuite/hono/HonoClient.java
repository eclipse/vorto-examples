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

import java.util.concurrent.CompletableFuture;

public interface HonoClient {
	
	CompletableFuture<HubRegistration> getHubRegistration(String tenant, String deviceId);
		
	static HonoClientBuilder newBuilder() {
		return new HonoClientBuilder();
	}
}
