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
package org.eclipse.vorto.example.mapping.handler.logger;

import org.eclipse.vorto.example.mapping.handler.Context;
import org.eclipse.vorto.example.mapping.handler.IPayloadHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Simple Handler that logs the normalized device payload to configured logging
 * appender.
 *
 */
@Component
public class LoggerPayloadHandler implements IPayloadHandler {

	private static final Logger logger = LoggerFactory.getLogger(LoggerPayloadHandler.class);

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public void handlePayload(JsonObject normalizedPayload, Context context) {
		logger.info("--> Normalized json for device ID " + context.getDeviceId());
		logger.info(System.lineSeparator() + gson.toJson(normalizedPayload));
	}

}
