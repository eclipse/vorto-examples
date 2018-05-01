package org.eclipse.vorto.codegen.webui.templates.service.bosch.internal;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.TemplateUtils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ThingsInvocationTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("ThingsInvocationTemplate.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _baseApplicationPath = TemplateUtils.getBaseApplicationPath(context);
    _builder.append(_baseApplicationPath, "");
    _builder.append("/service/bosch/internal");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.example.iot.");
    String _name = element.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append(".service.bosch.internal;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import org.apache.http.HttpHeaders;");
    _builder.newLine();
    _builder.append("import org.apache.http.client.methods.HttpUriRequest;");
    _builder.newLine();
    _builder.append("import org.springframework.security.core.Authentication;");
    _builder.newLine();
    _builder.append("import org.springframework.security.core.context.SecurityContextHolder;");
    _builder.newLine();
    _builder.append("import org.springframework.security.oauth2.client.OAuth2ClientContext;");
    _builder.newLine();
    _builder.append("import org.springframework.security.oauth2.provider.OAuth2Authentication;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.google.gson.GsonBuilder;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ThingsInvocationTemplate extends AsyncInvocationTemplate {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String apiToken;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private OAuth2ClientContext oauthClientContext;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private final static String CR_API_TOKEN_HEADER = \"x-cr-api-token\";");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ThingsInvocationTemplate(String apiToken, OAuth2ClientContext oauthClientContext) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super(new GsonBuilder().create());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.apiToken = apiToken;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.oauthClientContext = oauthClientContext;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected void preSend(HttpUriRequest request) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super.preSend(request);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("request.addHeader(CR_API_TOKEN_HEADER, apiToken);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("request.addHeader(HttpHeaders.AUTHORIZATION, \"Bearer \" + getToken());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String getToken() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Authentication autentication = SecurityContextHolder.getContext().getAuthentication();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (autentication instanceof OAuth2Authentication) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return (String) oauthClientContext.getAccessToken().getAdditionalInformation().get(\"id_token\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("throw new RuntimeException(\"User is not yet authenticated.\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder.toString();
  }
}
