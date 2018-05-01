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
package org.eclipse.vorto.codegen.kura.templates.cloud.bosch;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.kura.templates.Utils;
import org.eclipse.vorto.codegen.kura.templates.cloud.TypeMapper;
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType;
import org.eclipse.vorto.core.api.model.datatype.PrimitiveType;
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

/**
 * @author Alexander Edelmann
 */
@SuppressWarnings("all")
public class BoschDataServiceTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("BoschDataService.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _javaPackageBasePath = Utils.getJavaPackageBasePath(context);
    _builder.append(_javaPackageBasePath, "");
    _builder.append("/cloud/bosch");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _javaPackage = Utils.getJavaPackage(element);
    _builder.append(_javaPackage, "");
    _builder.append(".cloud.bosch;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import java.util.concurrent.ExecutionException;");
    _builder.newLine();
    _builder.append("import java.util.concurrent.TimeUnit;");
    _builder.newLine();
    _builder.append("import java.util.concurrent.TimeoutException;");
    _builder.newLine();
    _builder.append("import java.util.function.Function;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.slf4j.Logger;");
    _builder.newLine();
    _builder.append("import org.slf4j.LoggerFactory;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.bosch.cr.integration.IntegrationClient;");
    _builder.newLine();
    _builder.append("import com.bosch.cr.integration.things.FeatureHandle;");
    _builder.newLine();
    _builder.append("import com.bosch.cr.integration.things.ThingHandle;");
    _builder.newLine();
    _builder.append("import com.bosch.cr.json.JsonObject;");
    _builder.newLine();
    _builder.append("import com.bosch.cr.model.things.Feature;");
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
    _builder.newLine();
    _builder.append("public class BoschDataService implements IDataService {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private IntegrationClient integrationClient;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private static final Logger LOGGER = LoggerFactory.getLogger(BoschDataService.class);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private static final int TIMEOUT = 5;");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private String solutionId;");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private String endpointUrl;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public BoschDataService(String solutionId, String endpointUrl) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.solutionId = solutionId;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.endpointUrl = endpointUrl;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties = element.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        _builder.append("\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public void publish");
        String _name_1 = fbProperty.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_1);
        _builder.append(_firstUpper, "\t");
        _builder.append("(String thingId, ");
        FunctionblockModel _type = fbProperty.getType();
        String _name_2 = _type.getName();
        _builder.append(_name_2, "\t");
        _builder.append(" data) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t ");
        _builder.append("ThingHandle thingHandle = getIntegrationClient().things().forId(thingId);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t ");
        _builder.append("String featureId = \"");
        String _name_3 = fbProperty.getName();
        _builder.append(_name_3, "\t\t ");
        _builder.append("\"; //feature id according to Information model function block property");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("        ");
        _builder.append("FeatureHandle featureHandle = thingHandle.forFeature(featureId);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("        ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("        ");
        _builder.append("try {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("            ");
        _builder.append("featureHandle.retrieve()");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("                ");
        _builder.append(".exceptionally(addMissingFeature(thingHandle, featureId))");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("                ");
        _builder.append(".thenCompose(feature -> {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("                     ");
        _builder.append("JsonObject status = JsonObject.newBuilder()");
        _builder.newLine();
        {
          FunctionblockModel _type_1 = fbProperty.getType();
          FunctionBlock _functionblock = _type_1.getFunctionblock();
          Status _status = _functionblock.getStatus();
          EList<Property> _properties_1 = _status.getProperties();
          for(final Property statusProperty : _properties_1) {
            {
              boolean _and = false;
              PropertyType _type_2 = statusProperty.getType();
              if (!(_type_2 instanceof PrimitivePropertyType)) {
                _and = false;
              } else {
                PropertyType _type_3 = statusProperty.getType();
                PrimitiveType _type_4 = ((PrimitivePropertyType) _type_3).getType();
                boolean _equals = Objects.equal(_type_4, PrimitiveType.DATETIME);
                _and = _equals;
              }
              if (_and) {
                _builder.append("\t");
                _builder.append("                     \t");
                _builder.append(".set(\"");
                String _name_4 = statusProperty.getName();
                _builder.append(_name_4, "\t                     \t");
                _builder.append("\", JSON_DATE_FORMAT.format(data.get");
                String _name_5 = statusProperty.getName();
                String _checkKeyword = TypeMapper.checkKeyword(_name_5);
                String _firstUpper_1 = StringExtensions.toFirstUpper(_checkKeyword);
                _builder.append(_firstUpper_1, "\t                     \t");
                _builder.append("()))");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("\t");
                _builder.append("                     \t");
                _builder.append(".set(\"");
                String _name_6 = statusProperty.getName();
                _builder.append(_name_6, "\t                     \t");
                _builder.append("\", data.get");
                String _name_7 = statusProperty.getName();
                String _checkKeyword_1 = TypeMapper.checkKeyword(_name_7);
                String _firstUpper_2 = StringExtensions.toFirstUpper(_checkKeyword_1);
                _builder.append(_firstUpper_2, "\t                     \t");
                _builder.append("())");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("\t");
        _builder.append("                     \t");
        _builder.append(".build();");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("                     ");
        _builder.append("return featureHandle.setProperties(JsonObject.newBuilder().set(\"status\", status).build());");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("                 ");
        _builder.append("}).whenComplete((aVoid, throwable) -> {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("                     ");
        _builder.append("if (null == throwable) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("                         ");
        _builder.append("LOGGER.info(\"Thing with ID \'{}\' feature ");
        String _name_8 = fbProperty.getName();
        _builder.append(_name_8, "\t                         ");
        _builder.append(" was updated with values {}\", thingId,data);");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("                     ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("                     ");
        _builder.append("else {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("                         ");
        _builder.append("LOGGER.error(throwable.getMessage());");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("                     ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("                 ");
        _builder.append("}).get(TIMEOUT, TimeUnit.SECONDS);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("        ");
        _builder.append("} catch (InterruptedException | ExecutionException | TimeoutException e) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("            ");
        _builder.append("e.printStackTrace();");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("            ");
        _builder.append("throw new RuntimeException(e);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("        ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private IntegrationClient getIntegrationClient() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (this.integrationClient == null) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return integrationClient = ThingClientFactory.getThingIntegrationClient(solutionId,endpointUrl);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return this.integrationClient;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private Function<Throwable, Feature> addMissingFeature(ThingHandle thingHandle, String featureId) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return throwable -> {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("LOGGER.info(\"Creating the feature first because it\'s missing\");");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("Feature feature = Feature.newBuilder().withId(featureId).build();");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("return thingHandle.putFeature(feature)");
    _builder.newLine();
    _builder.append("                    ");
    _builder.append(".thenCompose(aVoid -> thingHandle.forFeature(featureId).retrieve())");
    _builder.newLine();
    _builder.append("                    ");
    _builder.append(".get(TIMEOUT, TimeUnit.SECONDS);");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("} catch (Exception e) {");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("throw new RuntimeException(e);");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("};");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    return _builder.toString();
  }
}
