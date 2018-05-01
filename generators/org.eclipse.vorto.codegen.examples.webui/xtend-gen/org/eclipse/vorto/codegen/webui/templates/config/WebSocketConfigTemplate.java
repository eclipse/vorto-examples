/**
 * Copyright (c) 2015-2016 Bosch Software Innovations GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * The Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * Contributors:
 * Bosch Software Innovations GmbH - Please refer to git log
 */
package org.eclipse.vorto.codegen.webui.templates.config;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.TemplateUtils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class WebSocketConfigTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("WebSocketConfig.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _baseApplicationPath = TemplateUtils.getBaseApplicationPath(context);
    _builder.append(_baseApplicationPath, "");
    _builder.append("/config");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.example.iot.");
    String _name = element.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append(".config;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import org.springframework.context.annotation.Configuration;");
    _builder.newLine();
    _builder.append("import org.springframework.messaging.simp.config.MessageBrokerRegistry;");
    _builder.newLine();
    _builder.append("import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;");
    _builder.newLine();
    _builder.append("import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;");
    _builder.newLine();
    _builder.append("import org.springframework.web.socket.config.annotation.StompEndpointRegistry;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Configuration");
    _builder.newLine();
    _builder.append("@EnableWebSocketMessageBroker");
    _builder.newLine();
    _builder.append("public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static final String ENDPOINT = \"/");
    String _name_1 = element.getName();
    String _lowerCase_1 = _name_1.toLowerCase();
    _builder.append(_lowerCase_1, "\t");
    _builder.append("endpoint\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void registerStompEndpoints(StompEndpointRegistry registry)  {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("registry.addEndpoint(ENDPOINT).withSockJS();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void configureMessageBroker(MessageBrokerRegistry registry) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("registry.setApplicationDestinationPrefixes(\"/");
    String _name_2 = element.getName();
    String _lowerCase_2 = _name_2.toLowerCase();
    _builder.append(_lowerCase_2, "\t\t");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("registry.enableSimpleBroker(\"/topic/device\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
