/**
 * Copyright (c) 2015, 2016 Bosch Software Innovations GmbH and others.
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
package org.eclipse.vorto.codegen.protobuf.templates;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.protobuf.templates.ProtobufTemplate;
import org.eclipse.vorto.core.api.model.datatype.EnumLiteral;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ProtobufEnumTemplate extends ProtobufTemplate<org.eclipse.vorto.core.api.model.datatype.Enum> {
  @Override
  public String getContent(final org.eclipse.vorto.core.api.model.datatype.Enum element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _description = element.getDescription();
      boolean _notEquals = (!Objects.equal(_description, null));
      if (_notEquals) {
        _builder.append("//");
        String _description_1 = element.getDescription();
        _builder.append(_description_1, "");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("enum ");
    String _name = element.getName();
    _builder.append(_name, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<EnumLiteral> _enums = element.getEnums();
      boolean _notEquals_1 = (!Objects.equal(_enums, null));
      if (_notEquals_1) {
        int counter = 0;
        _builder.newLineIfNotEmpty();
        {
          EList<EnumLiteral> _enums_1 = element.getEnums();
          for(final EnumLiteral literal : _enums_1) {
            String _name_1 = literal.getName();
            _builder.append(_name_1, "");
            _builder.append(" = ");
            int _plusPlus = counter++;
            _builder.append(_plusPlus, "");
            _builder.append("; ");
            {
              String _description_2 = literal.getDescription();
              boolean _notEquals_2 = (!Objects.equal(_description_2, null));
              if (_notEquals_2) {
                _builder.append("//");
                String _description_3 = literal.getDescription();
                _builder.append(_description_3, "");
              }
            }
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
