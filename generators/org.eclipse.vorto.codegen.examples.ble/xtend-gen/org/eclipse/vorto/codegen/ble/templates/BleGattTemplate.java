/**
 * Copyright (c) 2017 Oliver Meili
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  The Eclipse Distribution License is available at
 *  http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 *  Contributors:
 *  Oliver Meili <omi@ieee.org>
 */
package org.eclipse.vorto.codegen.ble.templates;

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.ble.model.blegatt.Characteristic;
import org.eclipse.vorto.codegen.ble.model.blegatt.CharacteristicProperty;
import org.eclipse.vorto.codegen.ble.model.blegatt.Device;
import org.eclipse.vorto.codegen.ble.model.blegatt.Service;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.model.Model;

@SuppressWarnings("all")
public abstract class BleGattTemplate<T extends Model> implements IFileTemplate<T> {
  public static String rootPath;
  
  public Service findService(final Device device, final FunctionblockModel fbm) {
    EList<Service> _services = device.getServices();
    for (final Service s : _services) {
      EList<FunctionblockModel> _functionblocks = s.getFunctionblocks();
      for (final FunctionblockModel f : _functionblocks) {
        boolean _equals = fbm.equals(f);
        if (_equals) {
          return s;
        }
      }
    }
    return null;
  }
  
  public Characteristic findCharacteristic(final Device device, final Property property) {
    EList<Service> _services = device.getServices();
    for (final Service s : _services) {
      EList<Characteristic> _characteristics = s.getCharacteristics();
      for (final Characteristic ch : _characteristics) {
        EList<CharacteristicProperty> _properties = ch.getProperties();
        for (final CharacteristicProperty cp : _properties) {
          Property _property = cp.getProperty();
          boolean _equals = property.equals(_property);
          if (_equals) {
            return ch;
          }
        }
      }
    }
    return null;
  }
}
