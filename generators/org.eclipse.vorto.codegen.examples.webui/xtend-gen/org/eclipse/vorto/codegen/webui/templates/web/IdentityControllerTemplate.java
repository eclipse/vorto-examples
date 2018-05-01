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
package org.eclipse.vorto.codegen.webui.templates.web;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.TemplateUtils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class IdentityControllerTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("IdentityController.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _baseApplicationPath = TemplateUtils.getBaseApplicationPath(context);
    _builder.append(_baseApplicationPath, "");
    _builder.append("/web");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.example.iot.xdk.web;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import java.lang.reflect.Type;");
    _builder.newLine();
    _builder.append("import java.util.Base64;");
    _builder.newLine();
    _builder.append("import java.util.HashMap;");
    _builder.newLine();
    _builder.append("import java.util.Map;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.springframework.beans.factory.annotation.Autowired;");
    _builder.newLine();
    _builder.append("import org.springframework.http.MediaType;");
    _builder.newLine();
    _builder.append("import org.springframework.security.core.Authentication;");
    _builder.newLine();
    _builder.append("import org.springframework.security.oauth2.client.OAuth2ClientContext;");
    _builder.newLine();
    _builder.append("import org.springframework.web.bind.annotation.RequestMapping;");
    _builder.newLine();
    _builder.append("import org.springframework.web.bind.annotation.RequestMethod;");
    _builder.newLine();
    _builder.append("import org.springframework.web.bind.annotation.RestController;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.google.gson.Gson;");
    _builder.newLine();
    _builder.append("import com.google.gson.reflect.TypeToken;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RestController");
    _builder.newLine();
    _builder.append("@RequestMapping(\"/rest/identities\")");
    _builder.newLine();
    _builder.append("public class IdentityController {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Autowired");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private OAuth2ClientContext oauthClientContext;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@RequestMapping(value = \"/user\", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Map<String, String> user(Authentication user) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Map<String, String> principal = new HashMap<String, String>();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("principal.put(\"userId\", user.getName());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("principal.put(\"userSub\", getUserSub(user));");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return principal;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String getUserSub(Authentication user) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String token = (String) oauthClientContext.getAccessToken().getAdditionalInformation().get(\"id_token\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return getJwtTokenMap(token).get(\"sub\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Map<String, String> getJwtTokenMap(String accessToken) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String[] jwtParts = accessToken.split(\"\\\\.\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Type type = new TypeToken<Map<String, String>>(){}.getType();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return new Gson().fromJson(new String(Base64.getUrlDecoder().decode(jwtParts[1])), type);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
