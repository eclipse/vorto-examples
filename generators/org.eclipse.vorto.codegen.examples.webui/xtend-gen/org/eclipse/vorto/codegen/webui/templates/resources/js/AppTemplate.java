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

import java.util.Map;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class AppTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("app.js");
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
    _builder.append("var iotConsoleMain = angular.module(\'");
    String _name = element.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("App\', [ \'ui.router\',\'ui.bootstrap\', ");
    {
      Map<String, String> _configurationProperties = context.getConfigurationProperties();
      String _orDefault = _configurationProperties.getOrDefault("swagger", "true");
      boolean _equalsIgnoreCase = _orDefault.equalsIgnoreCase("true");
      if (_equalsIgnoreCase) {
        _builder.append("\'swaggerUi\',");
      }
    }
    _builder.append("\'leaflet-directive\',\'nvd3\', \'frapontillo.gage\', \'login.controller\',");
    {
      Map<String, String> _configurationProperties_1 = context.getConfigurationProperties();
      String _orDefault_1 = _configurationProperties_1.getOrDefault("swagger", "true");
      boolean _equalsIgnoreCase_1 = _orDefault_1.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_1) {
        _builder.append("\'api.controller\',");
      }
    }
    _builder.append("\'");
    String _name_1 = element.getName();
    String _lowerCase_1 = _name_1.toLowerCase();
    _builder.append(_lowerCase_1, "");
    _builder.append(".list\', \'");
    String _name_2 = element.getName();
    String _lowerCase_2 = _name_2.toLowerCase();
    _builder.append(_lowerCase_2, "");
    _builder.append(".locate\', \'");
    String _name_3 = element.getName();
    String _lowerCase_3 = _name_3.toLowerCase();
    _builder.append(_lowerCase_3, "");
    _builder.append(".details\']);");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("iotConsoleMain.config([ \'$stateProvider\', \'$urlRouterProvider\', \'$httpProvider\',");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("function($stateProvider, $urlRouterProvider, $httpProvider) {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("$urlRouterProvider.otherwise(\"/\");");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("$stateProvider");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append(".state(\'browse\', {");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("url: \"/\",");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("templateUrl: \'partials/browser.html\',");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("controller: \'BrowserController\',");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("data: {");
    _builder.newLine();
    _builder.append("        \t\t");
    _builder.append("requireLogin: true");
    _builder.newLine();
    _builder.append("      \t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("})");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append(".state(\'details\', {");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("url: \"/details/:thingId\",");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("templateUrl: \'partials/details.html\',");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("controller: \'DetailsController\',");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("data: {");
    _builder.newLine();
    _builder.append("        \t\t");
    _builder.append("requireLogin: true");
    _builder.newLine();
    _builder.append("      \t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("})");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append(".state(\'locate\', {");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("url: \"/locate\",");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("templateUrl: \'partials/locator.html\',");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("controller: \'LocationController\',");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("data: {");
    _builder.newLine();
    _builder.append("        \t\t");
    _builder.append("requireLogin: true");
    _builder.newLine();
    _builder.append("      \t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("})");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties_2 = context.getConfigurationProperties();
      String _orDefault_2 = _configurationProperties_2.getOrDefault("swagger", "true");
      boolean _equalsIgnoreCase_2 = _orDefault_2.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_2) {
        _builder.append("    \t");
        _builder.append(".state(\'api\', {");
        _builder.newLine();
        _builder.append("    \t");
        _builder.append(" \t");
        _builder.append("url: \"/api\",");
        _builder.newLine();
        _builder.append("    \t");
        _builder.append(" \t");
        _builder.append("templateUrl: \'partials/api.html\',");
        _builder.newLine();
        _builder.append("    \t");
        _builder.append(" \t");
        _builder.append("controller: \'SwaggerController\',");
        _builder.newLine();
        _builder.append("    \t");
        _builder.append(" \t");
        _builder.append("data: {");
        _builder.newLine();
        _builder.append("    \t");
        _builder.append("   \t\t");
        _builder.append("requireLogin: true");
        _builder.newLine();
        _builder.append("    \t");
        _builder.append(" \t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("    \t");
        _builder.append(" ");
        _builder.append("})");
        _builder.newLine();
      }
    }
    _builder.append("    \t");
    _builder.append(".state(\'login\', {");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("url: \"/login\",");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("templateUrl: \'partials/login.html\',");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("controller: \'LoginController\',");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("data: {");
    _builder.newLine();
    _builder.append("        \t\t");
    _builder.append("requireLogin: false");
    _builder.newLine();
    _builder.append("      \t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("});");
    _builder.newLine();
    _builder.newLine();
    _builder.append("} ]).run(function($rootScope, $http, $location) {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("$rootScope.currentUser = \'undefined\';");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("$rootScope.currentUserSub = \'undefined\';");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("$rootScope.getUser = function() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$http.get(\'rest/identities/user\').success(function(data,status,headers,config) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$rootScope.currentUser = data.userId;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$rootScope.currentUserSub = data.userSub;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$rootScope.authenticated = true;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}).error(function(data,status,headers,config) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$rootScope.authenticated = false;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("console.log(\"User not authenticated. Redirecting user to login.\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$location.path(\"/login\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("});");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("};");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("$rootScope.getUser();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("$rootScope.logout = function() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$http.post(\'logout\',{}).success(function() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$rootScope.authenticated = false;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$location.path(\"/login\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}).error(function(data) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$location.path(\"/login\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$rootScope.authenticated = false;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("});");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("};");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("$rootScope.$on(\'$stateChangeStart\', function (event, toState, toParams) {");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("var requireLogin = toState.data.requireLogin;");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("if (requireLogin && $rootScope.authenticated == false) {");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("console.log(\"redirecting to login\");");
    _builder.newLine();
    _builder.append("      \t\t");
    _builder.append("event.preventDefault();");
    _builder.newLine();
    _builder.append("      \t\t");
    _builder.append("$location.path(\"/login\");");
    _builder.newLine();
    _builder.append("   \t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("  \t");
    _builder.append("});");
    _builder.newLine();
    _builder.append("});");
    _builder.newLine();
    return _builder.toString();
  }
}
