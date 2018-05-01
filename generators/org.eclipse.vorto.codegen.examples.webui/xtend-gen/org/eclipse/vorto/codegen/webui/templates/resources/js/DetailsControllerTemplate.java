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

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.resources.ui.IFunctionBlockUITemplate;
import org.eclipse.vorto.codegen.webui.templates.resources.ui.UIComponentFactory;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class DetailsControllerTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("details-controller.js");
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
    _builder.append("var ");
    String _name = element.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("Details = angular.module(\'");
    String _name_1 = element.getName();
    String _lowerCase_1 = _name_1.toLowerCase();
    _builder.append(_lowerCase_1, "");
    _builder.append(".details\', []);");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    String _name_2 = element.getName();
    String _lowerCase_2 = _name_2.toLowerCase();
    _builder.append(_lowerCase_2, "");
    _builder.append("Details.controller(\'DetailsController\', [\'$rootScope\', \'$scope\', \'$location\', \'$http\',\'$state\',\'$stateParams\',");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("function($rootScope, $scope, $location, $http, $state,$stateParams) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// Stuff for websockets\tin order to automatically retrieve device values without refreshing\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.websocket = {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("socket      : null,");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("stompClient : null");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.thing = null;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.getThing = function() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$http.get(\"rest/devices/\"+$stateParams.thingId)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append(".success(function(data, status) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("$scope.thing = data;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("$scope.isLoading = false;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("$scope.errorMessage = null;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties = element.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        _builder.append("\t\t\t\t");
        _builder.append("$scope.set");
        String _name_3 = fbProperty.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_3);
        _builder.append(_firstUpper, "\t\t\t\t");
        _builder.append("();");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("$scope.initSockets();");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("})");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append(".error(function(data, status, headers, config, statusText) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("$scope.isLoading = false;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("$scope.errorMessage = \"Could not load details!\";");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("});");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_1 = element.getProperties();
      for(final FunctionblockProperty fbProperty_1 : _properties_1) {
        _builder.append("\t\t");
        FunctionblockModel _type = fbProperty_1.getType();
        IFunctionBlockUITemplate template = UIComponentFactory.getByModelId(_type, context);
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        String _renderJavascript = template.renderJavascript(fbProperty_1, context);
        _builder.append(_renderJavascript, "\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("       \t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.reconnect = function() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("setTimeout($scope.initSockets, 10000);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.subscribeToThingChanges = function() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$scope.websocket.stompClient.subscribe(\"/topic/device/\" + $scope.thing.thingId, function(status) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("$scope.$apply(function() {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("$scope.thing = angular.fromJson(status.body);");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_2 = element.getProperties();
      for(final FunctionblockProperty fbProperty_2 : _properties_2) {
        _builder.append("\t\t\t\t\t");
        _builder.append("$scope.set");
        String _name_4 = fbProperty_2.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_4);
        _builder.append(_firstUpper_1, "\t\t\t\t\t");
        _builder.append("();");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t\t\t");
    _builder.append("});");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("});");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("// start subscribing to this thing ID");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$scope.websocket.stompClient.send(\"/");
    String _name_5 = element.getName();
    String _lowerCase_3 = _name_5.toLowerCase();
    _builder.append(_lowerCase_3, "\t\t\t");
    _builder.append("/");
    String _name_6 = element.getName();
    String _lowerCase_4 = _name_6.toLowerCase();
    _builder.append(_lowerCase_4, "\t\t\t");
    _builder.append("endpoint/subscribe\", {}, $scope.thing.thingId);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.$on(\"$destroy\", function() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if ($scope.websocket && $scope.websocket.stompClient) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("$scope.websocket.stompClient.send(\"/");
    String _name_7 = element.getName();
    String _lowerCase_5 = _name_7.toLowerCase();
    _builder.append(_lowerCase_5, "\t\t\t\t");
    _builder.append("/");
    String _name_8 = element.getName();
    String _lowerCase_6 = _name_8.toLowerCase();
    _builder.append(_lowerCase_6, "\t\t\t\t");
    _builder.append("endpoint/unsubscribe\", {}, $scope.thing.thingId);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("});");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.initSockets = function() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$scope.websocket.socket = new SockJS(\'");
    String _name_9 = element.getName();
    String _lowerCase_7 = _name_9.toLowerCase();
    _builder.append(_lowerCase_7, "\t\t\t");
    _builder.append("endpoint\');");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("$scope.websocket.stompClient = Stomp.over($scope.websocket.socket);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$scope.websocket.stompClient.connect({}, function() {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("console.log(\"Connected to websocket. Now subscribing.\");");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("$scope.subscribeToThingChanges();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("});");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("$scope.websocket.stompClient.onclose = $scope.reconnect;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$scope.getThing();\t \t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}]);");
    _builder.newLine();
    return _builder.toString();
  }
}
