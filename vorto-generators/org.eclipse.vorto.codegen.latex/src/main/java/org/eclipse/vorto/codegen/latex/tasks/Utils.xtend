/*******************************************************************************
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
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
package org.eclipse.vorto.codegen.latex.tasks

import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.BasicEMap
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.EMap
import org.eclipse.vorto.core.api.model.datatype.BooleanPropertyAttribute
import org.eclipse.vorto.core.api.model.datatype.BooleanPropertyAttributeType
import org.eclipse.vorto.core.api.model.datatype.Constraint
import org.eclipse.vorto.core.api.model.datatype.ConstraintIntervalType
import org.eclipse.vorto.core.api.model.datatype.EnumLiteral
import org.eclipse.vorto.core.api.model.datatype.EnumLiteralPropertyAttribute
import org.eclipse.vorto.core.api.model.datatype.EnumLiteralPropertyAttributeType
import org.eclipse.vorto.core.api.model.datatype.ObjectPropertyType
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType
import org.eclipse.vorto.core.api.model.datatype.PrimitiveType
import org.eclipse.vorto.core.api.model.datatype.Property
import org.eclipse.vorto.core.api.model.datatype.PropertyAttribute
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel
import org.eclipse.vorto.core.api.model.functionblock.Operation
import org.eclipse.vorto.core.api.model.functionblock.ReturnObjectType
import org.eclipse.vorto.core.api.model.functionblock.ReturnPrimitiveType
import org.eclipse.vorto.core.api.model.functionblock.ReturnType
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty

class Utils {

	def static String getReturnType(ReturnType type) {
		if (type instanceof ReturnPrimitiveType) {
			return (type as ReturnPrimitiveType).getReturnType().getName();
		} else if (type instanceof ReturnObjectType) {
			return (type as ReturnObjectType).getReturnType().getName();
		} else {
			return null;
		}
	}

	def static String getMeasurementUnit(Property property) {
		var literal = getEnumLiteralPropertyAttribute(property, EnumLiteralPropertyAttributeType.MEASUREMENT_UNIT);
		if (literal != null) {
			return literal.getName();
		}
		return "";
	}

	def static EnumLiteral getEnumLiteralPropertyAttribute(Property property, EnumLiteralPropertyAttributeType type) {
		if ((property.getPropertyAttributes() != null) && (property.getPropertyAttributes().size() > 0)) {
			for (PropertyAttribute pA : property.getPropertyAttributes()) {
				if (pA instanceof EnumLiteralPropertyAttribute) {
					var bPA = pA as EnumLiteralPropertyAttribute;
					if (bPA.getType() == type) {
						return bPA.getValue();
					}
				}
			}
		}
		return null;
	}

	def static String getMinConstraint(Property property) {
		if ((property.getConstraintRule() !== null && property.getConstraintRule().getConstraints() !== null) &&
			(property.getConstraintRule() !== null && property.getConstraintRule().getConstraints().size() > 0)) {
			for (Constraint constraint : property.getConstraintRule().getConstraints()) {
				if (constraint.getType() == ConstraintIntervalType.MIN) {
					return constraint.getConstraintValues();
				}
			}
		}
		return "";
	}

	def static String getMaxConstraint(Property property) {
		if ((property.getConstraintRule() !== null && property.getConstraintRule().getConstraints() !== null) &&
			(property.getConstraintRule() !== null && property.getConstraintRule().getConstraints().size() > 0)) {
			for (Constraint constraint : property.getConstraintRule().getConstraints()) {
				if (constraint.getType() == ConstraintIntervalType.MAX) {
					return constraint.getConstraintValues();
				}
			}
		}
		return "";
	}

	/**
	 * Returns the return type as string
	 */
	def static String getReturnTypeAsString(Operation op) {
		if (op.returnType !== null) {
			var returnType = op.returnType;
			if (returnType instanceof ReturnObjectType) {
				return (returnType as ReturnObjectType).returnType.name;
			} else if (returnType instanceof ReturnPrimitiveType) {
				return (returnType as ReturnPrimitiveType).returnType.getName();
			}
		} else {
			return "void";
		}
	}

	/**
	 * Returns the name of the return type or 'returnValue' as string
	 */
	def static String getReturnNameAsString(ReturnType returnType) {
		if (returnType instanceof ReturnObjectType) {
			return (returnType as ReturnObjectType).returnType.name.toFirstLower;
		} else if (returnType instanceof ReturnPrimitiveType) {
			return "returnValue";
		}
	}

	def static String getPropertyType(Property property) {
		if (property.getType() instanceof PrimitivePropertyType) {
			return (property.getType() as PrimitivePropertyType).getType().getName();
		} else if (property.getType() instanceof ObjectPropertyType) {
			return (property.getType() as ObjectPropertyType).getType().getName();
		} else {
			return null;
		}
	}

	def static boolean isReadable(Property property) {
		return getBooleanPropertyAttribute(property, BooleanPropertyAttributeType.READABLE);
	}

	def static boolean isWritable(Property property) {
		return getBooleanPropertyAttribute(property, BooleanPropertyAttributeType.WRITABLE);
	}

	def static boolean isEventable(Property property) {
		return getBooleanPropertyAttribute(property, BooleanPropertyAttributeType.EVENTABLE);
	}

	/**
	 * Sorts all functionblock properties according to their functionblock model
	 */
	def static EMap<FunctionblockModel, EList<FunctionblockProperty>> sortByPropertyType(
		EList<FunctionblockProperty> properties) {
		var EMap<FunctionblockModel, EList<FunctionblockProperty>> map = new BasicEMap();

		for (FunctionblockProperty property : properties) {
			if (map.containsKey(property.getType())) {
				var EList<FunctionblockProperty> eList = map.get(property.getType());
				eList.add(property);
				map.put(property.getType(), eList);
			} else {
				var EList<FunctionblockProperty> eList = new BasicEList<FunctionblockProperty>();
				eList.add(property);
				map.put(property.getType(), eList);
			}
		}
		return map;
	}

	def static boolean getBooleanPropertyAttribute(Property property, BooleanPropertyAttributeType type) {
		if ((property.getPropertyAttributes() !== null) && (property.getPropertyAttributes().size() > 0)) {
			for (PropertyAttribute pA : property.getPropertyAttributes()) {
				if (pA instanceof BooleanPropertyAttribute) {
					var bPA = pA as BooleanPropertyAttribute;
					if (bPA.getType() == type && bPA.isValue()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	def static boolean isSimpleNumeric(Property property) {
		if (property.getType() instanceof PrimitivePropertyType) {
			var primitiveType = (property.getType() as PrimitivePropertyType).getType();
			if (primitiveType == PrimitiveType.INT) {
				return true;
			} else if (primitiveType == PrimitiveType.FLOAT) {
				return true;
			} else if (primitiveType == PrimitiveType.DOUBLE) {
				return true;
			} else if (primitiveType == PrimitiveType.LONG) {
				return true;
			} else if (primitiveType == PrimitiveType.SHORT) {
				return true;
			}
		}
		return false;
	}

}
