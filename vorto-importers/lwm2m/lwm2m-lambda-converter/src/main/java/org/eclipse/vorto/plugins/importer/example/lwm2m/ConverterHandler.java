/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional information regarding copyright
 * ownership.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which is available at https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.plugins.importer.example.lwm2m;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import org.apache.log4j.Logger;
import org.eclipse.vorto.plugin.utils.ApiGatewayRequest;
import org.eclipse.vorto.plugin.utils.ApiGatewayResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterHandler implements RequestStreamHandler {

  static Logger logger = Logger.getLogger(ConverterHandler.class);

  static ObjectMapper mapper = new ObjectMapper();

  @Override
  public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
      throws IOException {

    ApiGatewayRequest request = ApiGatewayRequest.createFromJson(inputStream);

    LwM2MImporter importer = new LwM2MImporter();

    byte[] responseContent = importer.convert(new ByteArrayInputStream(request.getInput()));

    ApiGatewayResponse response =
        ApiGatewayResponse.builder().addHeader("Content-type", "application/octet-stream")
            .addHeader("content-disposition", "attachment; filename=lwm2m.zip")
            .setBinaryBody(responseContent).build();

    OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
    writer.write(mapper.writeValueAsString(response));
    writer.close();
  }

}
