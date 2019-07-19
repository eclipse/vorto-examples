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
package org.eclipse.vorto.example.mapping.internal;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Base64;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.eclipse.vorto.example.mapping.handler.Context;
import org.eclipse.vorto.example.mapping.handler.IPayloadHandler;
import org.eclipse.vorto.mapping.engine.model.binary.BinaryData;
import org.eclipse.vorto.model.ModelId;
import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


/**
 * AMQP Queue listener for any telemetry data coming in for the given tenant
 *
 */
public class HonoAMQPListener implements MessageListener {

  private static final Logger logger = LoggerFactory.getLogger(HonoAMQPListener.class);

  @Value(value = "${hono.tenantId}")
  private String tenantId;

  @Autowired
  private MappingService mappingService;
  
  @Autowired
  private List<IPayloadHandler> payloadHandlers;
  
  private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
  
  private static final String BASE_64_REGEX = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$";
  private static final String HEADER_DEVICE_ID = "device_id";
  private static final String HEADER_VORTO_ID = "vorto";
  private static final String HEADER_CONTENT_TYPE = "JMS_AMQP_CONTENT_TYPE";
  

  @Override
  public void onMessage(Message message) {
    logger.debug("Received AMQP message from Hono ...");
    try {
	  final String deviceId = message.getStringProperty(HEADER_DEVICE_ID);
	  final String modelId = message.getStringProperty(HEADER_VORTO_ID);
      final String contentType = message.getStringProperty(HEADER_CONTENT_TYPE);
      
      final String textMessage = ((TextMessage) message).getText();
      
      if (modelId == null) {
        logger.error("No vorto model id found in message. Please add a field 'vorto' as a custom field during device registration.");
        return;
      }
    	
      Object payload = null;
      if (message instanceof BytesMessage) {
        BytesMessage byteMessage = (BytesMessage) message;
        BinaryData binary = convertBytesMessageToString(byteMessage);
        
        try {
          payload = gson.fromJson(new String(binary.getData(),StandardCharsets.UTF_8), Object.class);
        } catch(JsonSyntaxException invalidJson) {
          payload = binary;
        }
      } else if (message instanceof TextMessage) {
    	if (Pattern.matches(BASE_64_REGEX, (CharSequence) textMessage)) {
    	/* Handle Base64 encoded payload */
	      byte[] bytePayload = Base64.getDecoder().decode(textMessage);
    	
    	  if (contentType.equals("application/json")) {
            payload = gson.fromJson(new String(bytePayload), Object.class);
    	  }
    	  
        } else {
    	  payload = gson.fromJson(textMessage, Object.class);  
        }
    	
      } else {
        logger.warn("Unsupported message format for incoming hono message :" + message.getClass());
      }

      InfomodelValue normalizedData = mappingService
          .map(ModelId.fromPrettyFormat(modelId), payload);
      
      final String json = gson.toJson(normalizedData.serialize());
      JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
      
      payloadHandlers.stream().forEach(handler -> {
        logger.info("Invoking payload handler " + handler.getClass().getName());
        try {
          handler.handlePayload(jsonObject, new Context(deviceId,normalizedData));
        } catch(Throwable t) {
          logger.error("Problem during handler invocation", t);
        }
      });
      
    } catch (JMSException e) {
      logger.error("Problem with consuming AMQP message", e);
    }
  }

  private BinaryData convertBytesMessageToString(BytesMessage message) throws JMSException {
    byte[] byteData = null;
    byteData = new byte[(int) message.getBodyLength()];
    message.readBytes(byteData);
    message.reset();
    return new BinaryData(byteData);
  }
}
