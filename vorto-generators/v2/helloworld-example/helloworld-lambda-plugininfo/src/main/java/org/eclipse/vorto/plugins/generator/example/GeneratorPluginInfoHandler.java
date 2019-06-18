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
package org.eclipse.vorto.plugins.generator.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;
import org.eclipse.vorto.plugin.generator.GeneratorPluginInfo;
import org.eclipse.vorto.plugin.utils.ApiGatewayRequest;
import org.eclipse.vorto.plugin.utils.ApiGatewayResponse;
import org.eclipse.vorto.plugins.generator.example.plugins.GeneratorPluginInfoFactory;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeneratorPluginInfoHandler implements RequestStreamHandler {

  private static final String PLUGIN_KEY = "pluginkey";
  
  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void handleRequest(InputStream input, OutputStream output, Context context)
      throws IOException {

    ApiGatewayRequest request = ApiGatewayRequest.createFromJson(input);
       
    Optional<GeneratorPluginInfo> pluginInfo = GeneratorPluginInfoFactory.getInstance().getForPlugin(request.getPathParam(PLUGIN_KEY));
    
    if (!pluginInfo.isPresent()) {
      ApiGatewayResponse response = ApiGatewayResponse.builder().setStatusCode(404).build();
      objectMapper.writeValue(output, response);
    } else {
      ApiGatewayResponse gatewayResponse = ApiGatewayResponse.builder()
          .setStatusCode(200)
          .setRawBody(objectMapper.writeValueAsString(pluginInfo.get()))
          .build();
      
      objectMapper.writeValue(output, gatewayResponse);
    }
   
    
    
  }
}
