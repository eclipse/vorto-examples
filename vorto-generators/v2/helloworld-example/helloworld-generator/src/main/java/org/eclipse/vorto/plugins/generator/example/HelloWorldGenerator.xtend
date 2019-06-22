/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.plugins.generator.example

import org.eclipse.vorto.core.api.model.informationmodel.InformationModel
import org.eclipse.vorto.plugin.generator.GeneratorException
import org.eclipse.vorto.plugin.generator.GeneratorPluginInfo
import org.eclipse.vorto.plugin.generator.ICodeGenerator
import org.eclipse.vorto.plugin.generator.InvocationContext
import org.eclipse.vorto.plugin.generator.config.ConfigTemplateBuilder
import org.eclipse.vorto.plugin.generator.config.ConfigTemplateBuilder.ChoiceItem
import org.eclipse.vorto.plugin.generator.utils.GenerationResultZip
import org.eclipse.vorto.plugin.generator.utils.GeneratorTaskFromFileTemplate
import org.eclipse.vorto.plugin.generator.utils.IFileTemplate

class HelloWorldGenerator implements ICodeGenerator {
	
	static val String KEY = "helloworld"
	static val String KEY_LANG = "lang"

	val static String LOGO_144x144 = "<base64 encoded logo>";
  	val static String LOGO_32x32 = "<base64 encoded logo>";

	override generate(InformationModel infomodel, InvocationContext context) throws GeneratorException {
		var output = new GenerationResultZip(infomodel,KEY);
		
		new GeneratorTaskFromFileTemplate(new HelloWorldTemplate).generate(infomodel,context,output)
		
		return output
	}
	
	/**
	 * Meta plugin information about the hello world generator.
	 */
	override getMeta() {
		return GeneratorPluginInfo.Builder(KEY)
			// Defines configuration params that can be passed to the generator at runtime
			.withConfigurationKey(KEY_LANG)
			.withConfigurationTemplate(ConfigTemplateBuilder.builder.withChoiceConfigurationItem(KEY_LANG,"Language",ChoiceItem.of("English","en"),ChoiceItem.of("German","de")).build)
			.withImage32x32(LOGO_32x32)
			.withImage144x144(LOGO_144x144)
			.withName("Hello World Generator")
			.withDescription("Generates a txt file that contains language specific greeting")
			.withDocumentationUrl("http://www.eclipse.org/vorto")
			.withVendor("Eclipse Vorto Team")
			.build
	}
	
	private static class HelloWorldTemplate implements IFileTemplate<InformationModel> {
		
		override getFileName(InformationModel context) {
			return "helloworld.txt"
		}
		
		override getPath(InformationModel context) {
			return null
		}
		
		override getContent(InformationModel model, InvocationContext context) {
			'''
			«IF context.configurationProperties.getOrDefault(KEY_LANG,"undefined").equals("en")»
			// Generated from the following model '«model.namespace»:«model.name»:«model.version»'
			Hello «model.name»
			«ELSEIF context.configurationProperties.getOrDefault(KEY_LANG,"undefined").equals("de")»
			// Generiert mit folgendem Modell '«model.namespace»:«model.name»:«model.version»'
			Hallo «model.name»
			«ELSE»
			// Generated from the following model '«model.namespace»:«model.name»:«model.version»'
			Hello «model.name»
			«ENDIF»
			
			'''
		}
		
	}
	
}
