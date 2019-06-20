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
package org.eclipse.vorto.importer.lwm2m;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.eclipse.vorto.plugin.utils.ApiGatewayRequest;
import org.eclipse.vorto.plugin.utils.ApiGatewayResponse;
import org.eclipse.vorto.plugins.importer.lwm2m.ModelImporterIPSO;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterHandler implements RequestStreamHandler {

	static Logger logger = Logger.getLogger(ConverterHandler.class);

	static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {

		ApiGatewayRequest request = ApiGatewayRequest.createFromJson(inputStream);

		ModelImporterIPSO importer = new ModelImporterIPSO();

		String responseContent = importer.convert(new ByteArrayInputStream(request.getInput()));

		ApiGatewayResponse response = ApiGatewayResponse.builder()
				.addHeader("Content-type", "application/octet-stream")
				.addHeader("content-disposition", "attachment; filename=lwm2m.zip")
				.setBinaryBody(responseContent.getBytes()).build();

		IOUtils.write(mapper.writeValueAsString(response), outputStream, StandardCharsets.UTF_8.name());

	}

}
