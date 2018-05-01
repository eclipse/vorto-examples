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

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.kura.templates.Utils;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.vorto.core.api.model.model.ModelId;
import org.eclipse.vorto.core.api.model.model.ModelIdFactory;
import org.eclipse.vorto.core.api.model.model.ModelReference;
import org.eclipse.vorto.core.api.model.model.ModelType;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * @author Erle Czar Mantos - Robert Bosch (SEA) Pte. Ltd.
 */
@SuppressWarnings("all")
public class KuraCloudDataServiceTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("KuraCloudDataService.java");
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
    _builder.append("import java.util.Date;");
    _builder.newLine();
    _builder.append("import java.util.Objects;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.eclipse.kura.KuraException;");
    _builder.newLine();
    _builder.append("import org.eclipse.kura.cloud.CloudClient;");
    _builder.newLine();
    _builder.append("import org.eclipse.kura.cloud.CloudClientListener;");
    _builder.newLine();
    _builder.append("import org.eclipse.kura.cloud.CloudService;");
    _builder.newLine();
    _builder.append("import org.eclipse.kura.message.KuraPayload;");
    _builder.newLine();
    _builder.append("import org.osgi.service.component.ComponentException;");
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
    _builder.newLine();
    _builder.append("import ");
    String _javaPackage_2 = Utils.getJavaPackage(element);
    _builder.append(_javaPackage_2, "");
    _builder.append(".cloud.IDataService;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public class KuraCloudDataService implements IDataService, CloudClientListener {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private static final Logger logger = LoggerFactory.getLogger(KuraCloudDataService.class);");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private final String APP_ID = \"BLE_APP_V1\";");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private CloudClient cloudClient;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String topic = \"data\";");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public KuraCloudDataService(CloudService cloudService, ");
    String _name_1 = element.getName();
    _builder.append(_name_1, "\t");
    _builder.append("Configuration configuration) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.topic = Objects.requireNonNull(configuration.publishTopic);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("cloudClient = cloudService.newCloudClient(this.APP_ID);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("cloudClient.addCloudClientListener(this);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} catch (KuraException e1) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("logger.error(\"Error starting component\", e1);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("throw new ComponentException(e1);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties = element.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public void publish");
        String _name_2 = fbProperty.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_2);
        _builder.append(_firstUpper, "\t");
        _builder.append("(String resourceId, ");
        FunctionblockModel _type = fbProperty.getType();
        String _name_3 = _type.getName();
        _builder.append(_name_3, "\t");
        _builder.append(" data) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("// send accelerometer to iot cloud backend");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("KuraPayload payload = new KuraPayload();");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("payload.setTimestamp(new Date());");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("payload.addMetric(\"");
        FunctionblockModel _type_1 = fbProperty.getType();
        String _name_4 = _type_1.getName();
        String _lowerCase = _name_4.toLowerCase();
        _builder.append(_lowerCase, "\t\t");
        _builder.append("\", data);");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("try {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("if (!payload.metricNames().isEmpty()) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t\t");
        _builder.append("cloudClient.publish(topic + \"/\" + resourceId, payload, 0, false);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("} catch (Exception e) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("logger.error(\"Problem sending data to cloud\", e);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void onConnectionEstablished() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// TODO Auto-generated method stub");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void onConnectionLost() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// TODO Auto-generated method stub");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void onControlMessageArrived(String arg0, String arg1, KuraPayload arg2, int arg3, boolean arg4) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// TODO Auto-generated method stub");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void onMessageArrived(String arg0, String arg1, KuraPayload arg2, int arg3, boolean arg4) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// TODO Auto-generated method stub");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void onMessageConfirmed(int arg0, String arg1) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// TODO Auto-generated method stub");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void onMessagePublished(int arg0, String arg1) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// TODO Auto-generated method stub");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
