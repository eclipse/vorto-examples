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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import org.eclipse.vorto.model.ModelId;
import org.eclipse.vorto.plugins.importer.example.lwm2m.LWM2M.Object;

/**
 * Imports (a bulk of) LwM2M XML definitions to the Vorto Repository by
 * converting them to Vorto Function Blocks and Mapping files
 *
 */
public class LwM2MImporter {

	private static final String NAMESPACE = "com.ipso.smartobjects";
	private static final String VERSION = "1.1.0"; // set globally because object definitions do not
													// contain the version information

	private static final FunctionblockTemplate FB_TEMPLATE = new FunctionblockTemplate();
	private static final MappingTemplate MAPPING_TEMPLATE = new MappingTemplate();

	public ValidationReport validate(InputStream fileInput) {
		try {
			LWM2M lwm2m = parse(fileInput);
			if (lwm2m.getObject().isEmpty()) {
				return ValidationReport.invalid("File does not contain any object definitions.");
			} else {
				return ValidationReport.valid("File is a valid LwM2M description");
			}

		} catch (Exception ex) {
			return ValidationReport.invalid(ex.getMessage());
		}
	}

	private LWM2M parse(InputStream fileInput) throws Exception {
		try {
			JAXBContext jc = JAXBContext.newInstance(LWM2M.class);

			XMLInputFactory inputFactory = XMLInputFactory.newFactory();
			inputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
			inputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
			XMLStreamReader xmlStreamReader = inputFactory.createXMLStreamReader(fileInput);

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			return (LWM2M) unmarshaller.unmarshal(xmlStreamReader);
		} finally {
			fileInput.close();
		}
	}

	public byte[] convert(InputStream fileInput) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(baos);

		try {
			LWM2M lwm2m = parse(fileInput);

			try {
				for (LWM2M.Object obj : lwm2m.getObject()) {

					final ModelId modelId = createModelId(obj);

					ZipEntry fbEntry = new ZipEntry(modelId.getName() + ".fbmodel");
					zip.putNextEntry(fbEntry);
					zip.write(FB_TEMPLATE.create(obj, modelId).getBytes());
					zip.closeEntry();

					ZipEntry mappingEntry = new ZipEntry(modelId.getName() + ".mapping");
					zip.putNextEntry(mappingEntry);
					zip.write(MAPPING_TEMPLATE.create(obj, modelId).getBytes());
					zip.closeEntry();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (zip != null) {
					zip.close();
				}
			}

			return baos.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private ModelId createModelId(Object obj) {
		return new ModelId(parseId(obj.getName()), NAMESPACE, VERSION);
	}

	private String parseId(String name) {
		return name.replace("/", "_").replace(" ", "_").replace("-", "_").replace("\"", "'");
	}

}
