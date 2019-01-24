/**
 * Copyright (c) 2019 Contributors to the Eclipse Foundation
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
package org.eclipse.vorto.connector.bt.core.sink.boschiotsuite.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AsyncInvocationTemplate {

	private Gson gson;
	
	private CredentialsProvider credentialsProvider = null;
	
	public AsyncInvocationTemplate(Gson gson) {
		this(gson,null);
	}
	
	public AsyncInvocationTemplate(Gson gson, CredentialsProvider credentialsProvider) {
		this.credentialsProvider = credentialsProvider;
		this.gson = gson;
	}
	
	public <Result> CompletableFuture<Result> execute(final HttpUriRequest request, Type type) {
		preSend(request);
		return CompletableFuture.supplyAsync(() -> {
			try {
				final HttpClient httpClient = credentialsProvider!= null ? HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build() : HttpClients.createDefault();
				return httpClient.execute(request, response -> {
					
					int statusCode = response.getStatusLine().getStatusCode();
					
					if (statusCode >= 200 && statusCode <= 299) {
						if (type.equals(Void.class)) {
							return null;
						}
						String json = EntityUtils.toString(response.getEntity(), "UTF-8");
						return gson.fromJson(json, type);
					} else if (statusCode == 400) {
						throw new IllegalArgumentException("Error executing async call, bad request. Error: " + getErrorString(response));
					} else if (statusCode == 401) {
						throw new IllegalArgumentException("Error executing async call, not able to authenticate with the server. Error: " + getErrorString(response));
					} else if (statusCode == 403) {
						throw new IllegalArgumentException("Error executing async call, not able to authorize with the server. Error: " + getErrorString(response));					
					} else if (statusCode == 404) {
						return null;
					} else {
						throw new AsyncExecutionException("Error while executing async call, server respond with a status of:  " + response.getStatusLine().getStatusCode() + 
								" Error: " + getErrorString(response));
					}
				});
			} catch (IOException e) {
				throw new AsyncExecutionException("Error executing async call to remote REST endpoint", e);
			}
		});
	}
	
	private String getErrorString(HttpResponse response) {
		Map<String, Object> errorMap = new HashMap<String, Object>();
		Map<String, Object> responseHeader = new HashMap<String, Object>();
		
		for(Header header : response.getAllHeaders()) {
			responseHeader.put(header.getName(), header.getValue());
		}
		errorMap.put("header", responseHeader);
		try {
			errorMap.put("body", gson.fromJson(IOUtils.toString(response.getEntity().getContent()), new TypeToken<Map<String, String>>(){}.getType()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return gson.toJson(errorMap);
	}
	
	public <Result> CompletableFuture<Result> execute(HttpUriRequest request) {
		return execute(request,new TypeToken<Result>(){}.getType());
	}

	protected void preSend(HttpUriRequest request) {
		request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		request.addHeader(HttpHeaders.ACCEPT, "application/json");
	}
}
