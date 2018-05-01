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
import org.eclipse.vorto.codegen.api.ITemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.utils.Utils;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class LatexSimplePropertyTemplate implements ITemplate<Property> {
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
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("\\\\\\hline\\multicolumn{3}{|p{15.5cm}|}{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Details:");
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
        _builder.append("The property is:");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\\begin{itemize}");
        _builder.newLine();
        {
          boolean _isReadable_1 = Utils.isReadable(property);
          if (_isReadable_1) {
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
            _builder.append("\\item writable");
            _builder.newLine();
          }
        }
        {
          boolean _isEventable_1 = Utils.isEventable(property);
          if (_isEventable_1) {
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("\\item eventable");
            _builder.newLine();
          }
        }
        _builder.append("\t");
        _builder.append("\\end{itemize}");
        _builder.newLine();
      }
    }
    {
      String _measurementUnit = Utils.getMeasurementUnit(property);
      boolean _notEquals = (!Objects.equal(_measurementUnit, null));
      if (_notEquals) {
        _builder.append("\t");
        _builder.append("Values are measured in ");
        String _measurementUnit_1 = Utils.getMeasurementUnit(property);
        _builder.append(_measurementUnit_1, "\t");
        _builder.append(".");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t");
      }
    }
    _builder.append("}\\\\");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("\\hline");
    _builder.newLine();
    _builder.append("\\end{tabular}\\\\\\\\\\\\");
    _builder.newLine();
    return _builder.toString();
  }
}
