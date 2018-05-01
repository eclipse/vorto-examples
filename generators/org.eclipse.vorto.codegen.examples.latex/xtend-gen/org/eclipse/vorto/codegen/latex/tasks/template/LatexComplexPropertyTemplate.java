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

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.ITemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.utils.Utils;
import org.eclipse.vorto.core.api.model.datatype.EnumLiteral;
import org.eclipse.vorto.core.api.model.datatype.ObjectPropertyType;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.vorto.core.api.model.datatype.Type;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class LatexComplexPropertyTemplate implements ITemplate<Property> {
  @Override
  public String getContent(final Property property, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\begin{tabular}{|p{4.5cm}|p{4cm}|p{7cm}|}");
    _builder.newLine();
    _builder.append("\\hline");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\\multicolumn{3}{|p{15.5cm}|}{\\textbf{Property}}\\\\");
    _builder.newLine();
    _builder.append("\\hline");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Name &  Data Type & Description\\\\");
    _builder.newLine();
    _builder.append("\\hline\\hline");
    _builder.newLine();
    _builder.append("\t");
    String _name = property.getName();
    String _replace = _name.replace("_", "\\_");
    _builder.append(_replace, "\t");
    _builder.append(" & ");
    String _propertyType = Utils.getPropertyType(property);
    _builder.append(_propertyType, "\t");
    _builder.append(" & ");
    String _description = property.getDescription();
    _builder.append(_description, "\t");
    _builder.append("\\\\");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("\\hline");
    _builder.newLine();
    {
      PropertyType _type = property.getType();
      if ((_type instanceof ObjectPropertyType)) {
        _builder.append("\t");
        _builder.newLine();
        {
          PropertyType _type_1 = property.getType();
          Type _type_2 = ((ObjectPropertyType) _type_1).getType();
          if ((_type_2 instanceof org.eclipse.vorto.core.api.model.datatype.Enum)) {
            _builder.append("\t");
            _builder.append("\\multicolumn{3}{|p{15.5cm}|}{");
            _builder.newLine();
            {
              boolean _or = false;
              boolean _or_1 = false;
              boolean _isEventable = Utils.isEventable(property);
              if (_isEventable) {
                _or_1 = true;
              } else {
                boolean _isReadable = Utils.isReadable(property);
                _or_1 = _isReadable;
              }
              if (_or_1) {
                _or = true;
              } else {
                boolean _isWritable = Utils.isWritable(property);
                _or = _isWritable;
              }
              if (_or) {
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("The property is:");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\\begin{itemize}");
                _builder.newLine();
                {
                  boolean _isReadable_1 = Utils.isReadable(property);
                  if (_isReadable_1) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\\item readable");
                    _builder.newLine();
                  }
                }
                {
                  boolean _isWritable_1 = Utils.isWritable(property);
                  if (_isWritable_1) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\\item writable");
                    _builder.newLine();
                  }
                }
                {
                  boolean _isEventable_1 = Utils.isEventable(property);
                  if (_isEventable_1) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\\item eventable");
                    _builder.newLine();
                  }
                }
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\\end{itemize}");
                _builder.newLine();
              }
            }
            _builder.append("\t");
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("The specified data type for this property is an Enum. Possible values are:");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("\\begin{itemize}");
            _builder.newLine();
            {
              PropertyType _type_3 = property.getType();
              Type _type_4 = ((ObjectPropertyType) _type_3).getType();
              EList<EnumLiteral> _enums = ((org.eclipse.vorto.core.api.model.datatype.Enum) _type_4).getEnums();
              for(final EnumLiteral literal : _enums) {
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("\\item ");
                String _name_1 = literal.getName();
                String _replace_1 = _name_1.replace("_", "\\_");
                _builder.append(_replace_1, "\t\t\t");
                _builder.append(" (");
                String _description_1 = literal.getDescription();
                _builder.append(_description_1, "\t\t\t");
                _builder.append(")");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("\\end{itemize}");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("}\\\\");
            _builder.newLine();
          } else {
            _builder.append("\t");
            _builder.append("\\multicolumn{3}{|p{15.5cm}|}{");
            _builder.newLine();
            {
              boolean _or_2 = false;
              boolean _or_3 = false;
              boolean _isEventable_2 = Utils.isEventable(property);
              if (_isEventable_2) {
                _or_3 = true;
              } else {
                boolean _isReadable_2 = Utils.isReadable(property);
                _or_3 = _isReadable_2;
              }
              if (_or_3) {
                _or_2 = true;
              } else {
                boolean _isWritable_2 = Utils.isWritable(property);
                _or_2 = _isWritable_2;
              }
              if (_or_2) {
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("The property is:");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\\begin{itemize}");
                _builder.newLine();
                {
                  boolean _isReadable_3 = Utils.isReadable(property);
                  if (_isReadable_3) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\\item readable");
                    _builder.newLine();
                  }
                }
                {
                  boolean _isWritable_3 = Utils.isWritable(property);
                  if (_isWritable_3) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\\item writable");
                    _builder.newLine();
                  }
                }
                {
                  boolean _isEventable_3 = Utils.isEventable(property);
                  if (_isEventable_3) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\\item eventable");
                    _builder.newLine();
                  }
                }
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\\end{itemize}");
                _builder.newLine();
              }
            }
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("The property type ");
            String _propertyType_1 = Utils.getPropertyType(property);
            _builder.append(_propertyType_1, "\t\t");
            _builder.append(" is a complex type. For a detailed description");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("please refer to the related section.}\\\\");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("\\hline");
    _builder.newLine();
    _builder.append("\\end{tabular}\\\\\\\\\\\\");
    _builder.newLine();
    return _builder.toString();
  }
}
