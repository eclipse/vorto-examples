package org.eclipse.vorto.codegen.kura.templates.cloud.bosch;

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

@SuppressWarnings("all")
public class BoschHubDataService implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("BoschHubDataService.java");
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
    _builder.append("import java.util.HashMap;");
    _builder.newLine();
    _builder.append("import java.util.Map;");
    _builder.newLine();
    _builder.append("import java.util.Objects;");
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
    _builder.newLine();
    _builder.append("import com.google.gson.Gson;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class BoschHubDataService implements IDataService {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String mqqtHostUrl;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String hubTenant;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private Map<String, BoschHubClient> deviceClients = new HashMap<String, BoschHubClient>();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private Gson gson = new Gson();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public BoschHubDataService(String mqqtHostUrl, String hubTenant) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.mqqtHostUrl = Objects.requireNonNull(mqqtHostUrl);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.hubTenant = Objects.requireNonNull(hubTenant);");
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
        _builder.append("(String resourceId, ");
        FunctionblockModel _type = fbProperty.getType();
        String _name_2 = _type.getName();
        _builder.append(_name_2, "\t");
        _builder.append(" ");
        String _name_3 = fbProperty.getName();
        _builder.append(_name_3, "\t");
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("getConnectedHubClient(resourceId).send(\"telemetry/\" + hubTenant + \"/\" + resourceId, gson.toJson(wrap(\"");
        String _name_4 = fbProperty.getName();
        _builder.append(_name_4, "\t\t");
        _builder.append("\", ");
        String _name_5 = fbProperty.getName();
        _builder.append(_name_5, "\t\t");
        _builder.append(")));");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private <T> Map<String, Map<String, Map<String, T>>> wrap(String name, T functionBlock) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Map<String, T> status = new HashMap<String, T>();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("status.put(\"status\", functionBlock);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Map<String, Map<String, T>> properties = new HashMap<String, Map<String, T>>();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("properties.put(\"properties\", status);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Map<String, Map<String, Map<String, T>>> wrapper = new HashMap<String, Map<String, Map<String, T>>>();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("wrapper.put(name, properties);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return wrapper; ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private BoschHubClient getConnectedHubClient(String resourceId) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("BoschHubClient client = deviceClients.get(resourceId);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (client == null) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("client = new BoschHubClient(mqqtHostUrl, resourceId);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("deviceClients.put(resourceId, client);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (!client.isConnected()) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("client.connect();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return client;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
