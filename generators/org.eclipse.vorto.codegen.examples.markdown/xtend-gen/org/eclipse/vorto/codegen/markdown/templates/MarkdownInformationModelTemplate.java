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
import org.eclipse.vorto.codegen.markdown.templates.MarkdownEntityTemplate;
import org.eclipse.vorto.codegen.markdown.templates.MarkdownEnumTemplate;
import org.eclipse.vorto.codegen.markdown.templates.MarkdownFunctionBlockTemplate;
import org.eclipse.vorto.codegen.utils.Utils;
import org.eclipse.vorto.core.api.model.datatype.Entity;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class MarkdownInformationModelTemplate implements ITemplate<InformationModel> {
  private MarkdownFunctionBlockTemplate fbTemplate;
  
  private MarkdownEntityTemplate entityTemplate;
  
  private MarkdownEnumTemplate enumTemplate;
  
  public MarkdownInformationModelTemplate(final MarkdownFunctionBlockTemplate fbTemplate, final MarkdownEntityTemplate entityTemplate, final MarkdownEnumTemplate enumTemplate) {
    this.fbTemplate = fbTemplate;
    this.entityTemplate = entityTemplate;
    this.enumTemplate = enumTemplate;
  }
  
  @Override
  public String getContent(final InformationModel im, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("# Information Model *");
    String _name = im.getName();
    _builder.append(_name, "");
    _builder.append("*");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("### Unique Identification");
    _builder.newLine();
    _builder.append("<table>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<tr><td>Name:</td><td>");
    String _name_1 = im.getName();
    _builder.append(_name_1, "\t");
    _builder.append("</td></tr>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<tr><td>Namespace:</td><td>");
    String _namespace = im.getNamespace();
    _builder.append(_namespace, "\t");
    _builder.append("</td></tr>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<tr><td>Version:</td><td>");
    String _version = im.getVersion();
    _builder.append(_version, "\t");
    _builder.append("</td></tr>");
    _builder.newLineIfNotEmpty();
    _builder.append("</table>");
    _builder.newLine();
    _builder.append("### Description");
    _builder.newLine();
    String _description = im.getDescription();
    _builder.append(_description, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("### Functionblock Overview");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<table>");
    _builder.newLine();
    _builder.append("<tr><td>Functionblock</td><td>Name</td><td>Description</td></tr>");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties = im.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        _builder.append("<tr><td>");
        FunctionblockModel _type = fbProperty.getType();
        String _name_2 = _type.getName();
        _builder.append(_name_2, "");
        _builder.append("</td><td>");
        String _name_3 = fbProperty.getName();
        _builder.append(_name_3, "");
        _builder.append("</td><td>");
        String _description_1 = fbProperty.getDescription();
        _builder.append(_description_1, "");
        _builder.append("</td></tr>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</table>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("# Functionblocks");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_1 = im.getProperties();
      for(final FunctionblockProperty fbProperty_1 : _properties_1) {
        FunctionblockModel _type_1 = fbProperty_1.getType();
        String _content = this.fbTemplate.getContent(_type_1, invocationContext);
        _builder.append(_content, "");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
      }
    }
    _builder.newLine();
    _builder.append("# Entities");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_2 = im.getProperties();
      for(final FunctionblockProperty fbProperty_2 : _properties_2) {
        {
          FunctionblockModel _type_2 = fbProperty_2.getType();
          FunctionBlock _functionblock = _type_2.getFunctionblock();
          EList<Entity> _referencedEntities = Utils.getReferencedEntities(_functionblock);
          for(final Entity type : _referencedEntities) {
            String _content_1 = this.entityTemplate.getContent(type, invocationContext);
            _builder.append(_content_1, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.newLine();
    _builder.append("# Enums");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_3 = im.getProperties();
      for(final FunctionblockProperty fbProperty_3 : _properties_3) {
        {
          FunctionblockModel _type_3 = fbProperty_3.getType();
          FunctionBlock _functionblock_1 = _type_3.getFunctionblock();
          EList<org.eclipse.vorto.core.api.model.datatype.Enum> _referencedEnums = Utils.getReferencedEnums(_functionblock_1);
          for(final org.eclipse.vorto.core.api.model.datatype.Enum type_1 : _referencedEnums) {
            String _content_2 = this.enumTemplate.getContent(type_1, invocationContext);
            _builder.append(_content_2, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.newLine();
    return _builder.toString();
  }
}
