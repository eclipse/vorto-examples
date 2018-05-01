package org.eclipse.vorto.codegen.thingworx;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.common.base.Objects;
import java.io.StringWriter;
import java.util.HashMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.Generated;
import org.eclipse.vorto.codegen.api.GenerationResultZip;
import org.eclipse.vorto.codegen.api.ICodeGeneratorTask;
import org.eclipse.vorto.codegen.api.IGeneratedWriter;
import org.eclipse.vorto.codegen.api.IGenerationResult;
import org.eclipse.vorto.codegen.api.IVortoCodeGenProgressMonitor;
import org.eclipse.vorto.codegen.api.IVortoCodeGenerator;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.api.VortoCodeGeneratorException;
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType;
import org.eclipse.vorto.core.api.model.datatype.PrimitiveType;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.vorto.core.api.model.functionblock.Configuration;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.functionblock.Operation;
import org.eclipse.vorto.core.api.model.functionblock.Param;
import org.eclipse.vorto.core.api.model.functionblock.PrimitiveParam;
import org.eclipse.vorto.core.api.model.functionblock.ReturnPrimitiveType;
import org.eclipse.vorto.core.api.model.functionblock.ReturnType;
import org.eclipse.vorto.core.api.model.functionblock.Status;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class ThingWorxCodeGenerator implements IVortoCodeGenerator {
  private enum VortoPropertyType {
    STATUS,
    
    CONFIGURATION;
  }
  
  public static class JSONGeneratorTask implements ICodeGeneratorTask<InformationModel> {
    @Override
    public void generate(final InformationModel model, final InvocationContext invocationContext, final IGeneratedWriter writer) {
      String _name = model.getName();
      String _plus = (_name + ".json");
      String _content = this.getContent(model);
      Generated _generated = new Generated(_plus, null, _content);
      writer.write(_generated);
    }
    
    public String getContent(final InformationModel model) {
      JsonFactory f = new JsonFactory();
      String json = "";
      try {
        StringWriter writer = new StringWriter();
        JsonGenerator g = f.createGenerator(writer);
        g.writeStartObject();
        this.generateThingTemplate(g, model);
        g.writeEndObject();
        g.flush();
        String _string = writer.toString();
        json = _string;
        g.close();
        writer.close();
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          return e.getMessage();
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      return json;
    }
    
    public void generateThingTemplate(final JsonGenerator g, final InformationModel model) {
      try {
        g.writeObjectFieldStart("thingTemplate");
        String _displayname = model.getDisplayname();
        g.writeStringField("name", _displayname);
        g.writeStringField("baseThingTemplate", "RemoteThing");
        String _description = model.getDescription();
        g.writeStringField("description", _description);
        g.writeStringField("tags", "Applications:Vorto_CodeGen");
        g.writeArrayFieldStart("propertyDefinitions");
        g.writeEndArray();
        g.writeArrayFieldStart("serviceDefinitions");
        g.writeEndArray();
        g.writeArrayFieldStart("eventDefinitions");
        g.writeEndArray();
        g.writeObjectFieldStart("alertConfigurations");
        g.writeEndObject();
        this.enumerateImplementedShapes(g, model);
        g.writeEndObject();
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    }
    
    protected void enumerateImplementedShapes(final JsonGenerator g, final InformationModel model) {
      try {
        g.writeArrayFieldStart("implementedShapes");
        EList<FunctionblockProperty> _properties = model.getProperties();
        for (final FunctionblockProperty functionBlock : _properties) {
          {
            FunctionblockModel fbModel = functionBlock.getType();
            g.writeStartObject();
            String _displayname = fbModel.getDisplayname();
            g.writeStringField("name", _displayname);
            g.writeStringField("type", "ThingShape");
            String _description = this.getDescription(fbModel);
            g.writeStringField("description", _description);
            g.writeStringField("tags", "Applications:Vorto_CodeGen");
            g.writeArrayFieldStart("propertyDefinitions");
            FunctionBlock _functionblock = fbModel.getFunctionblock();
            Status _status = _functionblock.getStatus();
            boolean _notEquals = (!Objects.equal(_status, null));
            if (_notEquals) {
              this.enumerateProperties(fbModel, g, ThingWorxCodeGenerator.VortoPropertyType.STATUS);
            }
            FunctionBlock _functionblock_1 = fbModel.getFunctionblock();
            Configuration _configuration = _functionblock_1.getConfiguration();
            boolean _notEquals_1 = (!Objects.equal(_configuration, null));
            if (_notEquals_1) {
              this.enumerateProperties(fbModel, g, ThingWorxCodeGenerator.VortoPropertyType.CONFIGURATION);
            }
            g.writeEndArray();
            g.writeArrayFieldStart("serviceDefinitions");
            this.enumerateServices(fbModel, g);
            g.writeEndArray();
            g.writeArrayFieldStart("eventDefinitions");
            g.writeEndArray();
            g.writeObjectFieldStart("alertConfigurations");
            g.writeArrayFieldStart("alertDefinitions");
            g.writeEndArray();
            g.writeEndObject();
            g.writeEndObject();
          }
        }
        g.writeEndArray();
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    }
    
    protected void enumerateProperties(final FunctionblockModel fbModel, final JsonGenerator g, final ThingWorxCodeGenerator.VortoPropertyType propType) {
      try {
        boolean readOnly = false;
        EList<Property> properties = null;
        boolean _equals = Objects.equal(propType, ThingWorxCodeGenerator.VortoPropertyType.STATUS);
        if (_equals) {
          readOnly = true;
          FunctionBlock _functionblock = fbModel.getFunctionblock();
          Status _status = _functionblock.getStatus();
          EList<Property> _properties = _status.getProperties();
          properties = _properties;
        } else {
          boolean _equals_1 = Objects.equal(propType, ThingWorxCodeGenerator.VortoPropertyType.CONFIGURATION);
          if (_equals_1) {
            FunctionBlock _functionblock_1 = fbModel.getFunctionblock();
            Configuration _configuration = _functionblock_1.getConfiguration();
            EList<Property> _properties_1 = _configuration.getProperties();
            properties = _properties_1;
            readOnly = false;
          }
        }
        for (final Property currentStatusProperty : properties) {
          PropertyType _type = currentStatusProperty.getType();
          if ((_type instanceof PrimitivePropertyType)) {
            String currentType = this.getPrimitivePropertyType(currentStatusProperty);
            g.writeStartObject();
            String _name = currentStatusProperty.getName();
            g.writeStringField("name", _name);
            String _thingWorxDataType = this.getThingWorxDataType(currentType);
            g.writeStringField("baseType", _thingWorxDataType);
            String _description = this.getDescription(currentStatusProperty);
            g.writeStringField("description", _description);
            g.writeBooleanField("isLocalOnly", false);
            g.writeObjectFieldStart("aspects");
            g.writeNumberField("cacheTime", 0.0);
            g.writeStringField("dataChangeType", "VALUE");
            g.writeBooleanField("isLogged", false);
            g.writeBooleanField("isPersistent", true);
            g.writeBooleanField("isReadOnly", readOnly);
            g.writeEndObject();
            g.writeEndObject();
          }
        }
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    }
    
    protected void enumerateServices(final FunctionblockModel fbModel, final JsonGenerator g) {
      try {
        FunctionBlock _functionblock = fbModel.getFunctionblock();
        EList<Operation> services = _functionblock.getOperations();
        for (final Operation currentService : services) {
          {
            g.writeStartObject();
            String _name = currentService.getName();
            g.writeStringField("name", _name);
            String _description = this.getDescription(currentService);
            g.writeStringField("description", _description);
            g.writeObjectFieldStart("resultType");
            g.writeStringField("name", "result");
            String _resultBaseType = this.getResultBaseType(currentService);
            g.writeStringField("baseType", _resultBaseType);
            String _description_1 = currentService.getDescription();
            g.writeStringField("description", _description_1);
            g.writeEndObject();
            g.writeArrayFieldStart("parameterDefinitions");
            EList<Param> _params = currentService.getParams();
            for (final Param currentParam : _params) {
              if ((currentParam instanceof PrimitiveParam)) {
                g.writeStartObject();
                String _name_1 = ((PrimitiveParam)currentParam).getName();
                g.writeStringField("name", _name_1);
                PrimitiveType _type = ((PrimitiveParam) currentParam).getType();
                String paramType = _type.getLiteral();
                String _thingWorxDataType = this.getThingWorxDataType(paramType);
                g.writeStringField("baseType", _thingWorxDataType);
                g.writeStringField("description", "");
                g.writeEndObject();
              }
            }
            g.writeEndArray();
            g.writeEndObject();
          }
        }
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    }
    
    protected String getDescription(final Property property) {
      String _description = property.getDescription();
      boolean _notEquals = (!Objects.equal(_description, null));
      if (_notEquals) {
        return property.getDescription();
      } else {
        return "";
      }
    }
    
    protected String getDescription(final Operation operation) {
      String _description = operation.getDescription();
      boolean _notEquals = (!Objects.equal(_description, null));
      if (_notEquals) {
        return operation.getDescription();
      } else {
        return "";
      }
    }
    
    protected String getDescription(final FunctionblockModel fbModel) {
      String _description = fbModel.getDescription();
      boolean _notEquals = (!Objects.equal(_description, null));
      if (_notEquals) {
        return fbModel.getDescription();
      } else {
        return "";
      }
    }
    
    protected String getPrimitivePropertyType(final Property property) {
      PropertyType _type = property.getType();
      if ((_type instanceof PrimitivePropertyType)) {
        PropertyType _type_1 = property.getType();
        PrimitiveType _type_2 = ((PrimitivePropertyType) _type_1).getType();
        return _type_2.toString();
      } else {
        return "UNDEFINED";
      }
    }
    
    protected String getResultBaseType(final Operation operation) {
      boolean _and = false;
      ReturnType _returnType = operation.getReturnType();
      boolean _notEquals = (!Objects.equal(_returnType, null));
      if (!_notEquals) {
        _and = false;
      } else {
        ReturnType _returnType_1 = operation.getReturnType();
        _and = (_returnType_1 instanceof ReturnPrimitiveType);
      }
      if (_and) {
        ReturnType _returnType_2 = operation.getReturnType();
        boolean _isMultiplicity = _returnType_2.isMultiplicity();
        boolean _equals = (_isMultiplicity == false);
        if (_equals) {
          ReturnType primitiveType = operation.getReturnType();
          PrimitiveType _returnType_3 = ((ReturnPrimitiveType) primitiveType).getReturnType();
          String typeName = _returnType_3.getLiteral();
          return this.getThingWorxDataType(typeName);
        } else {
          return "COMPLEX_TYPE";
        }
      } else {
        return "NOTHING";
      }
    }
    
    public String getThingWorxDataType(final String vortoType) {
      String dataType = "";
      Pair<String, String> _mappedTo = Pair.<String, String>of("base64Binary", "BLOB");
      Pair<String, String> _mappedTo_1 = Pair.<String, String>of("boolean", "BOOLEAN");
      Pair<String, String> _mappedTo_2 = Pair.<String, String>of("dateTime", "DATETIME");
      Pair<String, String> _mappedTo_3 = Pair.<String, String>of("int", "INTEGER");
      Pair<String, String> _mappedTo_4 = Pair.<String, String>of("short", "INTEGER");
      Pair<String, String> _mappedTo_5 = Pair.<String, String>of("long", "INTEGER");
      Pair<String, String> _mappedTo_6 = Pair.<String, String>of("byte", "INTEGER");
      Pair<String, String> _mappedTo_7 = Pair.<String, String>of("double", "NUMBER");
      Pair<String, String> _mappedTo_8 = Pair.<String, String>of("float", "NUMBER");
      Pair<String, String> _mappedTo_9 = Pair.<String, String>of("string", "STRING");
      final HashMap<String, String> typeMap = CollectionLiterals.<String, String>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3, _mappedTo_4, _mappedTo_5, _mappedTo_6, _mappedTo_7, _mappedTo_8, _mappedTo_9);
      String _get = typeMap.get(vortoType);
      dataType = _get;
      boolean _equals = Objects.equal(dataType, null);
      if (_equals) {
        dataType = "UNDEFINED";
      }
      return dataType;
    }
  }
  
  @Override
  public IGenerationResult generate(final InformationModel model, final InvocationContext invocationContext, final IVortoCodeGenProgressMonitor monitor) throws VortoCodeGeneratorException {
    String _serviceKey = this.getServiceKey();
    GenerationResultZip zipOutput = new GenerationResultZip(model, _serviceKey);
    ThingWorxCodeGenerator.JSONGeneratorTask _jSONGeneratorTask = new ThingWorxCodeGenerator.JSONGeneratorTask();
    _jSONGeneratorTask.generate(model, invocationContext, zipOutput);
    return zipOutput;
  }
  
  @Override
  public String getServiceKey() {
    return "thingworx";
  }
}
