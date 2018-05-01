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
import org.eclipse.vorto.codegen.latex.tasks.template.LatexComplexPropertyTemplate;
import org.eclipse.vorto.codegen.latex.tasks.template.LatexSimplePropertyConstraintTemplate;
import org.eclipse.vorto.codegen.latex.tasks.template.LatexSimplePropertyTemplate;
import org.eclipse.vorto.codegen.utils.Utils;
import org.eclipse.vorto.core.api.model.datatype.Entity;
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class LatexEntityTemplate implements ITemplate<Entity> {
  private LatexSimplePropertyConstraintTemplate constraintTemplate;
  
  private LatexSimplePropertyTemplate simpleTemplate;
  
  private LatexComplexPropertyTemplate complexTemplate;
  
  public LatexEntityTemplate(final LatexSimplePropertyConstraintTemplate constraintTemplate, final LatexSimplePropertyTemplate simpleTemplate, final LatexComplexPropertyTemplate complexTemplate) {
    this.constraintTemplate = constraintTemplate;
    this.simpleTemplate = simpleTemplate;
    this.complexTemplate = complexTemplate;
  }
  
  @Override
  public String getContent(final Entity entity, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\subsection{");
    String _displayname = entity.getDisplayname();
    _builder.append(_displayname, "");
    _builder.append("}");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _description = entity.getDescription();
    _builder.append(_description, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("The properties of the data type ");
    String _displayname_1 = entity.getDisplayname();
    _builder.append(_displayname_1, "\t");
    _builder.append("\\footnote{Name: ");
    String _name = entity.getName();
    _builder.append(_name, "\t");
    _builder.append(", Namespace: ");
    String _namespace = entity.getNamespace();
    _builder.append(_namespace, "\t");
    _builder.append(", Version: ");
    String _version = entity.getVersion();
    _builder.append(_version, "\t");
    _builder.append(".} are described below:\\\\\\\\");
    _builder.newLineIfNotEmpty();
    {
      EList<Property> _properties = entity.getProperties();
      for(final Property property : _properties) {
        {
          boolean _isSimpleNumeric = Utils.isSimpleNumeric(property);
          if (_isSimpleNumeric) {
            _builder.append("\t");
            String _content = this.constraintTemplate.getContent(property, invocationContext);
            _builder.append(_content, "\t");
            _builder.newLineIfNotEmpty();
          } else {
            {
              PropertyType _type = property.getType();
              if ((_type instanceof PrimitivePropertyType)) {
                _builder.append("\t");
                String _content_1 = this.simpleTemplate.getContent(property, invocationContext);
                _builder.append(_content_1, "\t");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("\t");
                String _content_2 = this.complexTemplate.getContent(property, invocationContext);
                _builder.append(_content_2, "\t");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder.toString();
  }
}
