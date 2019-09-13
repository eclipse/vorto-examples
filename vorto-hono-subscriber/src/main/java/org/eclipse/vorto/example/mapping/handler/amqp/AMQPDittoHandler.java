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
package org.eclipse.vorto.example.mapping.handler.amqp;

import javax.jms.ConnectionFactory;

import org.eclipse.vorto.example.mapping.handler.Context;
import org.eclipse.vorto.example.mapping.handler.IPayloadHandler;
import org.eclipse.vorto.example.mapping.internal.deserializer.MimeType;
import org.eclipse.vorto.mapping.targetplatform.ditto.TwinPayloadFactory;
import org.eclipse.vorto.model.runtime.FunctionblockValue;
import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class AMQPDittoHandler implements IPayloadHandler {

  private static final Logger logger = LoggerFactory.getLogger(AMQPDittoHandler.class);

  private JmsTemplate jmsTemplate;

  private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
  
  @Value(value = "${amqp.topic.ditto}")
  private String topic;

  public AMQPDittoHandler(ConnectionFactory connectionFactory) {
    this.jmsTemplate = new JmsTemplate(connectionFactory);
    this.jmsTemplate.setExplicitQosEnabled(true);
    this.jmsTemplate.setTimeToLive(1000 * 60);
  }

  @Override
  public void handlePayload(InfomodelValue infomodelValue, Context context) {
    logger.debug("Publishing following ditto command to AMQP Broker:");
    if (context.getMimeType() == MimeType.ECLIPSE_DITTO) {
      logger.debug((String) context.getRawPayload());
      jmsTemplate.convertAndSend(topic, (String) context.getRawPayload());
    } else {
      for (String fbProperty : infomodelValue.getProperties().keySet()) {
        FunctionblockValue value = infomodelValue.get(fbProperty); 
        JsonObject updateCommand = TwinPayloadFactory.toDittoProtocol(value, fbProperty,
            context.getNamespace(), context.getDeviceId());
        String updateCommandJson =  gson.toJson(updateCommand);
        logger.debug(updateCommandJson);
        jmsTemplate.convertAndSend(topic, updateCommandJson);
      }
    }



  }
}
