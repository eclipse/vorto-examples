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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.vorto.connector.bt.model.SourceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SourceTracker {
	
	private static Logger logger = LoggerFactory.getLogger(SourceTracker.class);
	
	private Collection<SourceContext> trackedSources = Collections.synchronizedList(new ArrayList<SourceContext>());

	public void addToTracker(SourceContext newSource) {
		logger.info("Tracking camera [" + newSource.getCameraIPAddress() + "]");
		trackedSources.add(newSource);
	}

	public Collection<SourceContext> getTrackedSources() {
		return trackedSources;
	}
}
