package org.eclipse.vorto.plugins.generator.example.plugins;

import java.util.HashMap;
import java.util.Map;

public class GeneratorPluginInfoFactory {
  
  
  private static GeneratorPluginInfoFactory instance = null;
  
  private static final Object helloworldPluginInfo = new Object();
 
  
  private final static Map<String, Object> infos = new HashMap<String, Object>();
  
  static {
    infos.put("helloworld",helloworldPluginInfo);
  }
  
  private GeneratorPluginInfoFactory() {
  }


  public static GeneratorPluginInfoFactory getInstance() {
    if (instance == null) {
      instance = new GeneratorPluginInfoFactory();
    }
    return instance;
  }
  public Object getForPlugin(String pluginkey) {
    return infos.get(pluginkey);
  }
}
