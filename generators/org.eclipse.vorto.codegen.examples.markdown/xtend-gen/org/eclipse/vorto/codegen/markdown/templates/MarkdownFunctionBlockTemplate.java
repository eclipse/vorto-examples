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
package org.eclipse.vorto.codegen.markdown.templates;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.ITemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.markdown.utils.Utils;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.functionblock.Configuration;
import org.eclipse.vorto.core.api.model.functionblock.Fault;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.functionblock.Operation;
import org.eclipse.vorto.core.api.model.functionblock.ReturnType;
import org.eclipse.vorto.core.api.model.functionblock.Status;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class MarkdownFunctionBlockTemplate implements ITemplate<FunctionblockModel> {
  @Override
  public String getContent(final FunctionblockModel fb, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("## Functionblock *");
    String _name = fb.getName();
    _builder.append(_name, "");
    _builder.append("*");
    _builder.newLineIfNotEmpty();
    _builder.append("### Unique Identification");
    _builder.newLine();
    _builder.append("<table>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<tr><td>Name:</td><td>");
    String _name_1 = fb.getName();
    _builder.append(_name_1, "\t");
    _builder.append("</td></tr>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<tr><td>Namespace:</td><td>");
    String _namespace = fb.getNamespace();
    _builder.append(_namespace, "\t");
    _builder.append("</td></tr>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<tr><td>Version:</td><td>");
    String _version = fb.getVersion();
    _builder.append(_version, "\t");
    _builder.append("</td></tr>");
    _builder.newLineIfNotEmpty();
    _builder.append("</table>");
    _builder.newLine();
    _builder.append("### Description");
    _builder.newLine();
    String _description = fb.getDescription();
    _builder.append(_description, "");
    _builder.newLineIfNotEmpty();
    {
      FunctionBlock _functionblock = fb.getFunctionblock();
      Status _status = _functionblock.getStatus();
      boolean _notEquals = (!Objects.equal(_status, null));
      if (_notEquals) {
        _builder.append("### Status Properties");
        _builder.newLine();
        _builder.append("<table>");
        _builder.newLine();
        _builder.append("<tr><td>Name</td><td>Type</td><td>Description</td></tr>");
        _builder.newLine();
        {
          FunctionBlock _functionblock_1 = fb.getFunctionblock();
          Status _status_1 = _functionblock_1.getStatus();
          EList<Property> _properties = _status_1.getProperties();
          for(final Property property : _properties) {
            _builder.append("<tr><td>");
            String _name_2 = property.getName();
            _builder.append(_name_2, "");
            _builder.append("</td><td>");
            String _propertyType = Utils.getPropertyType(property);
            _builder.append(_propertyType, "");
            _builder.append("</td><td>");
            String _description_1 = property.getDescription();
            _builder.append(_description_1, "");
            _builder.append("</td></tr>");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("</table>");
        _builder.newLine();
      }
    }
    _builder.newLine();
    {
      FunctionBlock _functionblock_2 = fb.getFunctionblock();
      Configuration _configuration = _functionblock_2.getConfiguration();
      boolean _notEquals_1 = (!Objects.equal(_configuration, null));
      if (_notEquals_1) {
        _builder.append("### Configuration Properties");
        _builder.newLine();
        _builder.append("<table>");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("<tr><td>Name</td><td>Type</td><td>Description</td></tr>");
        _builder.newLine();
        {
          FunctionBlock _functionblock_3 = fb.getFunctionblock();
          Configuration _configuration_1 = _functionblock_3.getConfiguration();
          EList<Property> _properties_1 = _configuration_1.getProperties();
          for(final Property property_1 : _properties_1) {
            _builder.append("\t");
            _builder.append("<tr><td>");
            String _name_3 = property_1.getName();
            _builder.append(_name_3, "\t");
            _builder.append("</td><td>");
            String _propertyType_1 = Utils.getPropertyType(property_1);
            _builder.append(_propertyType_1, "\t");
            _builder.append("</td><td>");
            String _description_2 = property_1.getDescription();
            _builder.append(_description_2, "\t");
            _builder.append("</td></tr>");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("</table>");
        _builder.newLine();
      }
    }
    _builder.newLine();
    {
      FunctionBlock _functionblock_4 = fb.getFunctionblock();
      Fault _fault = _functionblock_4.getFault();
      boolean _notEquals_2 = (!Objects.equal(_fault, null));
      if (_notEquals_2) {
        _builder.append("### Fault Properties");
        _builder.newLine();
        _builder.append("<table>");
        _builder.newLine();
        _builder.append("<tr><td>Name</td><td>Type</td><td>Description</td></tr>");
        _builder.newLine();
        {
          FunctionBlock _functionblock_5 = fb.getFunctionblock();
          Fault _fault_1 = _functionblock_5.getFault();
          EList<Property> _properties_2 = _fault_1.getProperties();
          for(final Property property_2 : _properties_2) {
            _builder.append("<tr><td>");
            String _name_4 = property_2.getName();
            _builder.append(_name_4, "");
            _builder.append("</td><td>");
            String _propertyType_2 = Utils.getPropertyType(property_2);
            _builder.append(_propertyType_2, "");
            _builder.append("</td><td>");
            String _description_3 = property_2.getDescription();
            _builder.append(_description_3, "");
            _builder.append("</td></tr>");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("</table>");
        _builder.newLine();
      }
    }
    {
      FunctionBlock _functionblock_6 = fb.getFunctionblock();
      EList<Operation> _operations = _functionblock_6.getOperations();
      for(final Operation operation : _operations) {
        _builder.append("### Operation *");
        String _name_5 = operation.getName();
        _builder.append(_name_5, "");
        _builder.append("*");
        _builder.newLineIfNotEmpty();
        _builder.append("<table>");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("<tr><td>Name</td><td>");
        String _name_6 = operation.getName();
        _builder.append(_name_6, "\t");
        _builder.append("</td></tr>");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("<tr><td>Description</td><td>");
        String _description_4 = operation.getDescription();
        _builder.append(_description_4, "\t");
        _builder.append("</td></tr>");
        _builder.newLineIfNotEmpty();
        {
          ReturnType _returnType = operation.getReturnType();
          boolean _notEquals_3 = (!Objects.equal(_returnType, null));
          if (_notEquals_3) {
            _builder.append("\t");
            _builder.append("<tr><td>Return Type</td><td>");
            ReturnType _returnType_1 = operation.getReturnType();
            String _returnType_2 = Utils.getReturnType(_returnType_1);
            _builder.append(_returnType_2, "\t");
            _builder.append("</td></tr>");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("</table>");
        _builder.newLine();
        _builder.newLine();
      }
    }
    return _builder.toString();
  }
}
