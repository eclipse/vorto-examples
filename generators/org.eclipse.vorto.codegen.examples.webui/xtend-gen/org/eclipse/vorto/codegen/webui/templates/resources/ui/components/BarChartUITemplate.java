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

import java.util.List;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.resources.ui.IFunctionBlockUITemplate;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class BarChartUITemplate implements IFunctionBlockUITemplate {
  private List<String> properties;
  
  public BarChartUITemplate(final List<String> properties) {
    this.properties = properties;
  }
  
  @Override
  public String renderJavascript(final FunctionblockProperty element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("$scope.set");
    String _name = element.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append(" = function() {\t\t\t\t\t\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t         ");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("$scope.");
    String _name_1 = element.getName();
    String _lowerCase = _name_1.toLowerCase();
    _builder.append(_lowerCase, "\t\t\t\t\t");
    _builder.append("Options = {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("chart: {");
    _builder.newLine();
    _builder.append("\t\t       \t\t\t\t");
    _builder.append("type: \'discreteBarChart\',");
    _builder.newLine();
    _builder.append("\t\t       \t\t\t\t");
    _builder.append("height: 250,");
    _builder.newLine();
    _builder.append("\t\t        \t\t\t");
    _builder.append("margin : {");
    _builder.newLine();
    _builder.append("\t\t            \t\t\t");
    _builder.append("top: 20,");
    _builder.newLine();
    _builder.append("\t\t            \t\t\t");
    _builder.append("right: 20,");
    _builder.newLine();
    _builder.append("\t\t           \t\t\t\t");
    _builder.append("bottom: 60,");
    _builder.newLine();
    _builder.append("\t\t            \t\t\t");
    _builder.append("left: 75");
    _builder.newLine();
    _builder.append("\t\t        \t\t\t");
    _builder.append("},");
    _builder.newLine();
    _builder.append("\t\t        \t\t\t");
    _builder.append("x: function(d){ return d.label; },");
    _builder.newLine();
    _builder.append("\t\t        \t\t\t");
    _builder.append("y: function(d){ return d.value; },");
    _builder.newLine();
    _builder.append("\t\t        \t\t\t");
    _builder.append("forceY: [0, 0], ");
    _builder.newLine();
    _builder.append("\t\t        \t\t\t");
    _builder.append("showValues: true,");
    _builder.newLine();
    _builder.append("\t\t        \t\t\t");
    _builder.append("valueFormat: function(d){");
    _builder.newLine();
    _builder.append("\t\t            \t\t\t");
    _builder.append("return d3.format(\',.2f\')(d);");
    _builder.newLine();
    _builder.append("\t\t       \t\t\t\t");
    _builder.append("},");
    _builder.newLine();
    _builder.append("\t\t       \t\t\t\t");
    _builder.append("transitionDuration: 500,");
    _builder.newLine();
    _builder.append("\t\t        \t\t\t");
    _builder.append("xAxis: {");
    _builder.newLine();
    _builder.append("\t\t        \t\t\t");
    _builder.append("},");
    _builder.newLine();
    _builder.append("\t\t        \t\t\t");
    _builder.append("yAxis: { ");
    _builder.newLine();
    _builder.append("\t\t        \t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t    \t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t       \t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t       \t\t");
    _builder.newLine();
    _builder.append("\t\t       \t\t");
    _builder.append("$scope.");
    String _name_2 = element.getName();
    String _lowerCase_1 = _name_2.toLowerCase();
    _builder.append(_lowerCase_1, "\t\t       \t\t");
    _builder.append("Data = [{");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t   \t\t\t\t");
    _builder.append("key: \"Cumulative Return\",");
    _builder.newLine();
    _builder.append("\t\t    \t\t\t");
    _builder.append("values: [");
    _builder.newLine();
    {
      boolean _hasElements = false;
      for(final String prop : this.properties) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "\t\t    \t\t\t\t");
        }
        _builder.append("\t\t    \t\t\t\t");
        _builder.append("{ \"label\" : \"");
        _builder.append(prop, "\t\t    \t\t\t\t");
        _builder.append("\" , \"value\" : $scope.thing.");
        String _name_3 = element.getName();
        String _lowerCase_2 = _name_3.toLowerCase();
        _builder.append(_lowerCase_2, "\t\t    \t\t\t\t");
        _builder.append(".");
        _builder.append(prop, "\t\t    \t\t\t\t");
        _builder.append(" }");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t    \t\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("}]; ");
    _builder.newLine();
    _builder.append("\t\t       \t");
    _builder.append("};");
    _builder.newLine();
    return _builder.toString();
  }
  
  @Override
  public String renderHtml(final FunctionblockProperty fbProperty, final InvocationContext ctx) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<div nvd3 options=\"");
    String _name = fbProperty.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("Options\" data=\"");
    String _name_1 = fbProperty.getName();
    String _lowerCase_1 = _name_1.toLowerCase();
    _builder.append(_lowerCase_1, "");
    _builder.append("Data\"></div>");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
}
