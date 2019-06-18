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
package org.eclipse.vorto.plugins.generator.example.plugins;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.eclipse.vorto.plugin.generator.GeneratorPluginInfo;
import org.eclipse.vorto.plugins.generator.example.HelloWorldGenerator;

public class GeneratorPluginInfoFactory {
  
  
  private static GeneratorPluginInfoFactory instance = null;
  
  private final static Set<GeneratorPluginInfo> infos = new HashSet<>();
  
  static {
    infos.add(new HelloWorldGenerator().getMeta());
  }
  
  private GeneratorPluginInfoFactory() {
  }


  public static GeneratorPluginInfoFactory getInstance() {
    if (instance == null) {
      instance = new GeneratorPluginInfoFactory();
    }
    return instance;
  }
  public Optional<GeneratorPluginInfo> getForPlugin(String pluginkey) {
    return infos.stream().filter(info -> info.getKey().equalsIgnoreCase(pluginkey)).findAny();
  }
}
