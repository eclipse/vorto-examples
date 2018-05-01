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
package org.eclipse.vorto.codegen.webui.templates.resources.js;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class LocationControllerTemplate implements IFileTemplate<FunctionblockProperty> {
  @Override
  public String getFileName(final FunctionblockProperty context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("location-controller.js");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final FunctionblockProperty context) {
    StringConcatenation _builder = new StringConcatenation();
    EObject _eContainer = context.eContainer();
    String _name = ((InformationModel) _eContainer).getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("-solution/src/main/resources/static/js");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final FunctionblockProperty locationModelProperty, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("var locateDevices = angular.module(\'");
    EObject _eContainer = locationModelProperty.eContainer();
    String _name = ((InformationModel) _eContainer).getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append(".locate\', []);");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("locateDevices.controller(\'LocationController\', [\'$rootScope\', \'$scope\', \'$location\', \'$http\',\'$state\',");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("function($rootScope, $scope, $location, $http, $state) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.isLoading = true;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.things = [];");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.showThingsInMap = function() {           ");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("$http.get(\"rest/devices\")");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append(".success(function(data, status) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("$scope.things = data;");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("for (var i = 0; i < $scope.things.length; i++) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("$scope.things[i] = convertLocation($scope.things[i]);");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("$scope.singapore = {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("lat: 1.3580576343735706,");
    _builder.newLine();
    _builder.append("\t                    ");
    _builder.append("lng: 103.798828125,");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("zoom: 2");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("$scope.isLoading = false;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("})");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append(".error(function(data, status, headers, config, statusText) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("$scope.isLoading = false;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append(");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("var convertLocation = function(thing) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("var location = {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("lng : thing.");
    String _name_1 = locationModelProperty.getName();
    String _lowerCase_1 = _name_1.toLowerCase();
    _builder.append(_lowerCase_1, "\t\t\t\t");
    _builder.append(".status.longitude === \"\" ? 103.798828125 : parseFloat(thing.");
    String _name_2 = locationModelProperty.getName();
    String _lowerCase_2 = _name_2.toLowerCase();
    _builder.append(_lowerCase_2, "\t\t\t\t");
    _builder.append(".status.longitude),");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("lat: thing.");
    String _name_3 = locationModelProperty.getName();
    String _lowerCase_3 = _name_3.toLowerCase();
    _builder.append(_lowerCase_3, "\t\t\t\t");
    _builder.append(".status.latitude === \"\" ? 1.3580576343735706 :parseFloat(thing.");
    String _name_4 = locationModelProperty.getName();
    String _lowerCase_4 = _name_4.toLowerCase();
    _builder.append(_lowerCase_4, "\t\t\t\t");
    _builder.append(".status.latitude), ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("message: $scope.renderPopup(thing), ");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("icon : $scope.renderMarker(thing)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return location;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.renderPopup = function(thing) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return \"<p><b>\"+thing.name+\"</b></p><a href=\'#/details/\"+thing.id+\"\'>More Info</a><img width=\'64px\' height=\'64px\' src=\'http://vortorepo.apps.bosch-iot-cloud.com/rest/model/image/\"+thing.thingType.namespace+\"/\"+thing.thingType.name+\"/\"+thing.thingType.version+\"\'/>\";");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.renderMarker = function(thing) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("var icon = {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("iconUrl : \"img/marker-icon.png\"");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return icon;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.showThingsInMap();\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}]);");
    _builder.newLine();
    return _builder.toString();
  }
}
