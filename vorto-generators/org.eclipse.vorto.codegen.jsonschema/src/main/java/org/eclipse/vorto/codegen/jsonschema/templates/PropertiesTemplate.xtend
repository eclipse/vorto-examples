/*******************************************************************************
 * Copyright (c) 2017 Bosch Software Innovations GmbH and others.
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
package org.eclipse.vorto.codegen.jsonschema.templates;

import java.util.ArrayList
import org.eclipse.vorto.codegen.api.IFileTemplate
import org.eclipse.vorto.codegen.api.InvocationContext
import org.eclipse.vorto.core.api.model.datatype.Property
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel

class PropertiesTemplate implements IFileTemplate<FunctionblockModel> {
	
	override getContent(FunctionblockModel fbm, InvocationContext invocationContext) {
		var definition = fbm.namespace + ":" + fbm.name + ":" + fbm.version;
		
		val properties = new ArrayList<Property>();
		if (fbm.functionblock.configuration !== null && fbm.functionblock.configuration.properties !== null) {
			for(property : fbm.functionblock.configuration.properties) {
				properties.add(property);	
			}	
		}
		
		if (fbm.functionblock.status !== null && fbm.functionblock.status.properties !== null) {
			for(property : fbm.functionblock.status.properties) {
				properties.add(property);	
			}
		}
		
		'''
			{
				"$schema": "http://json-schema.org/draft-04/schema#",
				"title": "Properties of <«definition»>",
				"type": "object",
				"properties": {
					«EntityTemplate.handleProperties(properties, invocationContext).toString.trim»
				},
				«EntityTemplate.calculateRequired(properties)»
			}
		'''
	}
	
	override getFileName(FunctionblockModel fbm) {
		fbm.namespace+"_"+fbm.name+"_"+fbm.version.replace(".","_")+".schema.json"
	}
	
	override getPath(FunctionblockModel context) {
		return null
	}
		
}
