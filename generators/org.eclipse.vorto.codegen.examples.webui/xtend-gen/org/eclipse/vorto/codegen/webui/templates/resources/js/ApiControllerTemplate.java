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
public class ApiControllerTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("api-controller.js");
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
    _builder.append("var apiController = angular.module(\'api.controller\', []);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("apiController.controller(\'SwaggerController\', [\'$rootScope\', \'$scope\', \'$location\', \'$http\', \'$sce\',");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("function($rootScope, $scope, $location, $http, $sce) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.isLoading = true;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.url = $scope.swaggerUrl = \'v2/api-docs\';");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.defaultErrorHandler = function(data, status) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("alert(\'Error Loading Swagger API!\');");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}]);");
    _builder.newLine();
    return _builder.toString();
  }
}
