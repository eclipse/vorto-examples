package org.eclipse.vorto.codegen.ble.alpwise.templates;

import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.ble.templates.BleGattTemplate;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class AlpwiseUtilsHeaderTemplate extends BleGattTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    return "BleUtils.h";
  }
  
  @Override
  public String getPath(final InformationModel context) {
    return BleGattTemplate.rootPath;
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef __BLEUTILS_H__");
    _builder.newLine();
    _builder.append("#define __BLEUTILS_H__");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include <stdint.h>");
    _builder.newLine();
    _builder.append("#include <attbase.h>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Common properties for characteristic entries in the attribute database");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("typedef struct characteristicProperty_s {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("uint8_t                          uuid[16];        /* Characteristic UUID */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("AttCharacteriticProperties       flags;           /* Access flags */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("AttUuid                          type;            /* UUID type and pointer to UUID */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Att128BitCharacteristicAttribute characteristic;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("AttAttribute                     attr;            /* Attribute handle */");
    _builder.newLine();
    _builder.append("} characteristicProperty_t;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**@brief This function prints out the error return value when a notification is sent.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param[in] status The return value of the send notifcation function");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("void printSendNotificationError(BleStatus status);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif /* __BLEUTILS_H__ */");
    _builder.newLine();
    return _builder.toString();
  }
}
