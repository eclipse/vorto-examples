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
package org.eclipse.vorto.plugins.importer.example.lwm2m

import org.eclipse.vorto.model.ModelId

class MappingTemplate {
	
	def String create(LWM2M.Object source, ModelId modelId) {
		'''
			vortolang 1.0
			
			namespace «modelId.namespace»
			version «modelId.version»
			displayname "«modelId.name»"
			description "LWM2M Mapping Model for «modelId.name»"
			
			using «modelId.namespace».«modelId.name»;«modelId.version»
			
			functionblockmapping «modelId.name»_lwm2m {
				targetplatform lwm2m
				
				from «modelId.name» to Object with {Name: "«source.name»", ObjectID: "«source.objectID»", ObjectURL: "«source.objectURN»", MultipleInstances: "«source.multipleInstances»", Mandatory: "«source.mandatory»", Description2: "«source.description2.replace("\"","'")»"}
				
				«FOR item : source.getResources.item»
					«IF item.operations == "RW" || item.operations == "W"»
						from «modelId.name».configuration.«parseName(item.name).toFirstLower» to Resource with {ID: "«item.id»", Name: "«item.name»", Units: "«item.units.replace("\"","'")»"}
					«ENDIF»
				«ENDFOR»
				
				«FOR item : source.getResources.item»
					«IF item.operations == "R"»
						from «modelId.name».status.«parseName(item.name).toFirstLower» to Resource with {ID: "«item.id»", Name: "«item.name»", Units: "«item.units.replace("\"","'")»"}
					«ENDIF»
				«ENDFOR»
				
				«FOR item : source.getResources.item»
					«IF item.operations == "E"»
						from «modelId.name».operation.«parseName(item.name).toFirstLower» to Resource with {ID: "«item.id»", Name: "«item.name»", MultipleInstances: "«item.multipleInstances»", Mandatory: "«item.mandatory»"}
					«ENDIF»
				«ENDFOR»
			}
		'''
	}
	
	def parseName(String name) {
		return name.replace(" ","").replace("/","").replace("-","")
	}
	
}
