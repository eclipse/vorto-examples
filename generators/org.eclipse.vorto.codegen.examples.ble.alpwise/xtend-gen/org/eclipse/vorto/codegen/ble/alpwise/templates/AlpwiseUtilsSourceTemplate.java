package org.eclipse.vorto.codegen.ble.alpwise.templates;

import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.ble.templates.BleGattTemplate;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class AlpwiseUtilsSourceTemplate extends BleGattTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    return "BleUtils.c";
  }
  
  @Override
  public String getPath(final InformationModel context) {
    return BleGattTemplate.rootPath;
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include <stdint.h>");
    _builder.newLine();
    _builder.append("#include <stdio.h>");
    _builder.newLine();
    _builder.append("#include <BleTypes.h>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"BleUtils.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void printSendNotificationError(BleStatus status) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (status == BLESTATUS_SUCCESS) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("printf(\"SendIndication status: BLESTATUS_SUCCESS\\n\");");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} else if (status == BLESTATUS_FAILED) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("printf(\"SendIndication status: BLESTATUS_FAILED\\n\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} else if (status == BLESTATUS_INVALID_PARMS) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("printf(\"SendIndication status: BLESTATUS_INVALID_PARMS\\n\");");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} else if (status == BLESTATUS_PENDING) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("printf(\"SendIndication status: BLESTATUS_PENDING\\n\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} else if (status == BLESTATUS_BUSY) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("printf(\"SendIndication status: BLESTATUS_BUSY\\n\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("printf(\"SendIndication status: other Error!\\n\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
