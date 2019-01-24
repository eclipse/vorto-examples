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

import java.util.Collection;

import org.eclipse.vorto.connector.bt.model.SourceContext;
import org.eclipse.vorto.connector.bt.model.SynchronizationResult;

public interface SynchronizationService {
	
	/**
	 * Synchronizes the digital twin state of registered Bosch IoT Things with the configured backend gateway platform
	 * @return 
	 */
	Collection<SynchronizationResult> synchronize(SourceContext sinkContext);
}
