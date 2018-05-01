/**
 * Copyright (c) 2015-2016 Bosch Software Innovations GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * The Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * Contributors:
 * Bosch Software Innovations GmbH - Please refer to git log
 */
package org.eclipse.vorto.codegen.kura.templates;

import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.kura.templates.Utils;
import org.eclipse.vorto.codegen.kura.templates.cloud.TypeMapper;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.functionblock.Status;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.vorto.core.api.model.model.ModelId;
import org.eclipse.vorto.core.api.model.model.ModelIdFactory;
import org.eclipse.vorto.core.api.model.model.ModelReference;
import org.eclipse.vorto.core.api.model.model.ModelType;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class DefaultAppTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = context.getName();
    _builder.append(_name, "");
    _builder.append("App.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _javaPackageBasePath = Utils.getJavaPackageBasePath(context);
    _builder.append(_javaPackageBasePath, "");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _javaPackage = Utils.getJavaPackage(element);
    _builder.append(_javaPackage, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import java.util.concurrent.Executors;");
    _builder.newLine();
    _builder.append("import java.util.concurrent.ScheduledExecutorService;");
    _builder.newLine();
    _builder.append("import java.util.concurrent.ScheduledFuture;");
    _builder.newLine();
    _builder.append("import java.util.concurrent.TimeUnit;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.osgi.service.component.ComponentContext;");
    _builder.newLine();
    _builder.append("import org.slf4j.Logger;");
    _builder.newLine();
    _builder.append("import org.slf4j.LoggerFactory;");
    _builder.newLine();
    _builder.newLine();
    {
      EList<ModelReference> _references = element.getReferences();
      for(final ModelReference reference : _references) {
        ModelId modelId = ModelIdFactory.newInstance(ModelType.Functionblock, reference);
        _builder.newLineIfNotEmpty();
        _builder.append("import ");
        String _javaPackage_1 = Utils.getJavaPackage(element);
        _builder.append(_javaPackage_1, "");
        _builder.append(".model.");
        String _name = modelId.getName();
        _builder.append(_name, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("import ");
    String _javaPackage_2 = Utils.getJavaPackage(element);
    _builder.append(_javaPackage_2, "");
    _builder.append(".cloud.IDataService;");
    _builder.newLineIfNotEmpty();
    {
      Map<String, String> _configurationProperties = context.getConfigurationProperties();
      String _orDefault = _configurationProperties.getOrDefault("boschcloud", "false");
      boolean _equalsIgnoreCase = _orDefault.equalsIgnoreCase("true");
      if (_equalsIgnoreCase) {
        _builder.append("import ");
        String _javaPackage_3 = Utils.getJavaPackage(element);
        _builder.append(_javaPackage_3, "");
        _builder.append(".cloud.bosch.BoschDataService;");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("import org.eclipse.kura.cloud.CloudService;");
        _builder.newLine();
      }
    }
    _builder.newLine();
    _builder.append("public class ");
    String _name_1 = element.getName();
    _builder.append(_name_1, "");
    _builder.append("App {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private static final Logger s_logger = LoggerFactory.getLogger(");
    String _name_2 = element.getName();
    _builder.append(_name_2, "\t");
    _builder.append("App.class);");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("private static final String APP_ID = \"");
    String _javaPackage_4 = Utils.getJavaPackage(element);
    _builder.append(_javaPackage_4, "    ");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private ScheduledExecutorService m_worker;");
    _builder.newLine();
    _builder.append("   \t");
    _builder.append("private ScheduledFuture<?> m_handle;");
    _builder.newLine();
    _builder.append("   \t");
    _builder.newLine();
    _builder.append("   \t");
    _builder.append("private IDataService dataService;");
    _builder.newLine();
    _builder.append("   \t");
    _builder.newLine();
    _builder.append("   \t");
    _builder.append("private String thingId = \"INSERT THING ID HERE\";");
    _builder.newLine();
    _builder.newLine();
    {
      Map<String, String> _configurationProperties_1 = context.getConfigurationProperties();
      String _orDefault_1 = _configurationProperties_1.getOrDefault("boschcloud", "false");
      boolean _equalsIgnoreCase_1 = _orDefault_1.equalsIgnoreCase("true");
      boolean _not = (!_equalsIgnoreCase_1);
      if (_not) {
        _builder.append("\t");
        _builder.append("private CloudService cloudService;");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public void setCloudService(CloudService cloudService) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("this.cloudService = cloudService;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public void unsetCloudService(CloudService cloudService) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("this.cloudService = null;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.newLine();
    _builder.append("   \t");
    _builder.append("public ");
    String _name_3 = element.getName();
    _builder.append(_name_3, "   \t");
    _builder.append("App() {");
    _builder.newLineIfNotEmpty();
    _builder.append("   \t\t");
    _builder.append("m_worker = Executors.newSingleThreadScheduledExecutor();");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties_2 = context.getConfigurationProperties();
      String _orDefault_2 = _configurationProperties_2.getOrDefault("boschcloud", "false");
      boolean _equalsIgnoreCase_2 = _orDefault_2.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_2) {
        _builder.append("dataService = new BoschDataService(\"\",\"wss://events.apps.bosch-iot-cloud.com\");");
        _builder.newLine();
      } else {
        _builder.append("dataService = new KuraCloudDataService(cloudService);");
        _builder.newLine();
      }
    }
    _builder.append("   \t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   \t");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("protected void activate(ComponentContext componentContext) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("s_logger.info(\"Bundle \" + APP_ID + \" has started!\");");
    _builder.newLine();
    _builder.append("        ");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("// cancel a current worker handle if one if active");
    _builder.newLine();
    _builder.append("   \t\t");
    _builder.append("if (m_handle != null) {");
    _builder.newLine();
    _builder.append("   \t\t\t");
    _builder.append("m_handle.cancel(true);");
    _builder.newLine();
    _builder.append("   \t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("   \t\t");
    _builder.append("// schedule a new worker");
    _builder.newLine();
    _builder.append("   \t\t");
    _builder.append("m_handle = m_worker.scheduleAtFixedRate(new Runnable() {");
    _builder.newLine();
    _builder.append("   \t\t\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("   \t\t\t");
    _builder.append("public void run() {");
    _builder.newLine();
    _builder.append("   \t\t\t\t");
    _builder.append("Thread.currentThread().setName(getClass().getSimpleName());");
    _builder.newLine();
    _builder.append("   \t\t\t\t");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties = element.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        _builder.append("   \t\t\t\t");
        FunctionblockModel _type = fbProperty.getType();
        String _name_4 = _type.getName();
        _builder.append(_name_4, "   \t\t\t\t");
        _builder.append(" ");
        String _name_5 = fbProperty.getName();
        _builder.append(_name_5, "   \t\t\t\t");
        _builder.append(" = new ");
        FunctionblockModel _type_1 = fbProperty.getType();
        String _name_6 = _type_1.getName();
        _builder.append(_name_6, "   \t\t\t\t");
        _builder.append("();");
        _builder.newLineIfNotEmpty();
        {
          FunctionblockModel _type_2 = fbProperty.getType();
          FunctionBlock _functionblock = _type_2.getFunctionblock();
          Status _status = _functionblock.getStatus();
          EList<Property> _properties_1 = _status.getProperties();
          for(final Property statusProperty : _properties_1) {
            _builder.append("   \t\t\t\t");
            String _name_7 = fbProperty.getName();
            _builder.append(_name_7, "   \t\t\t\t");
            _builder.append(".set");
            String _name_8 = statusProperty.getName();
            String _checkKeyword = TypeMapper.checkKeyword(_name_8);
            String _firstUpper = StringExtensions.toFirstUpper(_checkKeyword);
            _builder.append(_firstUpper, "   \t\t\t\t");
            _builder.append("(");
            PropertyType _type_3 = statusProperty.getType();
            String _randomValue = TypeMapper.getRandomValue(_type_3);
            _builder.append(_randomValue, "   \t\t\t\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("   \t\t\t\t");
        _builder.append("dataService.publish");
        String _name_9 = fbProperty.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_9);
        _builder.append(_firstUpper_1, "   \t\t\t\t");
        _builder.append("(thingId, ");
        String _name_10 = fbProperty.getName();
        _builder.append(_name_10, "   \t\t\t\t");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("   \t\t\t\t");
        _builder.newLine();
      }
    }
    _builder.append("   \t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   \t\t");
    _builder.append("}, 0, 300, TimeUnit.SECONDS);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("protected void deactivate(ComponentContext componentContext) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("s_logger.info(\"Bundle \" + APP_ID + \" has stopped!\");");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder.toString();
  }
}
