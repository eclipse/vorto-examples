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
package org.eclipse.vorto.example.mapping.config;

import org.eclipse.vorto.example.mapping.handler.Context;
import org.eclipse.vorto.example.mapping.handler.IPayloadHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.gson.JsonObject;

@Configuration
public class LocalConfiguration {

  /**
   * Add your custom handler here to process the mapped normalized device data
   * @return
   */
  @Bean
  public IPayloadHandler simpleHandler() {
    return new IPayloadHandler() {
      
      @Override
      public void handlePayload(JsonObject normalizedPayload, Context context) {
        System.out.println("--> Normalized json for device ID "+context.getDeviceId());
        System.out.println(normalizedPayload.toString());
      }
    };
  }
}


