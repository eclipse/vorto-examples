/**
 * Copyright (c) 2015-2016 Bosch Software Innovations GmbH and others.
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
 */
package org.eclipse.vorto.examples.generators;

import org.eclipse.vorto.codegen.artik.ArtikGenerator;
import org.eclipse.vorto.codegen.aws.AWSGenerator;
import org.eclipse.vorto.codegen.ble.alpwise.AlpwiseBtStackGenerator;
import org.eclipse.vorto.codegen.coap.CoAPGenerator;
import org.eclipse.vorto.codegen.javabean.JavabeanGenerator;
import org.eclipse.vorto.codegen.kura.KuraGenerator;
import org.eclipse.vorto.codegen.latex.LatexGenerator;
import org.eclipse.vorto.codegen.lwm2m.LWM2MGenerator;
import org.eclipse.vorto.codegen.markdown.MarkdownGenerator;
import org.eclipse.vorto.codegen.protobuf.ProtobufGenerator;
import org.eclipse.vorto.codegen.spi.config.AbstractGeneratorConfiguration;
import org.eclipse.vorto.codegen.spi.model.Generator;
import org.eclipse.vorto.codegen.thingworx.ThingWorxCodeGenerator;
import org.eclipse.vorto.codegen.webdevice.WebDeviceGenerator;
import org.eclipse.vorto.codegen.webui.WebUIGenerator;
import org.springframework.stereotype.Component;

@Component
public class GeneratorConfiguration extends AbstractGeneratorConfiguration {

	@Override
	protected void doSetup() {
		addGenerator(Generator.create("/generators/aws.properties", AWSGenerator.class));
		addGenerator(Generator.create("/generators/artik.properties", ArtikGenerator.class));
		addGenerator(Generator.create("/generators/alpwiseBt.properties", AlpwiseBtStackGenerator.class));
		addGenerator(Generator.create("/generators/coap.properties", CoAPGenerator.class));
		addGenerator(Generator.create("/generators/javabean.properties", JavabeanGenerator.class));
		addGenerator(Generator.create("/generators/kura.properties", KuraGenerator.class));
		addGenerator(Generator.create("/generators/latex.properties", LatexGenerator.class));
		addGenerator(Generator.create("/generators/lwm2m.properties", LWM2MGenerator.class));
		addGenerator(Generator.create("/generators/markdown.properties", MarkdownGenerator.class));
		addGenerator(Generator.create("/generators/protobuf.properties", ProtobufGenerator.class));
		addGenerator(Generator.create("/generators/thingworx.properties", ThingWorxCodeGenerator.class));
		addGenerator(Generator.create("/generators/webdevice.properties", WebDeviceGenerator.class));
		addGenerator(Generator.create("/generators/webui.properties", WebUIGenerator.class));
	}
	
	
}
