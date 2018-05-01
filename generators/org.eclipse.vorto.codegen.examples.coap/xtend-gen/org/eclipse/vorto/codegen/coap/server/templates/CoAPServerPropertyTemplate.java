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

import org.eclipse.vorto.codegen.api.ITemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.utils.Utils;
import org.eclipse.vorto.core.api.model.datatype.ObjectPropertyType;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class CoAPServerPropertyTemplate implements ITemplate<Property> {
  public CoAPServerPropertyTemplate() {
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
    _builder.append("private ");
    String _propertyType = Utils.getPropertyType(property);
    _builder.append(_propertyType, "");
    _builder.append(" ");
    String _name = property.getName();
    String _firstLower = StringExtensions.toFirstLower(_name);
    _builder.append(_firstLower, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      boolean _isReadable = Utils.isReadable(property);
      if (_isReadable) {
        {
          PropertyType _type = property.getType();
          if ((_type instanceof ObjectPropertyType)) {
            _builder.append("public ");
            String _propertyType_1 = Utils.getPropertyType(property);
            _builder.append(_propertyType_1, "");
            _builder.append(" get");
            String _name_1 = property.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name_1);
            _builder.append(_firstUpper, "");
            _builder.append("(){");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("public ");
            String _propertyType_2 = Utils.getPropertyType(property);
            _builder.append(_propertyType_2, "");
            _builder.append(" get");
            String _name_2 = property.getName();
            String _firstUpper_1 = StringExtensions.toFirstUpper(_name_2);
            _builder.append(_firstUpper_1, "");
            _builder.append("() throws Exception {");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("return ");
        String _name_3 = property.getName();
        String _firstLower_1 = StringExtensions.toFirstLower(_name_3);
        _builder.append(_firstLower_1, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
      }
    }
    _builder.newLine();
    {
      boolean _isWritable = Utils.isWritable(property);
      if (_isWritable) {
        _builder.append("public void set");
        String _name_4 = property.getName();
        String _firstUpper_2 = StringExtensions.toFirstUpper(_name_4);
        _builder.append(_firstUpper_2, "");
        _builder.append("(");
        String _propertyType_3 = Utils.getPropertyType(property);
        _builder.append(_propertyType_3, "");
        _builder.append(" ");
        String _name_5 = property.getName();
        String _firstLower_2 = StringExtensions.toFirstLower(_name_5);
        _builder.append(_firstLower_2, "");
        _builder.append("){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("this.");
        String _name_6 = property.getName();
        String _firstLower_3 = StringExtensions.toFirstLower(_name_6);
        _builder.append(_firstLower_3, "\t");
        _builder.append(" = ");
        String _name_7 = property.getName();
        String _firstLower_4 = StringExtensions.toFirstLower(_name_7);
        _builder.append(_firstLower_4, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
      }
    }
    {
      boolean _isEventable = Utils.isEventable(property);
      if (_isEventable) {
        _builder.append("public boolean subscribe");
        String _name_8 = property.getName();
        String _firstUpper_3 = StringExtensions.toFirstUpper(_name_8);
        _builder.append(_firstUpper_3, "");
        _builder.append("(){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("// Add your code here ...");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("return false;");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public boolean unsubscribe");
        String _name_9 = property.getName();
        String _firstUpper_4 = StringExtensions.toFirstUpper(_name_9);
        _builder.append(_firstUpper_4, "");
        _builder.append("(){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("// Add your code here ...");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("return false;");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder.toString();
  }
}
