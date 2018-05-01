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
package org.eclipse.vorto.codegen.webui.templates.config;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.TemplateUtils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class WebSecurityConfigTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("WebSecurityConfig.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _baseApplicationPath = TemplateUtils.getBaseApplicationPath(context);
    _builder.append(_baseApplicationPath, "");
    _builder.append("/config");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.example.iot.");
    String _name = element.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append(".config;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import java.util.Arrays;");
    _builder.newLine();
    _builder.append("import java.util.Optional;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import javax.servlet.Filter;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.springframework.beans.factory.annotation.Autowired;");
    _builder.newLine();
    _builder.append("import org.springframework.beans.factory.annotation.Value;");
    _builder.newLine();
    _builder.append("import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;");
    _builder.newLine();
    _builder.append("import org.springframework.boot.context.properties.ConfigurationProperties;");
    _builder.newLine();
    _builder.append("import org.springframework.boot.web.servlet.FilterRegistrationBean;");
    _builder.newLine();
    _builder.append("import org.springframework.context.annotation.Bean;");
    _builder.newLine();
    _builder.append("import org.springframework.context.annotation.Configuration;");
    _builder.newLine();
    _builder.append("import org.springframework.core.annotation.Order;");
    _builder.newLine();
    _builder.append("import org.springframework.security.config.annotation.web.builders.HttpSecurity;");
    _builder.newLine();
    _builder.append("import org.springframework.security.config.annotation.web.builders.WebSecurity;");
    _builder.newLine();
    _builder.append("import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;");
    _builder.newLine();
    _builder.append("import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;");
    _builder.newLine();
    _builder.append("import org.springframework.security.oauth2.client.OAuth2ClientContext;");
    _builder.newLine();
    _builder.append("import org.springframework.security.oauth2.client.OAuth2RestTemplate;");
    _builder.newLine();
    _builder.append("import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;");
    _builder.newLine();
    _builder.append("import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;");
    _builder.newLine();
    _builder.append("import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;");
    _builder.newLine();
    _builder.append("import org.springframework.security.oauth2.client.token.AccessTokenProvider;");
    _builder.newLine();
    _builder.append("import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;");
    _builder.newLine();
    _builder.append("import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;");
    _builder.newLine();
    _builder.append("import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;");
    _builder.newLine();
    _builder.append("import org.springframework.security.web.util.matcher.AntPathRequestMatcher;");
    _builder.newLine();
    _builder.append("import org.springframework.web.filter.CompositeFilter;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@Configuration");
    _builder.newLine();
    _builder.append("@EnableWebSecurity");
    _builder.newLine();
    _builder.append("@EnableOAuth2Client");
    _builder.newLine();
    _builder.append("@Order(6)");
    _builder.newLine();
    _builder.append("public class WebSecurityConfig extends WebSecurityConfigurerAdapter {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Autowired");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private OAuth2ClientContext oauth2ClientContext;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Value(\"${google.oauth2.resource.userInfoUri}\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String googleUserInfoUri;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void configure(WebSecurity web) throws Exception {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("web.ignoring().antMatchers(\"/webjars/**\",\"/css/**\",\"/js/**\",\"/dist/**\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected void configure(HttpSecurity http) throws Exception {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("http.authorizeRequests().antMatchers(\"/rest/identities/user/**\",\"/rest/devices/**\").authenticated().and().logout()");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append(".logoutRequestMatcher(new AntPathRequestMatcher(\"/logout\")).logoutSuccessUrl(\"/index.html\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("http.addFilterAt(ssoFilter(), BasicAuthenticationFilter.class);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("http.formLogin().loginPage(\"/login\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("http.csrf().disable();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private Filter ssoFilter() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("CompositeFilter filter = new CompositeFilter();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("filter.setFilters(Arrays.asList(googleFilter()));");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return filter;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private Filter googleFilter() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("AuthorizationCodeResourceDetails google = google();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("UserInfoTokenServices tokenService = new UserInfoTokenServices(googleUserInfoUri, google.getClientId());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return newSsoFilter(\"/google/login\", google, tokenService, Optional.empty(), ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new OAuth2RestTemplate(google, oauth2ClientContext));\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private Filter newSsoFilter(String defaultFilterProcessesUrl, OAuth2ProtectedResourceDetails resource, ");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("UserInfoTokenServices tokenService, Optional<AccessTokenProvider> accessTokenProvider,");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("OAuth2RestTemplate restTemplate) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("accessTokenProvider.ifPresent(provider -> restTemplate.setAccessTokenProvider(provider));");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(defaultFilterProcessesUrl);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("tokenService.setRestTemplate(restTemplate);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("filter.setRestTemplate(restTemplate);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("filter.setTokenServices(tokenService);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return filter;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Bean");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("FilterRegistrationBean registration = new FilterRegistrationBean();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("registration.setFilter(filter);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("registration.setOrder(-100);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return registration;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Bean");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@ConfigurationProperties(\"google.oauth2.client\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public AuthorizationCodeResourceDetails google() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return new AuthorizationCodeResourceDetails();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
