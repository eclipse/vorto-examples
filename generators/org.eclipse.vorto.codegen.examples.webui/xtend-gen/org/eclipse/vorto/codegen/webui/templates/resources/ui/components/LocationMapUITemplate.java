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

import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.resources.ui.IFunctionBlockUITemplate;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class LocationMapUITemplate implements IFunctionBlockUITemplate {
  private String longitudeProperty;
  
  private String latitudeProperty;
  
  public LocationMapUITemplate(final String longitudeProperty, final String latitudeProperty) {
    this.longitudeProperty = longitudeProperty;
    this.latitudeProperty = latitudeProperty;
  }
  
  @Override
  public String renderJavascript(final FunctionblockProperty element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("$scope.set");
    String _name = element.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append(" = function() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("$scope.thingLocation = {");
    _builder.newLine();
    _builder.append("\t            \t");
    _builder.append("lat: $scope.thing.");
    String _name_1 = element.getName();
    _builder.append(_name_1, "\t            \t");
    _builder.append(".");
    _builder.append(this.latitudeProperty, "\t            \t");
    _builder.append(" === \"\" ? 1.3580576343735706 : parseFloat($scope.thing.");
    String _name_2 = element.getName();
    _builder.append(_name_2, "\t            \t");
    _builder.append(".");
    _builder.append(this.latitudeProperty, "\t            \t");
    _builder.append("),");
    _builder.newLineIfNotEmpty();
    _builder.append("\t                ");
    _builder.append("lng: $scope.thing.");
    String _name_3 = element.getName();
    _builder.append(_name_3, "\t                ");
    _builder.append(".");
    _builder.append(this.longitudeProperty, "\t                ");
    _builder.append(" === \"\" ? 103.798828125 : parseFloat($scope.thing.");
    String _name_4 = element.getName();
    _builder.append(_name_4, "\t                ");
    _builder.append(".");
    _builder.append(this.longitudeProperty, "\t                ");
    _builder.append("),");
    _builder.newLineIfNotEmpty();
    _builder.append("\t                ");
    _builder.append("zoom: 8");
    _builder.newLine();
    _builder.append("\t            ");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t            ");
    _builder.append("$scope.markers = {");
    _builder.newLine();
    _builder.append("\t            \t");
    _builder.append("m1 : {");
    _builder.newLine();
    _builder.append("\t            \t\t");
    _builder.append("lat: $scope.thing.");
    String _name_5 = element.getName();
    _builder.append(_name_5, "\t            \t\t");
    _builder.append(".");
    _builder.append(this.latitudeProperty, "\t            \t\t");
    _builder.append(" === \"\" ? 1.3580576343735706 : parseFloat($scope.thing.");
    String _name_6 = element.getName();
    _builder.append(_name_6, "\t            \t\t");
    _builder.append(".");
    _builder.append(this.latitudeProperty, "\t            \t\t");
    _builder.append("),");
    _builder.newLineIfNotEmpty();
    _builder.append("\t                \t");
    _builder.append("lng: $scope.thing.");
    String _name_7 = element.getName();
    _builder.append(_name_7, "\t                \t");
    _builder.append(".");
    _builder.append(this.longitudeProperty, "\t                \t");
    _builder.append(" === \"\" ? 103.798828125 : parseFloat($scope.thing.");
    String _name_8 = element.getName();
    _builder.append(_name_8, "\t                \t");
    _builder.append(".");
    _builder.append(this.longitudeProperty, "\t                \t");
    _builder.append("),");
    _builder.newLineIfNotEmpty();
    _builder.append("\t                \t");
    _builder.append("icon : { iconUrl : \'webjars/leaflet/1.0.0/images/marker-icon.png\'}");
    _builder.newLine();
    _builder.append("\t                ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t            ");
    _builder.append("}; ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    return _builder.toString();
  }
  
  @Override
  public String renderHtml(final FunctionblockProperty fbProperty, final InvocationContext ctx) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<leaflet markers=\"markers\" lf-center=\"thingLocation\" width=\"100%\" height=\"250px\"></leaflet>");
    _builder.newLine();
    return _builder.toString();
  }
}
