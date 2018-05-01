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

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.ITemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.datatype.EnumLiteral;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class MarkdownEnumTemplate implements ITemplate<org.eclipse.vorto.core.api.model.datatype.Enum> {
  @Override
  public String getContent(final org.eclipse.vorto.core.api.model.datatype.Enum en, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("## Enum *");
    String _name = en.getName();
    _builder.append(_name, "");
    _builder.append("*");
    _builder.newLineIfNotEmpty();
    _builder.append("### Unique Identification");
    _builder.newLine();
    _builder.append("<table>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<tr><td>Name:</td><td>");
    String _name_1 = en.getName();
    _builder.append(_name_1, "\t");
    _builder.append("</td></tr>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<tr><td>Namespace:</td><td>");
    String _namespace = en.getNamespace();
    _builder.append(_namespace, "\t");
    _builder.append("</td></tr>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<tr><td>Version:</td><td>");
    String _version = en.getVersion();
    _builder.append(_version, "\t");
    _builder.append("</td></tr>");
    _builder.newLineIfNotEmpty();
    _builder.append("</table>");
    _builder.newLine();
    _builder.append("### Description");
    _builder.newLine();
    String _description = en.getDescription();
    _builder.append(_description, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("### Literals");
    _builder.newLine();
    _builder.append("<table>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<tr><td>Literal</td><td>Description</td></tr>");
    _builder.newLine();
    {
      EList<EnumLiteral> _enums = en.getEnums();
      for(final EnumLiteral literal : _enums) {
        _builder.append("<tr><td>");
        String _name_2 = literal.getName();
        _builder.append(_name_2, "");
        _builder.append("</td><td>");
        String _description_1 = literal.getDescription();
        _builder.append(_description_1, "");
        _builder.append("</td></tr>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</table>");
    _builder.newLine();
    return _builder.toString();
  }
}
