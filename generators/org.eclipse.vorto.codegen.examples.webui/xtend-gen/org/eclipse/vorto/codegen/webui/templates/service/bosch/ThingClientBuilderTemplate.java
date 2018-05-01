package org.eclipse.vorto.codegen.webui.templates.service.bosch;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.TemplateUtils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ThingClientBuilderTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("ThingClientBuilder.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _baseApplicationPath = TemplateUtils.getBaseApplicationPath(context);
    _builder.append(_baseApplicationPath, "");
    _builder.append("/service/bosch");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.example.iot.");
    String _name = element.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append(".service.bosch;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("import org.apache.http.HttpHost;");
    _builder.newLine();
    _builder.append("import org.apache.http.client.config.RequestConfig;");
    _builder.newLine();
    _builder.append("import org.springframework.security.oauth2.client.OAuth2ClientContext;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.example.iot.");
    String _name_1 = element.getName();
    String _lowerCase_1 = _name_1.toLowerCase();
    _builder.append(_lowerCase_1, "");
    _builder.append(".service.bosch.internal.DefaultThingClient;");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.example.iot.");
    String _name_2 = element.getName();
    String _lowerCase_2 = _name_2.toLowerCase();
    _builder.append(_lowerCase_2, "");
    _builder.append(".service.bosch.internal.ThingsInvocationTemplate;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public class ThingClientBuilder {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String endpointUrl;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private RequestConfig config;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String apiToken;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private OAuth2ClientContext oauth2context;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ThingClientBuilder() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.endpointUrl = \"https://things.apps.bosch-iot-cloud.com\";");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ThingClientBuilder withEndpointUrl(final String endpointUrl) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.endpointUrl = endpointUrl;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return this;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ThingClientBuilder withApiToken(final String apiToken) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.apiToken = apiToken;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return this;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ThingClientBuilder withProxy(final String proxyHost, int proxyPort) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.config = RequestConfig.custom().setProxy(new HttpHost(proxyHost, proxyPort)).build();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return this;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ThingClient build() {\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return new DefaultThingClient(this.endpointUrl,new ThingsInvocationTemplate(apiToken, oauth2context), config);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ThingClientBuilder withOAuth2ClientContext(OAuth2ClientContext oauth2context) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.oauth2context = oauth2context;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return this;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder.toString();
  }
}
