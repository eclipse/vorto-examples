/*******************************************************************************
 * Copyright (c) 2015, 2016 Bosch Software Innovations GmbH and others.
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
 *******************************************************************************/
package org.eclipse.vorto.codegen.protobuf

import org.eclipse.vorto.codegen.protobuf.templates.ProtobufEntityTemplate
import org.eclipse.vorto.codegen.protobuf.templates.ProtobufEnumTemplate
import org.eclipse.vorto.codegen.protobuf.templates.ProtobufFBTemplate
import org.eclipse.vorto.codegen.protobuf.templates.ProtobufIMTemplate
import org.eclipse.vorto.codegen.protobuf.templates.ProtobufMetaTemplate
import org.eclipse.vorto.core.api.model.datatype.Entity
import org.eclipse.vorto.core.api.model.datatype.Enum
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel
import org.eclipse.vorto.plugin.generator.GeneratorException
import org.eclipse.vorto.plugin.generator.GeneratorPluginInfo
import org.eclipse.vorto.plugin.generator.ICodeGenerator
import org.eclipse.vorto.plugin.generator.InvocationContext
import org.eclipse.vorto.plugin.generator.utils.GenerationResultZip
import org.eclipse.vorto.plugin.generator.utils.GeneratorTaskFromFileTemplate
import org.eclipse.vorto.plugin.utils.Utils

class ProtobufGenerator implements ICodeGenerator {
	
	static val String KEY = "protobuf"

	override generate(InformationModel infomodel, InvocationContext context) throws GeneratorException {
		var output = new GenerationResultZip(infomodel,KEY);
		
		var metaTemplate = new GeneratorTaskFromFileTemplate(new ProtobufMetaTemplate())
		metaTemplate.generate(infomodel,context,output)
		
		var imTemplate = new GeneratorTaskFromFileTemplate(new ProtobufIMTemplate())
		imTemplate.generate(infomodel,context,output)
		
		for (FunctionblockProperty fbProperty : infomodel.properties) {
			var fbTemplate = new GeneratorTaskFromFileTemplate(new ProtobufFBTemplate())
			fbTemplate.generate(fbProperty.type,context,output)
			
			for (Entity entity : Utils.getReferencedEntities(fbProperty.type.functionblock)) {
				var entityTemplate = new GeneratorTaskFromFileTemplate(new ProtobufEntityTemplate())
				entityTemplate.generate(entity,context,output)
			}
			for (Enum en : Utils.getReferencedEnums(fbProperty.type.functionblock)) {
				var enumTemplate = new GeneratorTaskFromFileTemplate(new ProtobufEnumTemplate())
				enumTemplate.generate(en,context,output)
			}
		}
		
		return output
	}
	
	override getMeta() {
		return GeneratorPluginInfo.Builder(KEY)
			.withDescription("Generates Google RPC services and protobuf messages")
			.withName("Google Protobuf")
			.withDocumentationUrl("https://json-schema.org")
			.withVendor("Eclipse Vorto Team")
			.build
	}
}
