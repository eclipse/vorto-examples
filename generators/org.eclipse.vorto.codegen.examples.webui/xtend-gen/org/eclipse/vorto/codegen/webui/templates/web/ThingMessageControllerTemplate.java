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
package org.eclipse.vorto.codegen.webui.templates.web;

import java.util.Map;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.TemplateUtils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ThingMessageControllerTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = context.getName();
    _builder.append(_name, "");
    _builder.append("MessageController.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _baseApplicationPath = TemplateUtils.getBaseApplicationPath(context);
    _builder.append(_baseApplicationPath, "");
    _builder.append("/web");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.example.iot.");
    String _name = element.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append(".web;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import org.slf4j.Logger;");
    _builder.newLine();
    _builder.append("import org.slf4j.LoggerFactory;");
    _builder.newLine();
    _builder.append("import org.springframework.beans.factory.annotation.Autowired;");
    _builder.newLine();
    _builder.append("import org.springframework.messaging.handler.annotation.MessageMapping;");
    _builder.newLine();
    _builder.append("import org.springframework.messaging.simp.SimpMessagingTemplate;");
    _builder.newLine();
    _builder.append("import org.springframework.stereotype.Controller;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.example.iot.");
    String _name_1 = element.getName();
    String _lowerCase_1 = _name_1.toLowerCase();
    _builder.append(_lowerCase_1, "");
    _builder.append(".config.WebSocketConfig;");
    _builder.newLineIfNotEmpty();
    {
      Map<String, String> _configurationProperties = context.getConfigurationProperties();
      String _orDefault = _configurationProperties.getOrDefault("persistence", "false");
      boolean _equalsIgnoreCase = _orDefault.equalsIgnoreCase("true");
      if (_equalsIgnoreCase) {
        _builder.append("import com.example.iot.");
        String _name_2 = element.getName();
        String _lowerCase_2 = _name_2.toLowerCase();
        _builder.append(_lowerCase_2, "");
        _builder.append(".dao.");
        String _name_3 = element.getName();
        _builder.append(_name_3, "");
        _builder.append("Repository;");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("import com.example.iot.");
    String _name_4 = element.getName();
    String _lowerCase_3 = _name_4.toLowerCase();
    _builder.append(_lowerCase_3, "");
    _builder.append(".model.");
    String _name_5 = element.getName();
    _builder.append(_name_5, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.example.iot.");
    String _name_6 = element.getName();
    String _lowerCase_4 = _name_6.toLowerCase();
    _builder.append(_lowerCase_4, "");
    _builder.append(".service.DataService;");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.example.iot.");
    String _name_7 = element.getName();
    String _lowerCase_5 = _name_7.toLowerCase();
    _builder.append(_lowerCase_5, "");
    _builder.append(".service.DataService.DataCallback;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("@Controller");
    _builder.newLine();
    _builder.append("public class ");
    String _name_8 = element.getName();
    _builder.append(_name_8, "");
    _builder.append("MessageController {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private Logger log = LoggerFactory.getLogger(");
    String _name_9 = element.getName();
    _builder.append(_name_9, "\t");
    _builder.append("MessageController.class);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Autowired");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private SimpMessagingTemplate messagingTemplate;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Autowired");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private DataService dataService;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties_1 = context.getConfigurationProperties();
      String _orDefault_1 = _configurationProperties_1.getOrDefault("persistence", "false");
      boolean _equalsIgnoreCase_1 = _orDefault_1.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_1) {
        _builder.append("\t");
        _builder.append("@Autowired");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private ");
        String _name_10 = element.getName();
        _builder.append(_name_10, "\t");
        _builder.append("Repository crudRepository;");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@MessageMapping(WebSocketConfig.ENDPOINT+\"/subscribe\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String subscribe(String thingId) throws Exception {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("log.info(\"Subscribing for thingId : \" + thingId);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("final SimpMessagingTemplate tmp = messagingTemplate;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("dataService.registerAsyncCallback(thingId, new DataCallback() {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("public void onChange(");
    String _name_11 = element.getName();
    _builder.append(_name_11, "\t\t\t\t");
    _builder.append(" thing) {");
    _builder.newLineIfNotEmpty();
    {
      Map<String, String> _configurationProperties_2 = context.getConfigurationProperties();
      String _orDefault_2 = _configurationProperties_2.getOrDefault("persistence", "false");
      boolean _equalsIgnoreCase_2 = _orDefault_2.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_2) {
        _builder.append("\t\t\t\t\t");
        _builder.append("crudRepository.save(thing);");
        _builder.newLine();
      }
    }
    _builder.append("\t\t\t\t\t");
    _builder.append("tmp.convertAndSend( \"/topic/device/\" + thingId, thing);\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("});");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} catch (Throwable e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("log.error(\"Error while subcribing to device\", e);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return thingId;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@MessageMapping(WebSocketConfig.ENDPOINT+\"/unsubscribe\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String unsubscribe(String thingId) throws Exception {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("log.info(\"Unsubscribing for thingId : \" + thingId);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("dataService.deregisterAsyncCallback(thingId);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} catch (Throwable e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("log.error(\"Error while unsubcribing to thing\", e);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return thingId;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
