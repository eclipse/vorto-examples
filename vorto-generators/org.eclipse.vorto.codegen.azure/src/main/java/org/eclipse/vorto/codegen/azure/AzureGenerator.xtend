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
package org.eclipse.vorto.codegen.azure

import org.eclipse.vorto.codegen.azure.templates.DTDLCapabilityTemplate
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel
import org.eclipse.vorto.plugin.generator.GeneratorException
import org.eclipse.vorto.plugin.generator.ICodeGenerator
import org.eclipse.vorto.plugin.generator.InvocationContext
import org.eclipse.vorto.plugin.generator.utils.GenerationResultZip
import org.eclipse.vorto.plugin.generator.utils.GeneratorTaskFromFileTemplate
import org.eclipse.vorto.plugin.generator.GeneratorPluginInfo

class AzureGenerator implements ICodeGenerator {
	
	static val String KEY = "azure"

	override generate(InformationModel infomodel, InvocationContext context) throws GeneratorException {
		var output = new GenerationResultZip(infomodel,KEY);
		
		new GeneratorTaskFromFileTemplate(new DTDLCapabilityTemplate()).generate(infomodel,context,output);

		return output
	}
	
	override getMeta() {
		return GeneratorPluginInfo.Builder(KEY)
			.withDescription("Generates Azure DTDL file")
			.withName("Azure IoT Plug&Play")
			.withDocumentationUrl("https://github.com/Azure/IoTPlugandPlay")
			.withVendor("Eclipse Vorto Team")
			.build
	}
	
}
