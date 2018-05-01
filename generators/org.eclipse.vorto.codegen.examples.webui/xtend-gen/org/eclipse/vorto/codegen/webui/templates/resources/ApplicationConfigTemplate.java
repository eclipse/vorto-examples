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
package org.eclipse.vorto.codegen.webui.templates.resources;

import java.util.Map;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ApplicationConfigTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("application.yml");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = context.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("-solution/src/main/resources");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("spring:");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("jackson:");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("serialization:");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("write-dates-as-timestamps: false");
    _builder.newLine();
    _builder.append("      ");
    _builder.newLine();
    _builder.append("server:");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("port: 8080");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("contextPath: /");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties = context.getConfigurationProperties();
      String _orDefault = _configurationProperties.getOrDefault("boschcloud", "false");
      boolean _equalsIgnoreCase = _orDefault.equalsIgnoreCase("true");
      if (_equalsIgnoreCase) {
        _builder.append("bosch:");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("things:");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("alias: CR");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("alias.password:");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("endpointUrl : https://things.apps.bosch-iot-cloud.com");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("wsEndpointUrl : wss://events.apps.bosch-iot-cloud.com");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("apiToken: ");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("keystoreLocation : /secure/CRClient.jks");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("trustStoreLocation : /secure/bosch-iot-cloud.jks");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("trustStorePassword : jks");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("solutionid: ");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("keystore:");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("password: ");
        _builder.newLine();
      }
    }
    _builder.append("http:");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("proxyUser: ");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("proxyPassword:");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("proxyHost: ");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("proxyPort: 8080");
    _builder.newLine();
    _builder.append("  ");
    _builder.newLine();
    _builder.append("google:");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("oauth2:");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("client:");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("clientId: ");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("clientSecret: ");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("accessTokenUri: https://www.googleapis.com/oauth2/v4/token");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("userAuthorizationUri: https://accounts.google.com/o/oauth2/v2/auth");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("clientAuthenticationScheme: form");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("scope:");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("- openid");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("- email");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("- profile");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("resource:");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("userInfoUri: https://www.googleapis.com/oauth2/v3/userinfo");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("preferTokenInfo: true");
    _builder.newLine();
    return _builder.toString();
  }
}
