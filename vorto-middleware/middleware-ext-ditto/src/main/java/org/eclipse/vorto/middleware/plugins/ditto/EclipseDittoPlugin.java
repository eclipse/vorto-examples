/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional information regarding copyright
 * ownership.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which is available at https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.middleware.plugins.ditto;

import org.eclipse.vorto.mapping.targetplatform.ditto.TwinPayloadFactory;
import org.eclipse.vorto.middleware.deserializer.MimeType;
import org.eclipse.vorto.middleware.monitoring.MonitorMessage;
import org.eclipse.vorto.middleware.monitoring.MonitorMessage.Severity;
import org.eclipse.vorto.middleware.plugins.ExecutionContext;
import org.eclipse.vorto.middleware.plugins.amqp.EclipseVortoAMQPPlugin;
import org.eclipse.vorto.model.runtime.FunctionblockValue;
import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.springframework.jms.JmsException;

import com.google.gson.JsonObject;

public class EclipseDittoPlugin extends EclipseVortoAMQPPlugin {

	@Override
	public void doExecute(InfomodelValue infomodelValue, ExecutionContext context) {
		if (context.getMimeType() == MimeType.ECLIPSE_DITTO) {
			context.getLogger().monitor(MonitorMessage.outboundMessage(context.getCorrelationId(),
					context.getDeviceId(), (String) context.getRawPayload(), Severity.INFO, getId()));
			try {
				jmsTemplate.convertAndSend(topic, (String) context.getRawPayload());
			} catch (JmsException exception) {
				throw new ExecutionProblem("Could not send data to AMQP", exception);
			}
		} else {
			for (String fbProperty : infomodelValue.getProperties().keySet()) {
				FunctionblockValue value = infomodelValue.get(fbProperty);
				JsonObject updateCommand = TwinPayloadFactory.toDittoProtocol(value, fbProperty,
						DittoUtils.getDittoNamespaceFromDeviceId(context.getDeviceId()),
						DittoUtils.getDittoSuffixFromDeviceId(context.getDeviceId()));
				String updateCommandJson = gson.toJson(updateCommand);
				context.getLogger().monitor(MonitorMessage.outboundMessage(context.getCorrelationId(),
						context.getDeviceId(), updateCommandJson, Severity.INFO, getId()));
				try {
					jmsTemplate.convertAndSend(topic, updateCommandJson);
				} catch (JmsException exception) {
					throw new ExecutionProblem("Could not send data to AMQP", exception);
				}
			}
		}

	}

	@Override
	public String getId() {
		return "AMQP_DITTO";
	}

	@Override
	public String getName() {
		return "Eclipse Ditto - Digital Twin API";
	}

	@Override
	public String getDescription() {
		return "Publishes harmonized device telemetry data to Eclipse Ditto Service.";
	}

	@Override
	public String getImageUrl() {
		return "https://www.eclipse.org/ditto/images/ditto.svg";
	}
}
