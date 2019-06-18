package org.eclipse.vorto.plugins.generator.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.eclipse.vorto.codegen.utils.ApiGatewayResponse;
import org.eclipse.vorto.codegen.utils.ObjectMapperFactory;
import org.eclipse.vorto.plugins.generator.example.plugins.GeneratorPluginInfoFactory;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeneratorPluginInfoHandler implements RequestStreamHandler {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void handleRequest(InputStream input, OutputStream output, Context context)
      throws IOException {
    ObjectMapper mapper = ObjectMapperFactory.getInstance();

    JsonNode inputJson = mapper.readTree(input);

    String pluginkey = inputJson.get("pathParameters").get("pluginkey").asText();

    
    Object pluginInfo = GeneratorPluginInfoFactory.getInstance().getForPlugin(pluginkey);
    
    if (pluginInfo == null) {
      ApiGatewayResponse response = ApiGatewayResponse.builder().setStatusCode(404).build();
      objectMapper.writeValue(output, response);
    } else {
      ApiGatewayResponse gatewayResponse = ApiGatewayResponse.builder()
          .setStatusCode(200)
          .setRawBody(objectMapper.writeValueAsString(pluginInfo))
          .build();
      
      objectMapper.writeValue(output, gatewayResponse);
    }
   
    
    
  }
}
