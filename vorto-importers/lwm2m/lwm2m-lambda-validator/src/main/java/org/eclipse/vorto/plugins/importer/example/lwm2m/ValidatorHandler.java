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
package org.eclipse.vorto.plugins.importer.example.lwm2m;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.eclipse.vorto.plugin.utils.ApiGatewayRequest;
import org.eclipse.vorto.plugin.utils.ApiGatewayResponse;
import org.eclipse.vorto.plugins.importer.example.lwm2m.LwM2MImporter;
import org.eclipse.vorto.plugins.importer.example.lwm2m.ValidationReport;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidatorHandler implements RequestStreamHandler {

	static Logger logger = Logger.getLogger(ValidatorHandler.class);

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {

		ApiGatewayRequest request = ApiGatewayRequest.createFromJson(inputStream);
		
		InputStream fis = new ByteArrayInputStream(request.getInput());

		LwM2MImporter importer = new LwM2MImporter();

		ValidationReport report = importer.validate(fis);

		ObjectMapper mapper = new ObjectMapper();
		String responseContent = mapper.writeValueAsString(report);
		
		ApiGatewayResponse response = ApiGatewayResponse.builder()
				.setRawBody(responseContent).build();

		IOUtils.write(mapper.writeValueAsString(response), outputStream, StandardCharsets.UTF_8.name());

	}

}
