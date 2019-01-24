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
package org.eclipse.vorto.connector.bt.core;

import java.util.Optional;

import org.eclipse.vorto.connector.bt.model.DeviceTelemetryData;
import org.eclipse.vorto.connector.bt.model.RegisteredDeviceId;
import org.eclipse.vorto.connector.bt.model.SourceContext;

public interface Sink {

	/**
	 * Updates the given registered thing with the given payload
	 * @param deviceId
	 * @param payload
	 */
	void sendTelemetryData(RegisteredDeviceId deviceId, DeviceTelemetryData telemetryPayload);
	
	/**
	 * @param technicalDeviceId
	 * @return source
	 */
	Optional<RegisteredDeviceId> getRegisteredDeviceId(SourceContext source);
	
}
