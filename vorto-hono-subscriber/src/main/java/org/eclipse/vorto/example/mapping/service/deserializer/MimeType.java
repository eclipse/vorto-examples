/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional information regarding copyright
 * ownership.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which is available at https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.example.mapping.service.deserializer;

public enum MimeType {

  ECLIPSE_DITTO("application/vnd.eclipseditto+json"), JSON("application/json"), CSV("text/csv");

  private String contentType;

  private MimeType(String contentType) {
    this.contentType = contentType;
  }

  public boolean equals(String contentType) {
    return this.contentType.equalsIgnoreCase(contentType);
  }
  
  public static MimeType create(String contentType) {
    if (contentType.equalsIgnoreCase(ECLIPSE_DITTO.contentType)) {
      return ECLIPSE_DITTO;
    } else if (contentType.equalsIgnoreCase(JSON.contentType)) {
      return JSON;
    } else if (contentType.equalsIgnoreCase(CSV.contentType)) {
      return CSV;
    } else {
      throw new UnsupportedOperationException("Only CSV, JSON or DITTO supported");
    }
  }
}
