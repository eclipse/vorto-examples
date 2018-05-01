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

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.TemplateUtils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class HistoryDataControllerTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("HistoryDataController.java");
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
    _builder.append("import java.util.ArrayList;");
    _builder.newLine();
    _builder.append("import java.util.List;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.springframework.beans.factory.annotation.Autowired;");
    _builder.newLine();
    _builder.append("import org.springframework.http.MediaType;");
    _builder.newLine();
    _builder.append("import org.springframework.web.bind.annotation.RequestMapping;");
    _builder.newLine();
    _builder.append("import org.springframework.web.bind.annotation.RequestMethod;");
    _builder.newLine();
    _builder.append("import org.springframework.web.bind.annotation.RestController;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.example.iot.");
    String _name_1 = element.getName();
    String _lowerCase_1 = _name_1.toLowerCase();
    _builder.append(_lowerCase_1, "");
    _builder.append(".dao.");
    String _name_2 = element.getName();
    _builder.append(_name_2, "");
    _builder.append("Repository;");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.example.iot.");
    String _name_3 = element.getName();
    String _lowerCase_2 = _name_3.toLowerCase();
    _builder.append(_lowerCase_2, "");
    _builder.append(".model.");
    String _name_4 = element.getName();
    _builder.append(_name_4, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("@RestController");
    _builder.newLine();
    _builder.append("@RequestMapping(\"/rest/history/devices\")");
    _builder.newLine();
    _builder.append("public class HistoryDataController {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Autowired");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private ");
    String _name_5 = element.getName();
    _builder.append(_name_5, "\t");
    _builder.append("Repository repository;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public List<");
    String _name_6 = element.getName();
    _builder.append(_name_6, "\t");
    _builder.append("> findAll() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("List<");
    String _name_7 = element.getName();
    _builder.append(_name_7, "\t\t");
    _builder.append("> target = new ArrayList<>();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("repository.findAll().forEach(target::add);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return target;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
