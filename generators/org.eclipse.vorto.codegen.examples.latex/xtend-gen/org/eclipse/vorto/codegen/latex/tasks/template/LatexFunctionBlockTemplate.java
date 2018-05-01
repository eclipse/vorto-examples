/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
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
package org.eclipse.vorto.codegen.latex.tasks.template;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.ITemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.latex.tasks.template.LatexComplexPropertyTemplate;
import org.eclipse.vorto.codegen.latex.tasks.template.LatexOperationTemplate;
import org.eclipse.vorto.codegen.latex.tasks.template.LatexSimplePropertyConstraintTemplate;
import org.eclipse.vorto.codegen.latex.tasks.template.LatexSimplePropertyTemplate;
import org.eclipse.vorto.codegen.utils.Utils;
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.vorto.core.api.model.functionblock.Configuration;
import org.eclipse.vorto.core.api.model.functionblock.Fault;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.functionblock.Operation;
import org.eclipse.vorto.core.api.model.functionblock.Status;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class LatexFunctionBlockTemplate implements ITemplate<FunctionblockModel> {
  private LatexSimplePropertyConstraintTemplate constraintTemplate;
  
  private LatexSimplePropertyTemplate simpleTemplate;
  
  private LatexComplexPropertyTemplate complexTemplate;
  
  private LatexOperationTemplate operationTemplate;
  
  public LatexFunctionBlockTemplate(final LatexSimplePropertyConstraintTemplate constraintTemplate, final LatexSimplePropertyTemplate simpleTemplate, final LatexComplexPropertyTemplate complexTemplate, final LatexOperationTemplate operationTemplate) {
    this.constraintTemplate = constraintTemplate;
    this.simpleTemplate = simpleTemplate;
    this.complexTemplate = complexTemplate;
    this.operationTemplate = operationTemplate;
  }
  
  @Override
  public String getContent(final FunctionblockModel fb, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\subsection{");
    String _displayname = fb.getDisplayname();
    _builder.append(_displayname, "");
    _builder.append("}");
    _builder.newLineIfNotEmpty();
    String _description = fb.getDescription();
    _builder.append(_description, "");
    _builder.append(" The details of the Functionblock ");
    String _displayname_1 = fb.getDisplayname();
    _builder.append(_displayname_1, "");
    _builder.newLineIfNotEmpty();
    _builder.append("\\footnote{Name: ");
    String _name = fb.getName();
    _builder.append(_name, "");
    _builder.append(", Namespace: ");
    String _namespace = fb.getNamespace();
    _builder.append(_namespace, "");
    _builder.append(", Version: ");
    String _version = fb.getVersion();
    _builder.append(_version, "");
    _builder.append(".}");
    _builder.newLineIfNotEmpty();
    _builder.append("are described below:");
    _builder.newLine();
    {
      FunctionBlock _functionblock = fb.getFunctionblock();
      Status _status = _functionblock.getStatus();
      boolean _notEquals = (!Objects.equal(_status, null));
      if (_notEquals) {
        _builder.append("\\subsubsection{Status Properties}");
        _builder.newLine();
        {
          FunctionBlock _functionblock_1 = fb.getFunctionblock();
          Status _status_1 = _functionblock_1.getStatus();
          EList<Property> _properties = _status_1.getProperties();
          for(final Property property : _properties) {
            {
              boolean _isSimpleNumeric = Utils.isSimpleNumeric(property);
              if (_isSimpleNumeric) {
                String _content = this.constraintTemplate.getContent(property, invocationContext);
                _builder.append(_content, "");
                _builder.newLineIfNotEmpty();
              } else {
                {
                  PropertyType _type = property.getType();
                  if ((_type instanceof PrimitivePropertyType)) {
                    String _content_1 = this.simpleTemplate.getContent(property, invocationContext);
                    _builder.append(_content_1, "");
                    _builder.newLineIfNotEmpty();
                  } else {
                    String _content_2 = this.complexTemplate.getContent(property, invocationContext);
                    _builder.append(_content_2, "");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
      }
    }
    _builder.newLine();
    {
      FunctionBlock _functionblock_2 = fb.getFunctionblock();
      Configuration _configuration = _functionblock_2.getConfiguration();
      boolean _notEquals_1 = (!Objects.equal(_configuration, null));
      if (_notEquals_1) {
        _builder.append("\\subsubsection{Configuration Properties}");
        _builder.newLine();
        {
          FunctionBlock _functionblock_3 = fb.getFunctionblock();
          Configuration _configuration_1 = _functionblock_3.getConfiguration();
          EList<Property> _properties_1 = _configuration_1.getProperties();
          for(final Property property_1 : _properties_1) {
            {
              boolean _isSimpleNumeric_1 = Utils.isSimpleNumeric(property_1);
              if (_isSimpleNumeric_1) {
                String _content_3 = this.constraintTemplate.getContent(property_1, invocationContext);
                _builder.append(_content_3, "");
                _builder.newLineIfNotEmpty();
              } else {
                {
                  PropertyType _type_1 = property_1.getType();
                  if ((_type_1 instanceof PrimitivePropertyType)) {
                    String _content_4 = this.simpleTemplate.getContent(property_1, invocationContext);
                    _builder.append(_content_4, "");
                    _builder.newLineIfNotEmpty();
                  } else {
                    String _content_5 = this.complexTemplate.getContent(property_1, invocationContext);
                    _builder.append(_content_5, "");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
      }
    }
    _builder.newLine();
    {
      FunctionBlock _functionblock_4 = fb.getFunctionblock();
      Fault _fault = _functionblock_4.getFault();
      boolean _notEquals_2 = (!Objects.equal(_fault, null));
      if (_notEquals_2) {
        _builder.append("\\subsubsection{Fault Properties}");
        _builder.newLine();
        {
          FunctionBlock _functionblock_5 = fb.getFunctionblock();
          Fault _fault_1 = _functionblock_5.getFault();
          EList<Property> _properties_2 = _fault_1.getProperties();
          for(final Property property_2 : _properties_2) {
            {
              boolean _isSimpleNumeric_2 = Utils.isSimpleNumeric(property_2);
              if (_isSimpleNumeric_2) {
                String _content_6 = this.constraintTemplate.getContent(property_2, invocationContext);
                _builder.append(_content_6, "");
                _builder.newLineIfNotEmpty();
              } else {
                {
                  PropertyType _type_2 = property_2.getType();
                  if ((_type_2 instanceof PrimitivePropertyType)) {
                    String _content_7 = this.simpleTemplate.getContent(property_2, invocationContext);
                    _builder.append(_content_7, "");
                    _builder.newLineIfNotEmpty();
                  } else {
                    String _content_8 = this.complexTemplate.getContent(property_2, invocationContext);
                    _builder.append(_content_8, "");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
      }
    }
    _builder.newLine();
    {
      boolean _and = false;
      FunctionBlock _functionblock_6 = fb.getFunctionblock();
      EList<Operation> _operations = _functionblock_6.getOperations();
      boolean _notEquals_3 = (!Objects.equal(_operations, null));
      if (!_notEquals_3) {
        _and = false;
      } else {
        FunctionBlock _functionblock_7 = fb.getFunctionblock();
        EList<Operation> _operations_1 = _functionblock_7.getOperations();
        int _size = _operations_1.size();
        boolean _greaterThan = (_size > 0);
        _and = _greaterThan;
      }
      if (_and) {
        _builder.append("\\subsubsection{Operations}");
        _builder.newLine();
        {
          FunctionBlock _functionblock_8 = fb.getFunctionblock();
          EList<Operation> _operations_2 = _functionblock_8.getOperations();
          for(final Operation operation : _operations_2) {
            String _content_9 = this.operationTemplate.getContent(operation, invocationContext);
            _builder.append(_content_9, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder.toString();
  }
}
