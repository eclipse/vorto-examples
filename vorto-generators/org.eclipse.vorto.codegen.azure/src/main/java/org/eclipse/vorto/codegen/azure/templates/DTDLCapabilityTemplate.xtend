package org.eclipse.vorto.codegen.azure.templates

import java.util.ArrayList
import org.eclipse.vorto.codegen.api.IFileTemplate
import org.eclipse.vorto.codegen.api.InvocationContext
import org.eclipse.vorto.core.api.model.datatype.DictionaryPropertyType
import org.eclipse.vorto.core.api.model.datatype.Entity
import org.eclipse.vorto.core.api.model.datatype.Enum
import org.eclipse.vorto.core.api.model.datatype.ObjectPropertyType
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType
import org.eclipse.vorto.core.api.model.datatype.PrimitiveType
import org.eclipse.vorto.core.api.model.datatype.PropertyType
import org.eclipse.vorto.core.api.model.functionblock.Configuration
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel

class DTDLCapabilityTemplate implements IFileTemplate<InformationModel> {
	
	override getFileName(InformationModel infomodel) {
		return infomodel.namespace+"_"+infomodel.name+"_CapabilityModel.json"
	}
	
	override getPath(InformationModel infomodel) {
		return null;
	}
	
	override getContent(InformationModel infomodel, InvocationContext context) {
		'''
		{
			"@context": [
			 	"http://azureiot.com/v0/contexts/CapabilityModel.json",
			    "http://azureiot.com/v0/contexts/Interface.json"
			],
		    "@id": "«Utils.createURI(infomodel)»",
		    "@type": "CapabilityModel",
		    "displayName": "«infomodel.name»",
		    "comment": "«infomodel.description»",
		    "implements": [
		    	«FOR fbProperty : infomodel.properties SEPARATOR ","»
		    	{
		    		"@id": "«Utils.createURI(fbProperty.type)»",
		    		"@type": "Interface",
		    		"comment": "«fbProperty.type.description»",
		    		«IF fbProperty.type.displayname !== null»"displayName": "«fbProperty.type.displayname»",«ENDIF»
		    		"contents": [
		    		«FOR property : flatProperties(fbProperty.type.functionblock) SEPARATOR ","»
		    			{
		    				"@id": "«Utils.createURI(fbProperty.type)»/«property.name»",
		    				"@type": «IF property.eContainer instanceof Configuration»"Property"«ELSE»"Telemetry"«ENDIF»,
		    				"name" : "«property.name»",
		    				«IF property.description !== null»"comment": "«property.description»",«ENDIF»
		    				«IF property.eContainer instanceof Configuration»"writable": true,«ENDIF»
		    				«createSchema(property.type,property.name,property.isMultiplicity)»
		    			}
		    		«ENDFOR»
		    		]
		    	}
		    	«ENDFOR»
		    ]
		}
		'''
	}
	
	def flatProperties(FunctionBlock fb) {
		var properties = new ArrayList();
		if (fb.configuration !== null) {
			properties.addAll(fb.configuration.properties);
		}
		if (fb.status !== null) {
			properties.addAll(fb.status.properties);
		}
		return properties
	}
	
	def convertToObject(ObjectPropertyType objectType) {
		var counter = 1;
		''' {
			«IF objectType.type instanceof Enum»
			 "@type": "Enum",
			 "enumValues": [
			 	«FOR literal : (objectType.type as Enum).enums SEPARATOR ","»
			 		{
			 			"name": "«literal.name»",
			 		    "enumValue": «counter++»
			 		}
			 	«ENDFOR»
			 ]
			 «ELSE»
			 "@type": "Object",
			  "fields": [
			  	«FOR property : (objectType.type as Entity).properties SEPARATOR ","»
			  		{
			  			"name": "«property.name»",
			  		    «createSchema(property.type,property.name,property.isMultiplicity)»
			  		}
			  	«ENDFOR»
			  ]
			«ENDIF»
		}
		'''
	}
	
	def convertToMap(DictionaryPropertyType dictionaryType, String propertyName) {
		''' {
			"@type": "Map",
			"mapKey": {
				 "name": "«propertyName»Key",
			     «createSchema(dictionaryType,propertyName,false)»
			},
			"mapValue": {
				"name": "«propertyName»Value",
			    «createSchema(dictionaryType,propertyName,false)»
			}
		}
		'''
	}
	
	def String createSchema(PropertyType type, String propName, boolean isMultiple) {
		'''
		"schema" : «IF type instanceof PrimitivePropertyType && !isMultiple»"«convertPrimitive(type as PrimitivePropertyType)»"«ELSEIF type instanceof PrimitivePropertyType && isMultiple»«convertToPrimitiveArray(type as PrimitivePropertyType)»«ELSEIF type instanceof DictionaryPropertyType»«convertToMap(type as DictionaryPropertyType,propName)»«ELSE»«convertToObject(type as ObjectPropertyType)»«ENDIF»
		'''
	}
	
	def convertToPrimitiveArray(PrimitivePropertyType primitiveType) {
		''' {
			"@type": "Array",
			"elementSchema": "«convertPrimitive(primitiveType)»"
		}
		'''
	}
	
	def convertPrimitive(PrimitivePropertyType primitiveType) {
		if (primitiveType.type === PrimitiveType.STRING) {
			return "string"
		} else if (primitiveType.type === PrimitiveType.BOOLEAN) {
			return "boolean"
		} else if (primitiveType.type === PrimitiveType.DATETIME) {
			return "datetime"
		} else if (primitiveType.type === PrimitiveType.DOUBLE) {
			return "double"
		} else if (primitiveType.type === PrimitiveType.FLOAT) {
			return "float"
		} else if (primitiveType.type === PrimitiveType.INT) {
			return "integer"
		} else if (primitiveType.type === PrimitiveType.LONG) {
			return "long"
		} else if (primitiveType.type === PrimitiveType.LONG) {
			return "long"
		} else {
			return "string"
		}
	}
	
	
}