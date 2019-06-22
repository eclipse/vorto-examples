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
package org.eclipse.vorto.plugins.importer.example.lwm2m;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class LwM2MImporterTest {

  @Test
  public void testValidation() {

    final String filename = "3328_1_1.xml";
    
    InputStream lwm2mXml = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);    
    
    LwM2MImporter importer = new LwM2MImporter();
    
    ValidationReport report = importer.validate(lwm2mXml);
    assertTrue(report.isValid());
    assertNotNull(report.getMessage());
  }
  
  @Test
  public void testConversion()throws Exception {
    final String filename = "3328_1_1.xml";
    
    InputStream lwm2mXml = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);    
    
    LwM2MImporter importer = new LwM2MImporter();
    
    byte[] convertedContent = importer.convert(lwm2mXml);
    
    ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(convertedContent));
    ZipEntry entry = null;
    
    Map<String,byte[]> content = new HashMap<>();
    while ((entry = zis.getNextEntry()) != null) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      IOUtils.copy(zis, baos);
      content.put(entry.getName(),baos.toByteArray());
    }
    
    assertEquals(2,content.keySet().size());
    assertTrue(content.keySet().contains("Power.fbmodel"));
    assertTrue(content.keySet().contains("Power.mapping"));
    
    System.out.println(new String(content.get("Power.fbmodel")));
    System.out.println(new String(content.get("Power.mapping")));
  }
}
