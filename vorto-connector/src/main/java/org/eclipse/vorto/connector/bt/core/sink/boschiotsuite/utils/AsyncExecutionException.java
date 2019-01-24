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

public class AsyncExecutionException extends RuntimeException {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AsyncExecutionException() { super(); }
	  public AsyncExecutionException(String message) { super(message); }
	  public AsyncExecutionException(String message, Throwable cause) { super(message, cause); }
	  public AsyncExecutionException(Throwable cause) { super(cause); }
}