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
package org.eclipse.vorto.codegen.coap.server.templates;

import com.google.common.base.Objects;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.vorto.codegen.api.ITemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.coap.CoAPUtils;
import org.eclipse.vorto.codegen.utils.Utils;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.functionblock.Operation;
import org.eclipse.vorto.core.api.model.functionblock.Param;
import org.eclipse.vorto.core.api.model.functionblock.ReturnPrimitiveType;
import org.eclipse.vorto.core.api.model.functionblock.ReturnType;
import org.eclipse.vorto.core.api.model.functionblock.Status;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class CoAPServerIMRequestHandlerTemplate implements ITemplate<InformationModel> {
  private String classPackage;
  
  private String className;
  
  private String interfaceName;
  
  private String interfacePrefix;
  
  private String[] imports;
  
  private String primitiveTypeWrapper_suffix;
  
  public CoAPServerIMRequestHandlerTemplate(final String classPackage, final String className, final String interfaceName, final String interfacePrefix, final String primitiveTypeWrapper_suffix, final String[] imports) {
    this.className = className;
    this.classPackage = classPackage;
    this.interfaceName = interfaceName;
    this.interfacePrefix = interfacePrefix;
    this.primitiveTypeWrapper_suffix = primitiveTypeWrapper_suffix;
    this.imports = imports;
  }
  
  @Override
  public String getContent(final InformationModel im, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(this.classPackage, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import org.eclipse.californium.core.coap.CoAP;");
    _builder.newLine();
    _builder.append("import org.eclipse.californium.core.coap.CoAP.Code;");
    _builder.newLine();
    _builder.append("import org.eclipse.californium.core.server.resources.CoapExchange;");
    _builder.newLine();
    {
      for(final String imprt : this.imports) {
        {
          boolean _notEquals = (!Objects.equal(imprt, null));
          if (_notEquals) {
            _builder.append("import ");
            _builder.append(imprt, "");
            _builder.append(".*;");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(this.className, "");
    _builder.append(" implements ");
    _builder.append(this.interfaceName, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private final int contentType = 50;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private ");
    _builder.append(this.interfacePrefix, "\t");
    String _name = im.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "\t");
    _builder.append(" ");
    String _name_1 = im.getName();
    String _firstLower = StringExtensions.toFirstLower(_name_1);
    _builder.append(_firstLower, "\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("private JsonTransformer transformer;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    _builder.append(this.className, "\t");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    String _name_2 = im.getName();
    String _firstLower_1 = StringExtensions.toFirstLower(_name_2);
    _builder.append(_firstLower_1, "\t\t");
    _builder.append(" = new ");
    String _name_3 = im.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_3);
    _builder.append(_firstUpper_1, "\t\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("transformer = new JsonTransformer();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    _builder.append(this.className, "\t");
    _builder.append("(");
    _builder.append(this.interfacePrefix, "\t");
    String _name_4 = im.getName();
    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_4);
    _builder.append(_firstUpper_2, "\t");
    _builder.append(" ");
    String _name_5 = im.getName();
    String _firstLower_2 = StringExtensions.toFirstLower(_name_5);
    _builder.append(_firstLower_2, "\t");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.");
    String _name_6 = im.getName();
    String _firstLower_3 = StringExtensions.toFirstLower(_name_6);
    _builder.append(_firstLower_3, "\t\t");
    _builder.append(" = ");
    String _name_7 = im.getName();
    String _firstLower_4 = StringExtensions.toFirstLower(_name_7);
    _builder.append(_firstLower_4, "\t\t");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("transformer = new JsonTransformer();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void onRequest(CoapExchange exchange, String uri) throws Exception {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String fbService = URIAnalyzer.getService(uri);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String fbInstance = URIAnalyzer.getInstance(uri);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String fbOperationName = URIAnalyzer.getOperation(uri);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    EList<FunctionblockProperty> _properties = im.getProperties();
    final EMap<FunctionblockModel, EList<FunctionblockProperty>> map = CoAPUtils.sortByPropertyType(_properties);
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    {
      Set<FunctionblockModel> _keySet = map.keySet();
      for(final FunctionblockModel resource : _keySet) {
        _builder.append("\t\t");
        _builder.append("if (fbService.equalsIgnoreCase(\"");
        String _name_8 = resource.getName();
        _builder.append(_name_8, "\t\t");
        _builder.append("\")) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("\t");
        EList<FunctionblockProperty> instances = map.get(resource);
        _builder.newLineIfNotEmpty();
        {
          for(final FunctionblockProperty instance : instances) {
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("if (fbInstance.equalsIgnoreCase(\"");
            String _name_9 = instance.getName();
            _builder.append(_name_9, "\t\t\t");
            _builder.append("\")) {");
            _builder.newLineIfNotEmpty();
            {
              FunctionblockModel _type = instance.getType();
              FunctionBlock _functionblock = _type.getFunctionblock();
              EList<Operation> _operations = _functionblock.getOperations();
              boolean _notEquals_1 = (!Objects.equal(_operations, null));
              if (_notEquals_1) {
                {
                  FunctionblockModel _type_1 = instance.getType();
                  FunctionBlock _functionblock_1 = _type_1.getFunctionblock();
                  EList<Operation> _operations_1 = _functionblock_1.getOperations();
                  for(final Operation operation : _operations_1) {
                    _builder.append("\t\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("if(exchange.getRequestCode() == Code.POST) {");
                    _builder.newLine();
                    _builder.append("\t\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("if (fbOperationName.equals(\"");
                    String _name_10 = operation.getName();
                    String _firstLower_5 = StringExtensions.toFirstLower(_name_10);
                    _builder.append(_firstLower_5, "\t\t\t\t\t");
                    _builder.append("\")) {");
                    _builder.newLineIfNotEmpty();
                    {
                      EList<Param> _params = operation.getParams();
                      boolean _isEmpty = _params.isEmpty();
                      boolean _not = (!_isEmpty);
                      if (_not) {
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        String _name_11 = operation.getName();
                        String _firstUpper_3 = StringExtensions.toFirstUpper(_name_11);
                        _builder.append(_firstUpper_3, "\t\t\t\t\t\t");
                        _builder.append("ParamSet paramSet = null;");
                        _builder.newLineIfNotEmpty();
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("if(exchange.getRequestPayload().length > 0) {");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("final Object o = transformer.deserialize(");
                        String _name_12 = operation.getName();
                        String _firstUpper_4 = StringExtensions.toFirstUpper(_name_12);
                        _builder.append(_firstUpper_4, "\t\t\t\t\t\t\t");
                        _builder.append("ParamSet.class, exchange.getRequestPayload());");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("if(o instanceof ");
                        String _name_13 = operation.getName();
                        String _firstUpper_5 = StringExtensions.toFirstUpper(_name_13);
                        _builder.append(_firstUpper_5, "\t\t\t\t\t\t\t");
                        _builder.append("ParamSet) {");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("\t\t");
                        _builder.append("paramSet = (");
                        String _name_14 = operation.getName();
                        String _firstUpper_6 = StringExtensions.toFirstUpper(_name_14);
                        _builder.append(_firstUpper_6, "\t\t\t\t\t\t\t\t");
                        _builder.append("ParamSet) o;");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("}");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("}");
                        _builder.newLine();
                      }
                    }
                    {
                      ReturnType _returnType = operation.getReturnType();
                      boolean _notEquals_2 = (!Objects.equal(_returnType, null));
                      if (_notEquals_2) {
                        {
                          ReturnType _returnType_1 = operation.getReturnType();
                          if ((_returnType_1 instanceof ReturnPrimitiveType)) {
                            _builder.append("\t\t");
                            _builder.append("\t");
                            _builder.append("\t");
                            _builder.append("\t\t");
                            _builder.append("final ");
                            String _name_15 = operation.getName();
                            String _firstUpper_7 = StringExtensions.toFirstUpper(_name_15);
                            _builder.append(_firstUpper_7, "\t\t\t\t\t\t");
                            _builder.append(this.primitiveTypeWrapper_suffix, "\t\t\t\t\t\t");
                            _builder.append(" result = new ");
                            String _name_16 = operation.getName();
                            String _firstUpper_8 = StringExtensions.toFirstUpper(_name_16);
                            _builder.append(_firstUpper_8, "\t\t\t\t\t\t");
                            _builder.append(this.primitiveTypeWrapper_suffix, "\t\t\t\t\t\t");
                            _builder.append("();");
                            _builder.newLineIfNotEmpty();
                            _builder.append("\t\t");
                            _builder.append("\t");
                            _builder.append("\t");
                            _builder.append("\t\t");
                            _builder.append("result.setValue(");
                            String _name_17 = im.getName();
                            String _firstLower_6 = StringExtensions.toFirstLower(_name_17);
                            _builder.append(_firstLower_6, "\t\t\t\t\t\t");
                            _builder.append(".get");
                            String _name_18 = instance.getName();
                            String _firstUpper_9 = StringExtensions.toFirstUpper(_name_18);
                            _builder.append(_firstUpper_9, "\t\t\t\t\t\t");
                            _builder.append("().");
                            String _name_19 = operation.getName();
                            String _firstLower_7 = StringExtensions.toFirstLower(_name_19);
                            _builder.append(_firstLower_7, "\t\t\t\t\t\t");
                            _builder.append("(");
                            String _operationParams = this.operationParams(operation);
                            _builder.append(_operationParams, "\t\t\t\t\t\t");
                            _builder.append("));");
                            _builder.newLineIfNotEmpty();
                          } else {
                            _builder.append("\t\t");
                            _builder.append("\t");
                            _builder.append("\t");
                            _builder.append("\t\t");
                            String _returnTypeAsString = CoAPUtils.getReturnTypeAsString(operation);
                            _builder.append(_returnTypeAsString, "\t\t\t\t\t\t");
                            _builder.append(" result = ");
                            String _name_20 = im.getName();
                            String _firstLower_8 = StringExtensions.toFirstLower(_name_20);
                            _builder.append(_firstLower_8, "\t\t\t\t\t\t");
                            _builder.append(".get");
                            String _name_21 = instance.getName();
                            String _firstUpper_10 = StringExtensions.toFirstUpper(_name_21);
                            _builder.append(_firstUpper_10, "\t\t\t\t\t\t");
                            _builder.append("().");
                            String _name_22 = operation.getName();
                            String _firstLower_9 = StringExtensions.toFirstLower(_name_22);
                            _builder.append(_firstLower_9, "\t\t\t\t\t\t");
                            _builder.append("(");
                            String _operationParams_1 = this.operationParams(operation);
                            _builder.append(_operationParams_1, "\t\t\t\t\t\t");
                            _builder.append(");");
                            _builder.newLineIfNotEmpty();
                          }
                        }
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("exchange.respond(");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("CoAP.ResponseCode.CHANGED,");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("transformer.serialize(result),");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("contentType);");
                        _builder.newLine();
                      } else {
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("exchange.respond(CoAP.ResponseCode.CHANGED);");
                        _builder.newLine();
                      }
                    }
                    _builder.append("\t\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("}");
                    _builder.newLine();
                    _builder.append("\t\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("}");
                    _builder.newLine();
                  }
                }
              }
            }
            {
              FunctionblockModel _type_2 = instance.getType();
              FunctionBlock _functionblock_2 = _type_2.getFunctionblock();
              Status _status = _functionblock_2.getStatus();
              boolean _notEquals_3 = (!Objects.equal(_status, null));
              if (_notEquals_3) {
                {
                  FunctionblockModel _type_3 = instance.getType();
                  FunctionBlock _functionblock_3 = _type_3.getFunctionblock();
                  Status _status_1 = _functionblock_3.getStatus();
                  EList<Property> _properties_1 = _status_1.getProperties();
                  for(final Property status : _properties_1) {
                    _builder.append("\t\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("if (fbOperationName.equals(\"");
                    String _name_23 = status.getName();
                    String _firstLower_10 = StringExtensions.toFirstLower(_name_23);
                    _builder.append(_firstLower_10, "\t\t\t\t");
                    _builder.append("\")) {");
                    _builder.newLineIfNotEmpty();
                    {
                      boolean _isReadable = Utils.isReadable(status);
                      if (_isReadable) {
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("if(exchange.getRequestCode() == Code.GET && exchange.getRequestOptions().getObserve() == null){");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("exchange.respond( ");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("CoAP.ResponseCode.CONTENT, ");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("transformer.serialize(");
                        String _name_24 = im.getName();
                        String _firstLower_11 = StringExtensions.toFirstLower(_name_24);
                        _builder.append(_firstLower_11, "\t\t\t\t\t\t\t");
                        _builder.append(".get");
                        String _name_25 = instance.getName();
                        String _firstUpper_11 = StringExtensions.toFirstUpper(_name_25);
                        _builder.append(_firstUpper_11, "\t\t\t\t\t\t\t");
                        _builder.append("().get");
                        String _name_26 = status.getName();
                        String _firstUpper_12 = StringExtensions.toFirstUpper(_name_26);
                        _builder.append(_firstUpper_12, "\t\t\t\t\t\t\t");
                        _builder.append("()), ");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("contentType);");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("return;");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("}");
                        _builder.newLine();
                      }
                    }
                    {
                      boolean _isWritable = Utils.isWritable(status);
                      if (_isWritable) {
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("if(exchange.getRequestCode() == Code.PUT) {");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("if(exchange.getRequestPayload().length > 0) {");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("final Object o = transformer.deserialize(");
                        String _name_27 = status.getName();
                        String _firstUpper_13 = StringExtensions.toFirstUpper(_name_27);
                        _builder.append(_firstUpper_13, "\t\t\t\t\t\t\t");
                        _builder.append(".class, exchange.getRequestPayload());");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("if(o instanceof ");
                        String _name_28 = status.getName();
                        String _firstUpper_14 = StringExtensions.toFirstUpper(_name_28);
                        _builder.append(_firstUpper_14, "\t\t\t\t\t\t\t");
                        _builder.append(") {");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t\t");
                        String _name_29 = im.getName();
                        String _firstLower_12 = StringExtensions.toFirstLower(_name_29);
                        _builder.append(_firstLower_12, "\t\t\t\t\t\t\t\t");
                        _builder.append(".get");
                        String _name_30 = instance.getName();
                        String _firstUpper_15 = StringExtensions.toFirstUpper(_name_30);
                        _builder.append(_firstUpper_15, "\t\t\t\t\t\t\t\t");
                        _builder.append("().set");
                        String _name_31 = status.getName();
                        String _firstUpper_16 = StringExtensions.toFirstUpper(_name_31);
                        _builder.append(_firstUpper_16, "\t\t\t\t\t\t\t\t");
                        _builder.append("((");
                        String _name_32 = status.getName();
                        String _firstUpper_17 = StringExtensions.toFirstUpper(_name_32);
                        _builder.append(_firstUpper_17, "\t\t\t\t\t\t\t\t");
                        _builder.append(") o);");
                        _builder.newLineIfNotEmpty();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t\t");
                        _builder.append("exchange.respond(CoAP.ResponseCode.CHANGED);");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("} else {");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t\t");
                        _builder.append("exchange.respond(CoAP.ResponseCode.BAD_REQUEST);");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("}");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("} else {");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t\t");
                        _builder.append("exchange.respond(CoAP.ResponseCode.BAD_REQUEST);");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("}");
                        _builder.newLine();
                        _builder.append("\t\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("}");
                        _builder.newLine();
                      }
                    }
                    _builder.append("\t\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("}");
                    _builder.newLine();
                  }
                }
              }
            }
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  private String operationParams(final Operation op) {
    String result = "";
    boolean _and = false;
    EList<Param> _params = op.getParams();
    boolean _notEquals = (!Objects.equal(_params, null));
    if (!_notEquals) {
      _and = false;
    } else {
      EList<Param> _params_1 = op.getParams();
      int _size = _params_1.size();
      boolean _greaterThan = (_size > 0);
      _and = _greaterThan;
    }
    if (_and) {
      EList<Param> _params_2 = op.getParams();
      for (final Param param : _params_2) {
        String _result = result;
        String _name = param.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name);
        String _plus = ("paramSet.get" + _firstUpper);
        String _plus_1 = (_plus + "(),\n");
        result = (_result + _plus_1);
      }
      int _length = result.length();
      int _minus = (_length - 2);
      return result.substring(0, _minus);
    } else {
      return "";
    }
  }
}
