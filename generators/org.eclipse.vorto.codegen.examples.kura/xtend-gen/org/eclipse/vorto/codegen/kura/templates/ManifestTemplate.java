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
package org.eclipse.vorto.codegen.kura.templates;

import java.util.Map;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.kura.templates.Utils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ManifestTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("MANIFEST.MF");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _basePath = Utils.getBasePath(context);
    _builder.append(_basePath, "");
    _builder.append("/META-INF");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Manifest-Version: 1.0");
    _builder.newLine();
    _builder.append("Bundle-ManifestVersion: 2");
    _builder.newLine();
    _builder.append("Bundle-Name: ");
    String _name = element.getName();
    _builder.append(_name, "");
    _builder.newLineIfNotEmpty();
    _builder.append("Bundle-SymbolicName: ");
    String _javaPackage = Utils.getJavaPackage(element);
    _builder.append(_javaPackage, "");
    _builder.newLineIfNotEmpty();
    _builder.append("Bundle-Version: 1.0.0.qualifier");
    _builder.newLine();
    _builder.append("Bundle-Vendor: Eclipse Vorto");
    _builder.newLine();
    _builder.append("Bundle-RequiredExecutionEnvironment: JavaSE-1.8");
    _builder.newLine();
    _builder.append("Service-Component: OSGI-INF/*.xml");
    _builder.newLine();
    _builder.append("Bundle-ActivationPolicy: lazy");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties = context.getConfigurationProperties();
      String _orDefault = _configurationProperties.getOrDefault("boschcloud", "false");
      boolean _equalsIgnoreCase = _orDefault.equalsIgnoreCase("true");
      if (_equalsIgnoreCase) {
        _builder.append("Bundle-ClassPath: lib/async-http-client-2.0.0.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/cr-integration-api-3.3.0.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/cr-integration-client-2.4.1.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/cr-integration-client-osgi-2.4.1.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/cr-json-1.6.0.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/cr-model-3.3.0.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/javassist-3.20.0-GA.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/netty-buffer-4.0.36.Final.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/netty-codec-4.0.36.Final.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/netty-codec-dns-2.0.0.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/netty-codec-http-4.0.36.Final.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/netty-common-4.0.36.Final.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/netty-handler-4.0.36.Final.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/netty-reactive-streams-1.0.4.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/netty-resolver-2.0.0.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/netty-resolver-dns-2.0.0.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/netty-transport-4.0.36.Final.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/reactive-streams-1.0.0.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/reactor-bus-2.0.7.RELEASE.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/reactor-core-2.0.7.RELEASE.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/stomp-client-2.4.1.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/stomp-common-2.4.1.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("lib/things-model-2.4.1.jar,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("secret/,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append(".");
        _builder.newLine();
      } else {
        {
          Map<String, String> _configurationProperties_1 = context.getConfigurationProperties();
          String _orDefault_1 = _configurationProperties_1.getOrDefault("boschhub", "false");
          boolean _equalsIgnoreCase_1 = _orDefault_1.equalsIgnoreCase("true");
          if (_equalsIgnoreCase_1) {
            _builder.append("Bundle-ClassPath: lib/org.eclipse.paho.client.mqttv3-1.0.2.jar,");
            _builder.newLine();
            _builder.append(" ");
            _builder.append("lib/gson-2.8.1.jar,");
            _builder.newLine();
            _builder.append(" ");
            _builder.append(".");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("Import-Package: org.slf4j;version=\"1.7.21\",");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("org.eclipse.kura;version=\"1.3.0\",");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("org.eclipse.kura.configuration;version=\"1.1.2\",");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties_2 = context.getConfigurationProperties();
      String _orDefault_2 = _configurationProperties_2.getOrDefault("bluetooth", "false");
      boolean _equalsIgnoreCase_2 = _orDefault_2.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_2) {
        _builder.append(" org.eclipse.kura.bluetooth;version=\"1.4.0\",");
        _builder.newLineIfNotEmpty();
        _builder.append(" ");
        _builder.append("org.eclipse.kura.bluetooth.listener;version=\"1.0.1\",");
        _builder.newLine();
      }
    }
    {
      Map<String, String> _configurationProperties_3 = context.getConfigurationProperties();
      String _orDefault_3 = _configurationProperties_3.getOrDefault("boschcloud", "false");
      boolean _equalsIgnoreCase_3 = _orDefault_3.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_3) {
        _builder.append(" org.osgi.service.component,");
        _builder.newLineIfNotEmpty();
        _builder.append(" ");
        _builder.append("com.eclipsesource.json;version=\"0.9.4\",");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("javax.net.ssl,");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("javax.security.cert");
        _builder.newLine();
      } else {
        {
          Map<String, String> _configurationProperties_4 = context.getConfigurationProperties();
          String _orDefault_4 = _configurationProperties_4.getOrDefault("boschhub", "false");
          boolean _equalsIgnoreCase_4 = _orDefault_4.equalsIgnoreCase("true");
          if (_equalsIgnoreCase_4) {
            _builder.append(" javax.net,");
            _builder.newLineIfNotEmpty();
            _builder.append(" ");
            _builder.append("javax.net.ssl,");
            _builder.newLine();
            _builder.append(" ");
            _builder.append("org.osgi.service.component");
            _builder.newLine();
          } else {
            _builder.append(" org.osgi.service.component,");
            _builder.newLineIfNotEmpty();
            _builder.append(" ");
            _builder.append("org.eclipse.kura.cloud;version=\"1.1.0\",");
            _builder.newLine();
            _builder.append(" ");
            _builder.append("org.eclipse.kura.message;version=\"1.1.1\"");
            _builder.newLine();
          }
        }
      }
    }
    return _builder.toString();
  }
}
