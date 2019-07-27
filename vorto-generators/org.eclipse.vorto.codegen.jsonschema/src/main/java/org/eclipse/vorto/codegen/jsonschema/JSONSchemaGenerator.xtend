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
package org.eclipse.vorto.codegen.jsonschema

import org.eclipse.vorto.codegen.jsonschema.templates.PropertiesTemplate
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel
import org.eclipse.vorto.plugin.generator.GeneratorException
import org.eclipse.vorto.plugin.generator.GeneratorPluginInfo
import org.eclipse.vorto.plugin.generator.ICodeGenerator
import org.eclipse.vorto.plugin.generator.IGenerationResult
import org.eclipse.vorto.plugin.generator.InvocationContext
import org.eclipse.vorto.plugin.generator.utils.GenerationResultZip
import org.eclipse.vorto.plugin.generator.utils.GeneratorTaskFromFileTemplate
import org.eclipse.vorto.plugin.generator.utils.IGeneratedWriter
import org.eclipse.vorto.plugin.generator.utils.SingleGenerationResult

class JSONSchemaGenerator implements ICodeGenerator {
	
	val static String KEY = "jsonschema"

	override generate(InformationModel infomodel, InvocationContext context) throws GeneratorException {
		var IGenerationResult output = null
		if (infomodel.properties.size == 1) {
			output = new SingleGenerationResult("application/schema+json")
		} else {
			output = new GenerationResultZip(infomodel,KEY);
		}
		
		for (FunctionblockProperty fbProperty : infomodel.getProperties()) {
		 	new GeneratorTaskFromFileTemplate(new PropertiesTemplate()).generate(fbProperty.type,context, output as IGeneratedWriter);
		}
		
		return output
	}
	
	override getMeta() {
		return GeneratorPluginInfo.Builder(KEY)
			.withDescription("Generates JSON Schema from Vorto Models")
			.withName("JSON Schema")
			.withDocumentationUrl("https://json-schema.org")
			.withVendor("Eclipse Vorto Team")
			.build
	}
}
