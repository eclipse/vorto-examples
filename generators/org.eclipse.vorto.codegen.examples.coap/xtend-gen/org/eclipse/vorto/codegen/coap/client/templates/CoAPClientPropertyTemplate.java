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

import org.eclipse.vorto.codegen.api.ITemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.coap.CoAPUtils;
import org.eclipse.vorto.codegen.utils.Utils;
import org.eclipse.vorto.core.api.model.datatype.ObjectPropertyType;
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class CoAPClientPropertyTemplate implements ITemplate<Property> {
  private String primTypeSuffix;
  
  private String paramSetSuffx;
  
  public CoAPClientPropertyTemplate(final String primTypeSuffix, final String paramSetSuffx) {
    this.primTypeSuffix = primTypeSuffix;
    this.paramSetSuffx = paramSetSuffx;
  }
  
  @Override
  public String getContent(final Property property, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("* ");
    String _description = property.getDescription();
    _builder.append(_description, "");
    _builder.newLineIfNotEmpty();
    _builder.append("*/");
    _builder.newLine();
    {
      boolean _isReadable = Utils.isReadable(property);
      if (_isReadable) {
        {
          PropertyType _type = property.getType();
          if ((_type instanceof ObjectPropertyType)) {
            _builder.append("public ");
            String _propertyTypeAsString = CoAPUtils.getPropertyTypeAsString(property);
            _builder.append(_propertyTypeAsString, "");
            _builder.append(" get");
            String _name = property.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name);
            _builder.append(_firstUpper, "");
            _builder.append("(){");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("public ");
            String _propertyTypeAsString_1 = CoAPUtils.getPropertyTypeAsString(property);
            _builder.append(_propertyTypeAsString_1, "");
            _builder.append(" get");
            String _name_1 = property.getName();
            String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
            _builder.append(_firstUpper_1, "");
            _builder.append("() throws Exception {");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("final Response response = Client.sendRequest(BASE_URI + \"/\" + \"");
        String _name_2 = property.getName();
        String _firstLower = StringExtensions.toFirstLower(_name_2);
        _builder.append(_firstLower, "\t");
        _builder.append("\", CoapMethod.GET);");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("if(Client.isValidResponseWithPayload(response)) {");
        _builder.newLine();
        {
          PropertyType _type_1 = property.getType();
          if ((_type_1 instanceof ObjectPropertyType)) {
            _builder.append("\t\t");
            _builder.append("final Object o = Client.deserializePayload(");
            String _propertyTypeAsString_2 = CoAPUtils.getPropertyTypeAsString(property);
            _builder.append(_propertyTypeAsString_2, "\t\t");
            _builder.append(".class, response.getPayload());");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("if(o instanceof ");
            String _propertyTypeAsString_3 = CoAPUtils.getPropertyTypeAsString(property);
            _builder.append(_propertyTypeAsString_3, "\t\t");
            _builder.append("){");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("return (");
            String _propertyTypeAsString_4 = CoAPUtils.getPropertyTypeAsString(property);
            _builder.append(_propertyTypeAsString_4, "\t\t\t");
            _builder.append(") o;");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("}");
            _builder.newLine();
          } else {
            _builder.append("\t\t");
            _builder.append("final Object o = Client.deserializePayload(");
            String _name_3 = property.getName();
            String _firstUpper_2 = StringExtensions.toFirstUpper(_name_3);
            _builder.append(_firstUpper_2, "\t\t");
            _builder.append(this.primTypeSuffix, "\t\t");
            _builder.append(".class, response.getPayload());");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("if(o instanceof ");
            String _name_4 = property.getName();
            String _firstUpper_3 = StringExtensions.toFirstUpper(_name_4);
            _builder.append(_firstUpper_3, "\t\t");
            _builder.append(this.primTypeSuffix, "\t\t");
            _builder.append("){");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("final ");
            String _name_5 = property.getName();
            String _firstUpper_4 = StringExtensions.toFirstUpper(_name_5);
            _builder.append(_firstUpper_4, "\t\t\t");
            _builder.append(this.primTypeSuffix, "\t\t\t");
            _builder.append(" result = (");
            String _name_6 = property.getName();
            String _firstUpper_5 = StringExtensions.toFirstUpper(_name_6);
            _builder.append(_firstUpper_5, "\t\t\t");
            _builder.append(this.primTypeSuffix, "\t\t\t");
            _builder.append(") o;");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("return result.getValue();");
            _builder.newLine();
            _builder.append("\t\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        {
          PropertyType _type_2 = property.getType();
          if ((_type_2 instanceof ObjectPropertyType)) {
            _builder.append("\t");
            _builder.append("return null;");
            _builder.newLine();
          } else {
            _builder.append("\t");
            _builder.append("throw new Exception(\"Request Failed!\");");
            _builder.newLine();
          }
        }
        _builder.append("}");
        _builder.newLine();
      }
    }
    {
      boolean _isWritable = Utils.isWritable(property);
      if (_isWritable) {
        _builder.newLine();
        _builder.append("public void set");
        String _name_7 = property.getName();
        String _firstUpper_6 = StringExtensions.toFirstUpper(_name_7);
        _builder.append(_firstUpper_6, "");
        _builder.append("(");
        String _propertyTypeAsString_5 = CoAPUtils.getPropertyTypeAsString(property);
        _builder.append(_propertyTypeAsString_5, "");
        _builder.append(" ");
        String _name_8 = property.getName();
        String _firstLower_1 = StringExtensions.toFirstLower(_name_8);
        _builder.append(_firstLower_1, "");
        _builder.append("){");
        _builder.newLineIfNotEmpty();
        {
          PropertyType _type_3 = property.getType();
          if ((_type_3 instanceof PrimitivePropertyType)) {
            _builder.append("\t");
            String _name_9 = property.getName();
            String _firstUpper_7 = StringExtensions.toFirstUpper(_name_9);
            _builder.append(_firstUpper_7, "\t");
            _builder.append(this.paramSetSuffx, "\t");
            _builder.append(" paramSet = new ");
            String _name_10 = property.getName();
            String _firstUpper_8 = StringExtensions.toFirstUpper(_name_10);
            _builder.append(_firstUpper_8, "\t");
            _builder.append(this.paramSetSuffx, "\t");
            _builder.append("();");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("paramSet.setValue(");
            String _name_11 = property.getName();
            String _firstLower_2 = StringExtensions.toFirstLower(_name_11);
            _builder.append(_firstLower_2, "\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("byte[] payload = Client.serializePayload(paramSet);");
            _builder.newLine();
          } else {
            _builder.append("\t");
            _builder.append("byte[] payload = Client.serializePayload(");
            String _name_12 = property.getName();
            String _firstLower_3 = StringExtensions.toFirstLower(_name_12);
            _builder.append(_firstLower_3, "\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("Client.sendRequest(BASE_URI + \"/\" + \"");
        String _name_13 = property.getName();
        String _firstLower_4 = StringExtensions.toFirstLower(_name_13);
        _builder.append(_firstLower_4, "\t");
        _builder.append("\", CoapMethod.PUT, payload);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.newLine();
    return _builder.toString();
  }
}
