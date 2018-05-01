/**
 * Copyright (c) 2015-2016 Bosch Software Innovations GmbH and others.
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
package org.eclipse.vorto.codegen.webui.templates.resources.ui.components;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.resources.ui.IFunctionBlockUITemplate;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.functionblock.Status;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class DefaultUITemplate implements IFunctionBlockUITemplate {
  @Override
  public String renderHtml(final FunctionblockProperty fbProperty, final InvocationContext ctx) {
    StringConcatenation _builder = new StringConcatenation();
    {
      FunctionblockModel _type = fbProperty.getType();
      FunctionBlock _functionblock = _type.getFunctionblock();
      Status _status = _functionblock.getStatus();
      boolean _notEquals = (!Objects.equal(_status, null));
      if (_notEquals) {
        {
          FunctionblockModel _type_1 = fbProperty.getType();
          FunctionBlock _functionblock_1 = _type_1.getFunctionblock();
          Status _status_1 = _functionblock_1.getStatus();
          EList<Property> _properties = _status_1.getProperties();
          for(final Property statusProperty : _properties) {
            _builder.append("<dl class=\"dl-horizontal\">");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("<div class=\"row\">");
            _builder.newLine();
            _builder.append("\t\t");
            _builder.append("<div class=\"col-sm-6\">");
            _builder.newLine();
            _builder.append("\t\t\t");
            _builder.append("<label class=\"control-label input-label\" style=\"margin-left:10px\">");
            String _name = statusProperty.getName();
            _builder.append(_name, "\t\t\t");
            _builder.append(":</label>");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("</div>");
            _builder.newLine();
            _builder.append("\t\t");
            _builder.append("<div class=\"col-sm-6\">");
            _builder.newLine();
            _builder.append("\t\t\t\t");
            _builder.append("<input readonly type=\"text\" name=\"");
            String _name_1 = statusProperty.getName();
            _builder.append(_name_1, "\t\t\t\t");
            _builder.append("\" ");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t\t");
            _builder.append("value={{thing.");
            String _name_2 = fbProperty.getName();
            _builder.append(_name_2, "\t\t\t\t\t");
            _builder.append(".");
            String _name_3 = statusProperty.getName();
            _builder.append(_name_3, "\t\t\t\t\t");
            _builder.append("}}");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t\t");
            _builder.append("class=\"ng-pristine ng-valid col-xs-10\" pull-right\"/>");
            _builder.newLine();
            _builder.append("\t\t");
            _builder.append("</div>");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("</div>");
            _builder.newLine();
            _builder.append("</dl>");
            _builder.newLine();
          }
        }
      }
    }
    _builder.newLine();
    return _builder.toString();
  }
  
  @Override
  public String renderJavascript(final FunctionblockProperty fbProperty, final InvocationContext ctx) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("$scope.set");
    String _name = fbProperty.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append(" = function() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    return _builder.toString();
  }
}
