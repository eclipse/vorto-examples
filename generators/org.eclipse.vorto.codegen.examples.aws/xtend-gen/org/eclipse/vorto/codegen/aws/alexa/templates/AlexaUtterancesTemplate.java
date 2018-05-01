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
package org.eclipse.vorto.codegen.aws.alexa.templates;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.api.mapping.IMapped;
import org.eclipse.vorto.codegen.aws.alexa.templates.AbstractAlexaTemplate;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.functionblock.Operation;
import org.eclipse.vorto.core.api.model.functionblock.Param;
import org.eclipse.vorto.core.api.model.functionblock.Status;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class AlexaUtterancesTemplate extends AbstractAlexaTemplate {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = context.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("Utterances.txt");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<FunctionblockProperty> _properties = element.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        {
          FunctionblockModel _type = fbProperty.getType();
          FunctionBlock _functionblock = _type.getFunctionblock();
          EList<Operation> _operations = _functionblock.getOperations();
          for(final Operation operation : _operations) {
            IMapped<Operation> mappedElement = context.getMappedElement(operation, AbstractAlexaTemplate.STEREOTYPE_ALEXA);
            _builder.newLineIfNotEmpty();
            String _name = fbProperty.getName();
            CharSequence _defaultCommand = this.getDefaultCommand(_name, operation);
            String _string = _defaultCommand.toString();
            String _attributeValue = mappedElement.getAttributeValue("command", _string);
            String[] commands = this.split(_attributeValue);
            _builder.newLineIfNotEmpty();
            {
              for(final String singleCmd : commands) {
                String _name_1 = fbProperty.getName();
                String _replace = _name_1.replace("_", "");
                _builder.append(_replace, "");
                String _name_2 = operation.getName();
                String _firstUpper = StringExtensions.toFirstUpper(_name_2);
                String _replace_1 = _firstUpper.replace("_", "");
                _builder.append(_replace_1, "");
                _builder.append(" ");
                String _replace_2 = singleCmd.replace("_", " ");
                _builder.append(_replace_2, "");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.newLine();
        {
          FunctionblockModel _type_1 = fbProperty.getType();
          FunctionBlock _functionblock_1 = _type_1.getFunctionblock();
          Status _status = _functionblock_1.getStatus();
          boolean _notEquals = (!Objects.equal(_status, null));
          if (_notEquals) {
            {
              FunctionblockModel _type_2 = fbProperty.getType();
              FunctionBlock _functionblock_2 = _type_2.getFunctionblock();
              Status _status_1 = _functionblock_2.getStatus();
              EList<Property> _properties_1 = _status_1.getProperties();
              for(final Property statusProperty : _properties_1) {
                IMapped<Property> mappedElement_1 = context.getMappedElement(statusProperty, AbstractAlexaTemplate.STEREOTYPE_ALEXA);
                _builder.newLineIfNotEmpty();
                String _name_3 = fbProperty.getName();
                CharSequence _defaultCommand_1 = this.getDefaultCommand(_name_3, statusProperty);
                String _string_1 = _defaultCommand_1.toString();
                String _attributeValue_1 = mappedElement_1.getAttributeValue("command", _string_1);
                String[] commands_1 = this.split(_attributeValue_1);
                _builder.newLineIfNotEmpty();
                {
                  for(final String singleCmd_1 : commands_1) {
                    String _name_4 = fbProperty.getName();
                    String _replace_3 = _name_4.replace("_", "");
                    _builder.append(_replace_3, "");
                    String _name_5 = statusProperty.getName();
                    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_5);
                    String _replace_4 = _firstUpper_1.replace("_", "");
                    _builder.append(_replace_4, "");
                    _builder.append(" ");
                    String _replace_5 = singleCmd_1.replace("_", " ");
                    _builder.append(_replace_5, "");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        _builder.newLine();
      }
    }
    return _builder.toString();
  }
  
  private String[] split(final String concatenatedValue) {
    return concatenatedValue.split(";");
  }
  
  protected CharSequence getDefaultCommand(final String fbPropertyName, final Operation operation) {
    CharSequence _xifexpression = null;
    boolean _and = false;
    EList<Param> _params = operation.getParams();
    boolean _notEquals = (!Objects.equal(_params, null));
    if (!_notEquals) {
      _and = false;
    } else {
      EList<Param> _params_1 = operation.getParams();
      int _length = ((Object[])Conversions.unwrapArray(_params_1, Object.class)).length;
      boolean _greaterThan = (_length > 0);
      _and = _greaterThan;
    }
    if (_and) {
      StringConcatenation _builder = new StringConcatenation();
      {
        EList<Param> _params_2 = operation.getParams();
        boolean _hasElements = false;
        for(final Param param : _params_2) {
          if (!_hasElements) {
            _hasElements = true;
            _builder.append("{", "");
          } else {
            _builder.appendImmediate(" ", "");
          }
          {
            boolean _isAlexaSupportedParamType = this.isAlexaSupportedParamType(param);
            if (_isAlexaSupportedParamType) {
              String _name = param.getName();
              _builder.append(_name, "");
            }
          }
        }
        if (_hasElements) {
          _builder.append("}", "");
        }
      }
      _xifexpression = _builder;
    } else {
      String _name_1 = operation.getName();
      return ((fbPropertyName + " ") + _name_1);
    }
    return _xifexpression;
  }
  
  protected CharSequence getDefaultCommand(final String functionblockPropertyName, final Property property) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("get ");
    _builder.append(functionblockPropertyName, "");
    _builder.append(" ");
    String _name = property.getName();
    _builder.append(_name, "");
    return _builder;
  }
}
