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
package org.eclipse.vorto.codegen.ble.model.blegatt.util

import org.eclipse.vorto.core.api.model.datatype.BooleanPropertyAttribute
import org.eclipse.vorto.core.api.model.datatype.BooleanPropertyAttributeType
import org.eclipse.vorto.core.api.model.datatype.Property
import org.eclipse.vorto.core.api.model.datatype.PropertyAttribute

class Utils {

	
	def static boolean isReadable(Property property) {
		return getBooleanPropertyAttribute(property, BooleanPropertyAttributeType.READABLE);
	}

	def static boolean isWritable(Property property) {
		return getBooleanPropertyAttribute(property, BooleanPropertyAttributeType.WRITABLE);
	}

	def static boolean isEventable(Property property) {
		return getBooleanPropertyAttribute(property, BooleanPropertyAttributeType.EVENTABLE);
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
}
