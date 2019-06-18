package org.eclipse.vorto.plugins.generator.helloworld.plugins;

public class HelloWorldPluginInfo implements IPluginMeta {

  private static final String LOGO_144x144 = "<base64 encoded logo>";
  private static final String LOGO_32x32 = "<base64 encoded logo>";
  
  private Object info = null;
  
  public HelloWorldPluginInfo() {
//    DefaultGeneratorConfigUI configUI = new DefaultGeneratorConfigUI(jsonschema.getInfo());
//
//    info = new GeneratorPluginInfo(jsonschema.getServiceKey(),
//                                                  jsonschema.getInfo().getName(),
//                                                  jsonschema.getInfo().getDescription(),
//                                                  jsonschema.getInfo().getOrganisation(),
//                                                  "https://json-schema.org",
//                                                  LOGO_32x32,LOGO_144x144,
//                                                  configUI.getContent(),
//                                                  configUI.getKeys());
  }
  
  @Override
  public Object getInfo() {
    return info;
  }
}
