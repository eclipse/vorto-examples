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
package org.eclipse.vorto.example.mapping.internal.deserializer;

public class MimeTypes {
	public static final class Application {
		public static final String ECLIPSE_DITTO = "application/vnd.eclipseditto+json";
		public static final String JSON = "application/json";
		private Application() {
		}
	}
	
	public static final class Text {
		public static final String CSV = "text/csv";
		private Text() {
		}
	}
}
