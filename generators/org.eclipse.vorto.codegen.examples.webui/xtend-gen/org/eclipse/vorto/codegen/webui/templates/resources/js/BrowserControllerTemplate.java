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

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class BrowserControllerTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("browser-controller.js");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = context.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("-solution/src/main/resources/static/js");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("var listDevice = angular.module(\'");
    String _name = element.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append(".list\', []);");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("listDevice.controller(\'BrowserController\', [\'$rootScope\', \'$scope\', \'$location\', \'$http\', \'$state\',");
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
    _builder.append("\t\t");
    _builder.append("$scope.thingsMatrix = null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.listView = false;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.currentPage = 1;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.getThings = function() {\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$http.get(\"rest/devices\")");
    _builder.newLine();
    _builder.append("\t\t\t\t ");
    _builder.append(".success(function(data, status) {");
    _builder.newLine();
    _builder.append("\t\t\t\t \t");
    _builder.append("if (Object.keys(data).length > 0) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("$scope.things = data;");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("$scope.thingsMatrix = $scope.listToMatrix($scope.things, 5);");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("$scope.isLoading = false;");
    _builder.newLine();
    _builder.append("\t\t\t\t ");
    _builder.append("})");
    _builder.newLine();
    _builder.append("\t\t\t\t ");
    _builder.append(".error(function(data, status, headers, config, statusText) {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("console.log(\"Error: \" + JSON.stringify(data));");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("$scope.isLoading = false;");
    _builder.newLine();
    _builder.append("\t\t\t\t ");
    _builder.append("});");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}\t");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.listToMatrix = function(list, n) {");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("var grid = [], i = 0, x = list.length, col, row = -1;");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("for (var i = 0; i < x; i++) {");
    _builder.newLine();
    _builder.append("\t\t        ");
    _builder.append("col = i % n;");
    _builder.newLine();
    _builder.append("\t\t        ");
    _builder.append("if (col === 0) {");
    _builder.newLine();
    _builder.append("\t\t            ");
    _builder.append("grid[++row] = [];");
    _builder.newLine();
    _builder.append("\t\t        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t        ");
    _builder.append("grid[row][col] = list[i];");
    _builder.newLine();
    _builder.append("\t\t    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t    \t");
    _builder.append("return grid;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.normalizeThingId = function(thingId) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("var newId =  thingId.replace(\":\",\"_\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return newId.split(\".\").join(\"_\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.toggleBox = function(thingId) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("var boxes = document.getElementsByClassName(\"tiny-box\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("for (i = 0; i < boxes.length; i++) {");
    _builder.newLine();
    _builder.append("    \t\t\t");
    _builder.append("boxes[i].style.border = \"\";");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("document.getElementById(\'tinyBox:\' + thingId).style.border = \"4px solid #c2e1f5\";");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$(document).ready(function(){");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("$(\'[data-toggle=\"tooltip\"]\').tooltip();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("});");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.toggleView = function() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if($scope.listView) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("$scope.listView = false;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("$scope.listView = true;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.getThings();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}]);");
    _builder.newLine();
    return _builder.toString();
  }
}
