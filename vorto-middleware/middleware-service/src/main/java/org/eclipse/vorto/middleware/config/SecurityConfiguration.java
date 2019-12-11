/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.middleware.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CompositeFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableOAuth2Client
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${cors}")
	private String crossOrigin;

	@Value("${admin.password}")
	private String adminPassword = "";

	@Autowired
	private AccessTokenProvider accessTokenProvider;

	@Autowired
	private OAuth2ClientContext oauth2ClientContext;

	@Autowired
	private UserInfoTokenServices tokenService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/api/1/**", "/endpoint/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/1/**").authenticated().and().csrf().disable()
				.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class).logout().logoutUrl("/logout")
				.logoutSuccessUrl("/").and().headers().frameOptions().sameOrigin();
	}

	private Filter ssoFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> ssoFilters = new ArrayList<Filter>();
		ssoFilters.add(filter());
		filter.setFilters(ssoFilters);
		return filter;
	}

	private Filter filter() {
		OAuth2RestTemplate restTemplate = createOAuthTemplate();

		restTemplate.setAccessTokenProvider(accessTokenProvider);

		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(
				"/github/login");
		tokenService.setRestTemplate(restTemplate);
		filter.setRestTemplate(restTemplate);
		filter.setTokenServices(tokenService);

		return filter;
	}

	private OAuth2RestTemplate createOAuthTemplate() {
		return new OAuth2RestTemplate(createDetails(), oauth2ClientContext);
	}

	private AuthorizationCodeResourceDetails createDetails() {
		return github();
	}

	@Bean
	@ConfigurationProperties("github.oauth2.client")
	public AuthorizationCodeResourceDetails github() {
		return new AuthorizationCodeResourceDetails();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(adminPassword).roles("ADMIN");
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin(crossOrigin);
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	@Bean
	@RequestScope
	public RestTemplate restTemplate(HttpServletRequest request) {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final RestTemplate restTemplate = new RestTemplate();
		if (authHeader != null && !authHeader.isEmpty()) {
			restTemplate.getInterceptors().add((httpOut, bytes, clientHttpReqExec) -> {
				httpOut.getHeaders().set(HttpHeaders.AUTHORIZATION, authHeader);
				return clientHttpReqExec.execute(httpOut, bytes);
			});
		}
		return restTemplate;
	}
}
