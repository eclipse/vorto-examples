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
public class AlexaIndentSchemaTemplate extends AbstractAlexaTemplate {
  @Override
  public String getFileName(final InformationModel context) {
    return "IntendSchema.json";
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("{");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("\"intents\": [");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties = element.getProperties();
      boolean _hasElements = false;
      for(final FunctionblockProperty fbProperty : _properties) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "\t\t");
        }
        {
          FunctionblockModel _type = fbProperty.getType();
          FunctionBlock _functionblock = _type.getFunctionblock();
          EList<Operation> _operations = _functionblock.getOperations();
          boolean _hasElements_1 = false;
          for(final Operation operation : _operations) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(",", "\t\t");
            }
            _builder.append("\t\t");
            _builder.append("{");
            _builder.newLine();
            _builder.append("\t\t");
            _builder.append("\"intent\": \"");
            String _name = fbProperty.getName();
            String _replace = _name.replace("_", "");
            _builder.append(_replace, "\t\t");
            String _name_1 = operation.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name_1);
            String _replace_1 = _firstUpper.replace("_", "");
            _builder.append(_replace_1, "\t\t");
            _builder.append("\"");
            {
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
                _builder.append(",");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append("\"slots\": [");
                _builder.newLine();
                {
                  EList<Param> _params_2 = operation.getParams();
                  boolean _hasElements_2 = false;
                  for(final Param param : _params_2) {
                    if (!_hasElements_2) {
                      _hasElements_2 = true;
                    } else {
                      _builder.appendImmediate(",", "\t\t\t");
                    }
                    {
                      boolean _isAlexaSupportedParamType = this.isAlexaSupportedParamType(param);
                      if (_isAlexaSupportedParamType) {
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("{");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\"name\": \"");
                        String _name_2 = param.getName();
                        _builder.append(_name_2, "\t\t\t");
                        _builder.append("\",");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\"type\": \"");
                        String _mapToAlexaSupportedType = this.mapToAlexaSupportedType(param);
                        _builder.append(_mapToAlexaSupportedType, "\t\t\t");
                        _builder.append("\"");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("}");
                        _builder.newLine();
                      }
                    }
                  }
                }
                _builder.append("\t\t");
                _builder.append("]");
                _builder.newLine();
              }
            }
            _builder.append("\t\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
        {
          FunctionblockModel _type_1 = fbProperty.getType();
          FunctionBlock _functionblock_1 = _type_1.getFunctionblock();
          Status _status = _functionblock_1.getStatus();
          boolean _notEquals_1 = (!Objects.equal(_status, null));
          if (_notEquals_1) {
            _builder.append("\t\t");
            {
              FunctionblockModel _type_2 = fbProperty.getType();
              FunctionBlock _functionblock_2 = _type_2.getFunctionblock();
              EList<Operation> _operations_1 = _functionblock_2.getOperations();
              int _length_1 = ((Object[])Conversions.unwrapArray(_operations_1, Object.class)).length;
              boolean _greaterThan_1 = (_length_1 > 0);
              if (_greaterThan_1) {
                _builder.append(",");
              }
            }
            _builder.newLineIfNotEmpty();
            {
              FunctionblockModel _type_3 = fbProperty.getType();
              FunctionBlock _functionblock_3 = _type_3.getFunctionblock();
              Status _status_1 = _functionblock_3.getStatus();
              EList<Property> _properties_1 = _status_1.getProperties();
              boolean _hasElements_3 = false;
              for(final Property statusProperty : _properties_1) {
                if (!_hasElements_3) {
                  _hasElements_3 = true;
                } else {
                  _builder.appendImmediate(",", "\t\t");
                }
                _builder.append("\t\t");
                _builder.append("{");
                _builder.newLine();
                _builder.append("\t\t");
                _builder.append("\"intent\": \"");
                String _name_3 = fbProperty.getName();
                String _replace_2 = _name_3.replace("_", "");
                _builder.append(_replace_2, "\t\t");
                String _name_4 = statusProperty.getName();
                String _firstUpper_1 = StringExtensions.toFirstUpper(_name_4);
                String _replace_3 = _firstUpper_1.replace("_", "");
                _builder.append(_replace_3, "\t\t");
                _builder.append("\"");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append("}");
                _builder.newLine();
              }
            }
          }
        }
      }
    }
    _builder.append("  ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
