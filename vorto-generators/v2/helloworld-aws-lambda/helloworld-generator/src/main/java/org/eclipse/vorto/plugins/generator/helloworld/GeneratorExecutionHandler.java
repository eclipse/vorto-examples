package org.eclipse.vorto.plugins.generator.helloworld;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import org.eclipse.vorto.codegen.api.IGenerationResult;
import org.eclipse.vorto.codegen.api.IVortoCodeGenerator;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.api.VortoCodeGeneratorException;
import org.eclipse.vorto.codegen.utils.ApiGatewayResponse;
import org.eclipse.vorto.codegen.utils.ObjectMapperFactory;
import org.eclipse.vorto.codegen.utils.Utils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.vorto.core.api.model.model.Model;
import org.eclipse.vorto.model.ModelContent;
import org.eclipse.vorto.model.conversion.ModelContentToEcoreConverter;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Lambda Function that invokes a generator based on the plugin key query param
 *
 */
public class GeneratorExecutionHandler implements RequestStreamHandler {
  
    private static final Map<String, IVortoCodeGenerator> generators = new HashMap<>();
    
    static {
      //TODO: add code generator
      //generators.put("helloworld",new HelloworldGenerator());
    }
    
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context)
        throws IOException {
      
      
      ObjectMapper mapper = ObjectMapperFactory.getInstance();
      
      JsonNode inputJson = mapper.readTree(input);
      
      String pluginkey =  inputJson.get("pathParameters").get("pluginkey").asText();

      final IVortoCodeGenerator generator = generators.get(pluginkey);
      
      if (generator == null) {
        ApiGatewayResponse response = ApiGatewayResponse.builder()
            .setStatusCode(400)
            .build();
        objectMapper.writeValue(output, response); 
        return;
      } 
      
      String modelAsString =  inputJson.get("body").asText();
      
      Map<String,String> params = new HashMap<>();
      if (!inputJson.get("queryStringParameters").isNull()) {
        Iterator<String> iter = inputJson.get("queryStringParameters").fieldNames();
        while(iter.hasNext()) {
          String fieldName = iter.next();
          params.put(fieldName, inputJson.get("queryStringParameters").get(fieldName).asText());
        }
      }
      
      ModelContent modelContent = null;
      if (inputJson.get("isBase64Encoded").asBoolean() == true) {
        modelContent = mapper.readValue(com.amazonaws.util.Base64.decode(modelAsString.getBytes()), ModelContent.class);
      } else {
        modelContent = mapper.readValue(modelAsString.getBytes(), ModelContent.class);
      }
     
      
      ModelContentToEcoreConverter converter = new ModelContentToEcoreConverter();
      
      Model converted = converter.convert(modelContent, Optional.empty());
      
      InvocationContext invocationContext = InvocationContext.simpleInvocationContext(params);
      
      InformationModel infomodel = Utils.toInformationModel(converted);
      
      try {
        IGenerationResult generatorResult = generator.generate(infomodel, invocationContext, null);
        ApiGatewayResponse validResponse = createResponse(generatorResult);
  
        OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
        writer.write(objectMapper.writeValueAsString(validResponse));
        writer.close();
      } catch (VortoCodeGeneratorException e) {
        ApiGatewayResponse response = ApiGatewayResponse.builder()
            .setStatusCode(500)
            .build(); 
        objectMapper.writeValue(output, response); 
      }
    }

    private ApiGatewayResponse createResponse(IGenerationResult generatorResult) {
      Map<String, String> headers = new HashMap<>();
      headers.put("Content-Type", "application/octet-stream");
      headers.put("Content-Disposition","attachment; filename=\""+generatorResult.getFileName()+"\"");
      return ApiGatewayResponse.builder()
              .setStatusCode(200)
              .setBinaryBody(generatorResult.getContent())
              .setHeaders(headers)
              .build();
    }
}