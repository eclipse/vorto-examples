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
import javax.jms.JMSException;
import javax.jms.Message;
import org.eclipse.vorto.example.mapping.handler.Context;
import org.eclipse.vorto.example.mapping.handler.IPayloadHandler;
import org.eclipse.vorto.example.mapping.internal.deserializer.MimeType;
import org.eclipse.vorto.model.runtime.FunctionblockValue;
import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AMQPNativeHandler implements IPayloadHandler {

  private static final Logger logger = LoggerFactory.getLogger(AMQPNativeHandler.class);

  private JmsTemplate jmsTemplate;

  private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

  @Value(value = "${amqp.topic.native}")
  private String topic;

  public AMQPNativeHandler(ConnectionFactory connectionFactory) {
    this.jmsTemplate = new JmsTemplate(connectionFactory);
  }

  @Override
  public void handlePayload(InfomodelValue infomodelValue, Context context) {
    if (context.getMimeType() == MimeType.ECLIPSE_DITTO) {      
      return;
    }
    
    logger.debug("Publishing Vorto payload to telemetry/vorto topic.");
    for (String fbProperty : infomodelValue.getProperties().keySet()) {
      FunctionblockValue value = infomodelValue.get(fbProperty); 
      String updateCommandJson =  gson.toJson(value.serialize());
     
      jmsTemplate.convertAndSend(topic, updateCommandJson, new MessagePostProcessor() {
        
        @Override
        public Message postProcessMessage(Message message) throws JMSException {
          logger.debug("Adding additional headers to message");
          message.setStringProperty("device_id", context.getDeviceId());
          message.setStringProperty("vorto_model_id",value.getMeta().getId().getPrettyFormat());
          message.setStringProperty("vorto_model_name", fbProperty);
          return message;
        }
      }); 
    }
  }
}
