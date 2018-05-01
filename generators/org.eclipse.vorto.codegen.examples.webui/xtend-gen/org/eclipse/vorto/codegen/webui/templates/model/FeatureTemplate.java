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

import com.google.common.base.Objects;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.templates.java.JavaClassFieldGetterTemplate;
import org.eclipse.vorto.codegen.templates.java.JavaClassFieldSetterTemplate;
import org.eclipse.vorto.codegen.templates.java.JavaClassFieldTemplate;
import org.eclipse.vorto.codegen.webui.templates.TemplateUtils;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.functionblock.Status;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class FeatureTemplate implements IFileTemplate<FunctionblockProperty> {
  private JavaClassFieldTemplate propertyTemplate;
  
  private JavaClassFieldSetterTemplate propertySetterTemplate;
  
  private JavaClassFieldGetterTemplate propertyGetterTemplate;
  
  public FeatureTemplate() {
    this.propertyTemplate = new JavaClassFieldTemplate() {
      @Override
      protected CharSequence addFieldAnnotations(final Property property) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("@JsonProperty(\"");
        String _name = property.getName();
        _builder.append(_name, "");
        _builder.append("\")");
        _builder.newLineIfNotEmpty();
        return _builder;
      }
    };
    JavaClassFieldSetterTemplate _javaClassFieldSetterTemplate = new JavaClassFieldSetterTemplate("set");
    this.propertySetterTemplate = _javaClassFieldSetterTemplate;
    JavaClassFieldGetterTemplate _javaClassFieldGetterTemplate = new JavaClassFieldGetterTemplate("get");
    this.propertyGetterTemplate = _javaClassFieldGetterTemplate;
  }
  
  @Override
  public String getFileName(final FunctionblockProperty context) {
    StringConcatenation _builder = new StringConcatenation();
    FunctionblockModel _type = context.getType();
    String _name = _type.getName();
    _builder.append(_name, "");
    _builder.append(".java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final FunctionblockProperty context) {
    StringConcatenation _builder = new StringConcatenation();
    EObject _eContainer = context.eContainer();
    String _baseApplicationPath = TemplateUtils.getBaseApplicationPath(((InformationModel) _eContainer));
    _builder.append(_baseApplicationPath, "");
    _builder.append("/model");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final FunctionblockProperty element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.example.iot.");
    EObject _eContainer = element.eContainer();
    String _name = ((InformationModel) _eContainer).getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append(".model;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import com.fasterxml.jackson.annotation.JsonProperty;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("* ");
    String _description = element.getDescription();
    _builder.append(_description, "");
    _builder.newLineIfNotEmpty();
    _builder.append("*/");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties = context.getConfigurationProperties();
      String _orDefault = _configurationProperties.getOrDefault("history", "true");
      boolean _equalsIgnoreCase = _orDefault.equalsIgnoreCase("true");
      if (_equalsIgnoreCase) {
        _builder.append("@javax.persistence.Entity");
        _builder.newLine();
      }
    }
    _builder.append("public class ");
    FunctionblockModel _type = element.getType();
    String _name_1 = _type.getName();
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
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    FunctionblockModel _type_1 = element.getType();
    FunctionBlock fb = _type_1.getFunctionblock();
    _builder.append("\t");
    _builder.newLineIfNotEmpty();
    {
      Status _status = fb.getStatus();
      boolean _notEquals = (!Objects.equal(_status, null));
      if (_notEquals) {
        {
          FunctionblockModel _type_2 = element.getType();
          FunctionBlock _functionblock = _type_2.getFunctionblock();
          Status _status_1 = _functionblock.getStatus();
          EList<Property> _properties = _status_1.getProperties();
          for(final Property property : _properties) {
            _builder.append("\t");
            String _content = this.propertyTemplate.getContent(property, context);
            _builder.append(_content, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.newLine();
        {
          FunctionblockModel _type_3 = element.getType();
          FunctionBlock _functionblock_1 = _type_3.getFunctionblock();
          Status _status_2 = _functionblock_1.getStatus();
          EList<Property> _properties_1 = _status_2.getProperties();
          for(final Property property_1 : _properties_1) {
            _builder.append("\t");
            String _content_1 = this.propertySetterTemplate.getContent(property_1, context);
            _builder.append(_content_1, "\t");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            String _content_2 = this.propertyGetterTemplate.getContent(property_1, context);
            _builder.append(_content_2, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
