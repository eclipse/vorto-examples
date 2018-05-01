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
package org.eclipse.vorto.codegen.webui.templates.model;

import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.TemplateUtils;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class ThingTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = context.getName();
    _builder.append(_name, "");
    _builder.append(".java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _baseApplicationPath = TemplateUtils.getBaseApplicationPath(context);
    _builder.append(_baseApplicationPath, "");
    _builder.append("/model");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel model, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.example.iot.");
    String _name = model.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append(".model;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import org.eclipse.vorto.repository.api.ModelId;");
    _builder.newLine();
    _builder.newLine();
    {
      Map<String, String> _configurationProperties = context.getConfigurationProperties();
      String _orDefault = _configurationProperties.getOrDefault("history", "true");
      boolean _equalsIgnoreCase = _orDefault.equalsIgnoreCase("true");
      if (_equalsIgnoreCase) {
        _builder.append("@javax.persistence.Entity");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    String _name_1 = model.getName();
    _builder.append(_name_1, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties_1 = context.getConfigurationProperties();
      String _orDefault_1 = _configurationProperties_1.getOrDefault("history", "true");
      boolean _equalsIgnoreCase_1 = _orDefault_1.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_1) {
        _builder.append("\t");
        _builder.append("@javax.persistence.Id");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private Long id;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private java.util.Date createdOn;");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String thingId;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String name;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    {
      Map<String, String> _configurationProperties_2 = context.getConfigurationProperties();
      String _orDefault_2 = _configurationProperties_2.getOrDefault("history", "true");
      boolean _equalsIgnoreCase_2 = _orDefault_2.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_2) {
        _builder.append("@javax.persistence.Transient");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("private ModelId thingType;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties = model.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        {
          Map<String, String> _configurationProperties_3 = context.getConfigurationProperties();
          String _orDefault_3 = _configurationProperties_3.getOrDefault("history", "true");
          boolean _equalsIgnoreCase_3 = _orDefault_3.equalsIgnoreCase("true");
          if (_equalsIgnoreCase_3) {
            _builder.append("\t");
            _builder.append("@javax.persistence.OneToOne(cascade = {javax.persistence.CascadeType.PERSIST,javax.persistence.CascadeType.MERGE})");
            _builder.newLine();
          }
        }
        _builder.append("\t");
        _builder.append("private ");
        FunctionblockModel _type = fbProperty.getType();
        String _name_2 = _type.getName();
        _builder.append(_name_2, "\t");
        _builder.append(" ");
        String _name_3 = fbProperty.getName();
        _builder.append(_name_3, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _name_4 = model.getName();
    _builder.append(_name_4, "\t");
    _builder.append("(String thingId, String name, ModelId thingType) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.thingId = thingId;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.name = name;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.thingType = thingType;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected ");
    String _name_5 = model.getName();
    _builder.append(_name_5, "\t");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String getThingId() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return thingId;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void setThingId(String thingId) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.thingId = thingId;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_1 = model.getProperties();
      for(final FunctionblockProperty fbProperty_1 : _properties_1) {
        _builder.append("\t");
        _builder.append("public ");
        FunctionblockModel _type_1 = fbProperty_1.getType();
        String _name_6 = _type_1.getName();
        _builder.append(_name_6, "\t");
        _builder.append(" get");
        String _name_7 = fbProperty_1.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_7);
        _builder.append(_firstUpper, "\t");
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("return ");
        String _name_8 = fbProperty_1.getName();
        _builder.append(_name_8, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_2 = model.getProperties();
      for(final FunctionblockProperty fbProperty_2 : _properties_2) {
        _builder.append("\t");
        _builder.append("public void set");
        String _name_9 = fbProperty_2.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_9);
        _builder.append(_firstUpper_1, "\t");
        _builder.append("(");
        FunctionblockModel _type_2 = fbProperty_2.getType();
        String _name_10 = _type_2.getName();
        _builder.append(_name_10, "\t");
        _builder.append(" ");
        String _name_11 = fbProperty_2.getName();
        _builder.append(_name_11, "\t");
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("this.");
        String _name_12 = fbProperty_2.getName();
        _builder.append(_name_12, "\t\t");
        _builder.append(" = ");
        String _name_13 = fbProperty_2.getName();
        _builder.append(_name_13, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ModelId getThingType() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return thingType;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void setThingType(ModelId thingType) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.thingType = thingType;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String getName() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return name;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void setName(String name) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.name = name;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties_4 = context.getConfigurationProperties();
      String _orDefault_4 = _configurationProperties_4.getOrDefault("history", "true");
      boolean _equalsIgnoreCase_4 = _orDefault_4.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_4) {
        _builder.append("\t");
        _builder.append("public Long getId() {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("return id;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public void setId(Long id) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("this.id = id;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public java.util.Date getCreatedOn() {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("return createdOn;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public void setCreatedOn(java.util.Date createdOn) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("this.createdOn = createdOn;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
