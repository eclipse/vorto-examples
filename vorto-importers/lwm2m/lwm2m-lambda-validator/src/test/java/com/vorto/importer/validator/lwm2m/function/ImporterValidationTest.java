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
package com.vorto.importer.validator.lwm2m.function;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.eclipse.vorto.importer.lwm2m.ValidatorHandler;
import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ImporterValidationTest {

	@Test
	public void test() throws IOException {

		ValidatorHandler importerValidation = new ValidatorHandler();
		Context context = createContext();

		String filename = "3328_1_1.xml";
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classloader.getResourceAsStream(filename);
		OutputStream outputStream = new ByteArrayOutputStream();

		importerValidation.handleRequest(inputStream, outputStream, context);

		ObjectMapper om = new ObjectMapper();
		JsonNode node = om.readTree(outputStream.toString());
		JsonNode body = om.readTree(node.get("body").asText());

		Assert.assertEquals(body.get("message").asText(), "File is a valid LwM2M description");

	}

	private Context createContext() {
		TestContext ctx = new TestContext();

		// TODO: customize your context here if needed.
		ctx.setFunctionName("Your Function Name");

		return ctx;
	}

}
