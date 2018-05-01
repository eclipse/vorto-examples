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
package org.eclipse.vorto.codegen.coap.client.templates;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.ITemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.coap.CoAPUtils;
import org.eclipse.vorto.core.api.model.functionblock.Operation;
import org.eclipse.vorto.core.api.model.functionblock.Param;
import org.eclipse.vorto.core.api.model.functionblock.ReturnObjectType;
import org.eclipse.vorto.core.api.model.functionblock.ReturnType;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class CoAPClientOperationTemplate implements ITemplate<Operation> {
  private String primitiveTypeWrapper_suffix;
  
  private String paramSet_suffix;
  
  private ITemplate<Param> parameterTemplate;
  
  public CoAPClientOperationTemplate(final String primitiveTypeWrapper_suffix, final String paramSet_suffix, final ITemplate<Param> parameterTemplate) {
    this.primitiveTypeWrapper_suffix = primitiveTypeWrapper_suffix;
    this.paramSet_suffix = paramSet_suffix;
    this.parameterTemplate = parameterTemplate;
  }
  
  @Override
  public String getContent(final Operation op, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("* ");
    String _description = op.getDescription();
    _builder.append(_description, "");
    _builder.newLineIfNotEmpty();
    _builder.append("*/");
    _builder.newLine();
    {
      boolean _or = false;
      ReturnType _returnType = op.getReturnType();
      if ((_returnType instanceof ReturnObjectType)) {
        _or = true;
      } else {
        ReturnType _returnType_1 = op.getReturnType();
        boolean _equals = Objects.equal(_returnType_1, null);
        _or = _equals;
      }
      if (_or) {
        _builder.append("public ");
        String _returnTypeAsString = CoAPUtils.getReturnTypeAsString(op);
        _builder.append(_returnTypeAsString, "");
        _builder.append(" ");
        String _name = op.getName();
        _builder.append(_name, "");
        _builder.append("(");
        String _parameterString = this.getParameterString(op, invocationContext);
        _builder.append(_parameterString, "");
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("public ");
        String _returnTypeAsString_1 = CoAPUtils.getReturnTypeAsString(op);
        _builder.append(_returnTypeAsString_1, "");
        _builder.append(" ");
        String _name_1 = op.getName();
        _builder.append(_name_1, "");
        _builder.append("(");
        String _parameterString_1 = this.getParameterString(op, invocationContext);
        _builder.append(_parameterString_1, "");
        _builder.append(") throws Exception {");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      EList<Param> _params = op.getParams();
      boolean _isEmpty = _params.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("\t");
        String _name_2 = op.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_2);
        _builder.append(_firstUpper, "\t");
        _builder.append(this.paramSet_suffix, "\t");
        _builder.append(" paramSet = new ");
        String _name_3 = op.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_3);
        _builder.append(_firstUpper_1, "\t");
        _builder.append("ParamSet();");
        _builder.newLineIfNotEmpty();
        {
          EList<Param> _params_1 = op.getParams();
          for(final Param param : _params_1) {
            _builder.append("\t");
            _builder.append("paramSet.set");
            String _name_4 = param.getName();
            String _firstUpper_2 = StringExtensions.toFirstUpper(_name_4);
            _builder.append(_firstUpper_2, "\t");
            _builder.append("(");
            String _name_5 = param.getName();
            _builder.append(_name_5, "\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.newLine();
        _builder.append("\t");
        _builder.append("byte[] payload = Client.serializePayload(paramSet);");
        _builder.newLine();
        {
          ReturnType _returnType_2 = op.getReturnType();
          boolean _notEquals = (!Objects.equal(_returnType_2, null));
          if (_notEquals) {
            _builder.append("\t");
            _builder.append("final Response response = Client.sendRequest(BASE_URI + \"/\" + \"");
            String _name_6 = op.getName();
            String _firstLower = StringExtensions.toFirstLower(_name_6);
            _builder.append(_firstLower, "\t");
            _builder.append("\", CoapMethod.POST, payload);");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t");
            _builder.append("Client.sendRequest(BASE_URI + \"/\" + \"");
            String _name_7 = op.getName();
            String _firstLower_1 = StringExtensions.toFirstLower(_name_7);
            _builder.append(_firstLower_1, "\t");
            _builder.append("\", CoapMethod.POST, payload);");
            _builder.newLineIfNotEmpty();
          }
        }
      } else {
        {
          ReturnType _returnType_3 = op.getReturnType();
          boolean _notEquals_1 = (!Objects.equal(_returnType_3, null));
          if (_notEquals_1) {
            _builder.append("\t");
            _builder.append("final Response response = Client.sendRequest(BASE_URI + \"/\" + \"");
            String _name_8 = op.getName();
            String _firstLower_2 = StringExtensions.toFirstLower(_name_8);
            _builder.append(_firstLower_2, "\t");
            _builder.append("\", CoapMethod.POST);");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t");
            _builder.append("Client.sendRequest(BASE_URI + \"/\" + \"");
            String _name_9 = op.getName();
            String _firstLower_3 = StringExtensions.toFirstLower(_name_9);
            _builder.append(_firstLower_3, "\t");
            _builder.append("\", CoapMethod.POST, payload);");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.newLine();
    {
      ReturnType _returnType_4 = op.getReturnType();
      boolean _notEquals_2 = (!Objects.equal(_returnType_4, null));
      if (_notEquals_2) {
        _builder.append("\t");
        _builder.append("if(response != null) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("if(response.getCode().value < 100) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("if(response.getPayloadSize() > 0) {");
        _builder.newLine();
        {
          ReturnType _returnType_5 = op.getReturnType();
          if ((_returnType_5 instanceof ReturnObjectType)) {
            _builder.append("\t");
            _builder.append("\t\t\t");
            String returnType = CoAPUtils.getReturnTypeAsString(op);
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t\t");
            _builder.append("final Object o = Client.deserializePayload(");
            _builder.append(returnType, "\t\t\t\t");
            _builder.append(".class, response.getPayload());");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t\t");
            _builder.append("if(o instanceof ");
            _builder.append(returnType, "\t\t\t\t");
            _builder.append("){");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t\t");
            _builder.append("\t");
            _builder.append("return (");
            _builder.append(returnType, "\t\t\t\t\t");
            _builder.append(") o;");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t\t");
            _builder.append("}");
            _builder.newLine();
          } else {
            _builder.append("\t");
            _builder.append("\t\t\t");
            _builder.append("final Object o = Client.deserializePayload(");
            String _name_10 = op.getName();
            String _firstUpper_3 = StringExtensions.toFirstUpper(_name_10);
            _builder.append(_firstUpper_3, "\t\t\t\t");
            _builder.append(this.primitiveTypeWrapper_suffix, "\t\t\t\t");
            _builder.append(".class, response.getPayload());");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t\t");
            _builder.append("if(o instanceof ");
            String _name_11 = op.getName();
            String _firstUpper_4 = StringExtensions.toFirstUpper(_name_11);
            _builder.append(_firstUpper_4, "\t\t\t\t");
            _builder.append(this.primitiveTypeWrapper_suffix, "\t\t\t\t");
            _builder.append("){");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t\t");
            _builder.append("\t");
            _builder.append("final ");
            String _name_12 = op.getName();
            String _firstUpper_5 = StringExtensions.toFirstUpper(_name_12);
            _builder.append(_firstUpper_5, "\t\t\t\t\t");
            _builder.append(this.primitiveTypeWrapper_suffix, "\t\t\t\t\t");
            _builder.append(" result = (");
            String _name_13 = op.getName();
            String _firstUpper_6 = StringExtensions.toFirstUpper(_name_13);
            _builder.append(_firstUpper_6, "\t\t\t\t\t");
            _builder.append(this.primitiveTypeWrapper_suffix, "\t\t\t\t\t");
            _builder.append(") o;");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t\t");
            _builder.append("\t");
            _builder.append("return result.getValue();");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t\t\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        {
          ReturnType _returnType_6 = op.getReturnType();
          if ((_returnType_6 instanceof ReturnObjectType)) {
            _builder.append("\t");
            _builder.append("return null;");
            _builder.newLine();
          } else {
            _builder.append("\t");
            _builder.append("throw new Exception(\"Request failed!\");");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  public String getParameterString(final Operation op, final InvocationContext invocationContext) {
    String result = "";
    EList<Param> _params = op.getParams();
    for (final Param param : _params) {
      String _content = this.parameterTemplate.getContent(param, invocationContext);
      String _plus = ((result + ", ") + _content);
      result = _plus;
    }
    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(result);
    if (_isNullOrEmpty) {
      return "";
    } else {
      int _length = result.length();
      String _substring = result.substring(2, _length);
      String _replaceAll = _substring.replaceAll("\n", "");
      return _replaceAll.replaceAll("\r", "");
    }
  }
}
