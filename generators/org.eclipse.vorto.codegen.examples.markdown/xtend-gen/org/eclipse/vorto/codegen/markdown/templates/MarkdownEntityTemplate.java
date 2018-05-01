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
import org.eclipse.vorto.codegen.markdown.utils.Utils;
import org.eclipse.vorto.core.api.model.datatype.Entity;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class MarkdownEntityTemplate implements ITemplate<Entity> {
  @Override
  public String getContent(final Entity entity, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("## Entity *");
    String _name = entity.getName();
    _builder.append(_name, "");
    _builder.append("*");
    _builder.newLineIfNotEmpty();
    _builder.append("### Unique Identification");
    _builder.newLine();
    _builder.append("<table>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<tr><td>Name:</td><td>");
    String _name_1 = entity.getName();
    _builder.append(_name_1, "\t");
    _builder.append("</td></tr>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<tr><td>Namespace:</td><td>");
    String _namespace = entity.getNamespace();
    _builder.append(_namespace, "\t");
    _builder.append("</td></tr>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<tr><td>Version:</td><td>");
    String _version = entity.getVersion();
    _builder.append(_version, "\t");
    _builder.append("</td></tr>");
    _builder.newLineIfNotEmpty();
    _builder.append("</table>");
    _builder.newLine();
    _builder.append("### Description");
    _builder.newLine();
    String _description = entity.getDescription();
    _builder.append(_description, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("### Properties");
    _builder.newLine();
    _builder.append("<table>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<tr><td>Name</td><td>Type</td><td>Description</td></tr>");
    _builder.newLine();
    {
      EList<Property> _properties = entity.getProperties();
      for(final Property property : _properties) {
        _builder.append("\t");
        _builder.append("<tr><td>");
        String _name_2 = property.getName();
        _builder.append(_name_2, "\t");
        _builder.append("</td><td>");
        String _propertyType = Utils.getPropertyType(property);
        _builder.append(_propertyType, "\t");
        _builder.append("</td><td>");
        String _description_1 = property.getDescription();
        _builder.append(_description_1, "\t");
        _builder.append("</td></tr>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</table>");
    _builder.newLine();
    return _builder.toString();
  }
}
