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

public class DeserializerFactory {
	public static IDeserializer getDeserializer(String contentType) {
		switch (contentType) {
		case MimeTypes.Text.CSV:
			return new CsvDeserializer();
		case MimeTypes.Application.JSON:
			return new JsonDeserializer();
		case MimeTypes.Application.ECLIPSE_DITTO:
			return new DittoDeserializer();
		default:
			return null;
		}
	}
}
