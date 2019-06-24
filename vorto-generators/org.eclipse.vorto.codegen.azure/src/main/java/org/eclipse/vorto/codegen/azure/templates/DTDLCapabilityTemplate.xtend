package org.eclipse.vorto.codegen.azure.templates

import java.util.ArrayList
import org.eclipse.emf.common.util.EList
import org.eclipse.vorto.core.api.model.datatype.DictionaryPropertyType
import org.eclipse.vorto.core.api.model.datatype.Entity
import org.eclipse.vorto.core.api.model.datatype.Enum
import org.eclipse.vorto.core.api.model.datatype.ObjectPropertyType
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType
import org.eclipse.vorto.core.api.model.datatype.PrimitiveType
import org.eclipse.vorto.core.api.model.datatype.PropertyType
import org.eclipse.vorto.core.api.model.datatype.Type
import org.eclipse.vorto.core.api.model.functionblock.Configuration
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock
import org.eclipse.vorto.core.api.model.functionblock.Param
import org.eclipse.vorto.core.api.model.functionblock.ReturnDictonaryType
import org.eclipse.vorto.core.api.model.functionblock.ReturnObjectType
import org.eclipse.vorto.core.api.model.functionblock.ReturnPrimitiveType
import org.eclipse.vorto.core.api.model.functionblock.ReturnType
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel
import org.eclipse.vorto.plugin.generator.InvocationContext
import org.eclipse.vorto.plugin.generator.utils.IFileTemplate
import org.eclipse.vorto.core.api.model.functionblock.PrimitiveParam
import org.eclipse.vorto.core.api.model.functionblock.DictonaryParam
import org.eclipse.vorto.core.api.model.functionblock.RefParam

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
		    		«IF !fbProperty.type.functionblock.operations.isEmpty»,«ENDIF»
		    		«FOR operation : fbProperty.type.functionblock.operations SEPARATOR ","»
		    			{
		    				"@id": "«Utils.createURI(fbProperty.type)»/«operation.name»",
		    				"@type": "Command",
		    				«IF operation.description !== null»"description" : "«operation.description»",«ENDIF»
		    				"name": "«operation.name»",
		    				"commandType" : "synchronous"«IF !operation.params.isEmpty || operation.returnType !== null»,«ENDIF»
		    				«IF !operation.params.isEmpty»
		    				"request" : {
		    					«createCommandRequest(operation.params)»
		    				}«IF operation.returnType !== null»,«ENDIF»
		    				«ENDIF»
		    				«IF operation.returnType !== null»
		    				"response": {
		    					"name": "«operation.name»Response",
		    				    «createSchema(operation.returnType,operation.name,operation.returnType.multiplicity)»
		    				}
		    				«ENDIF»
		    			}
		    		«ENDFOR»
		    		]
		    	}
		    	«ENDFOR»
		    ]
		}
		'''
	}
	
	/**
	 * Currently supports only params with a length of 1. If many params per operations are defined, it would ideally create a wrapper param object here.
	 * FIXME!
	 */
	def createCommandRequest(EList<Param> params) {
		'''
		"name": "«params.get(0).name»",
		«IF params.get(0).description !== null»"description": "«params.get(0).description»",«ENDIF»
		«createSchema(params.get(0),params.get(0).name,params.get(0).isMultiplicity)»
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
		
	def convertToObject(Type objectType) {
		var counter = 1;
		''' {
			«IF objectType instanceof Enum»
			 "@type": "Enum",
			 "enumValues": [
			 	«FOR literal : (objectType as Enum).enums SEPARATOR ","»
			 		{
			 			"name": "«literal.name»",
			 		    "enumValue": «counter++»
			 		}
			 	«ENDFOR»
			 ]
			 «ELSE»
			 "@type": "Object",
			  "fields": [
			  	«FOR property : (objectType as Entity).properties SEPARATOR ","»
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
	
	def convertToMap(ReturnDictonaryType dictionaryType, String propertyName) {
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
	
	def convertToMap(DictonaryParam dictionaryType, String propertyName) {
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
	
	def String createSchema(Param type, String propName, boolean isMultiple) {
		'''
		"schema" : «IF type instanceof PrimitiveParam && !isMultiple»"«convertPrimitive((type as PrimitiveParam).type)»"«ELSEIF type instanceof PrimitiveParam && isMultiple»«convertToPrimitiveArray((type as PrimitiveParam).type)»«ELSEIF type instanceof DictonaryParam»«convertToMap(type as DictonaryParam,propName)»«ELSE»«convertToObject((type as RefParam).type)»«ENDIF»
		'''
	}
	
	def String createSchema(ReturnType type, String propName, boolean isMultiple) {
		'''
		"schema" : «IF type instanceof ReturnPrimitiveType && !isMultiple»"«convertPrimitive((type as ReturnPrimitiveType).returnType)»"«ELSEIF type instanceof ReturnPrimitiveType && isMultiple»«convertToPrimitiveArray((type as ReturnPrimitiveType).returnType)»«ELSEIF type instanceof ReturnDictonaryType»«convertToMap(type as ReturnDictonaryType,propName)»«ELSE»«convertToObject((type as ReturnObjectType).returnType)»«ENDIF»
		'''
	}
	
	def String createSchema(PropertyType type, String propName, boolean isMultiple) {
		'''
		"schema" : «IF type instanceof PrimitivePropertyType && !isMultiple»"«convertPrimitive((type as PrimitivePropertyType).type)»"«ELSEIF type instanceof PrimitivePropertyType && isMultiple»«convertToPrimitiveArray((type as PrimitivePropertyType).type)»«ELSEIF type instanceof DictionaryPropertyType»«convertToMap(type as DictionaryPropertyType,propName)»«ELSE»«convertToObject((type as ObjectPropertyType).type)»«ENDIF»
		'''
	}
	
	def convertToPrimitiveArray(PrimitiveType primitiveType) {
		''' {
			"@type": "Array",
			"elementSchema": "«convertPrimitive(primitiveType)»"
		}
		'''
	}
	
	def convertPrimitive(PrimitiveType primitiveType) {
		if (primitiveType === PrimitiveType.STRING) {
			return "string"
		} else if (primitiveType === PrimitiveType.BOOLEAN) {
			return "boolean"
		} else if (primitiveType === PrimitiveType.DATETIME) {
			return "datetime"
		} else if (primitiveType === PrimitiveType.DOUBLE) {
			return "double"
		} else if (primitiveType === PrimitiveType.FLOAT) {
			return "float"
		} else if (primitiveType === PrimitiveType.INT) {
			return "integer"
		} else if (primitiveType === PrimitiveType.LONG) {
			return "long"
		} else if (primitiveType === PrimitiveType.LONG) {
			return "long"
		} else {
			return "string"
		}
	}
	
	
}