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
package org.eclipse.vorto.codegen.kura.templates.cloud.bosch;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.kura.templates.Utils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * @author Alexander Edelmann
 */
@SuppressWarnings("all")
public class ThingClientFactoryTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("ThingClientFactory.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _javaPackageBasePath = Utils.getJavaPackageBasePath(context);
    _builder.append(_javaPackageBasePath, "");
    _builder.append("/cloud/bosch");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _javaPackage = Utils.getJavaPackage(element);
    _builder.append(_javaPackage, "");
    _builder.append(".cloud.bosch;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import java.net.URL;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.slf4j.Logger;");
    _builder.newLine();
    _builder.append("import org.slf4j.LoggerFactory;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.bosch.cr.integration.IntegrationClient;");
    _builder.newLine();
    _builder.append("import com.bosch.cr.integration.client.IntegrationClientImpl;");
    _builder.newLine();
    _builder.append("import com.bosch.cr.integration.client.configuration.AuthenticationConfiguration;");
    _builder.newLine();
    _builder.append("import com.bosch.cr.integration.client.configuration.IntegrationClientConfiguration;");
    _builder.newLine();
    _builder.append("import com.bosch.cr.integration.client.configuration.ProxyConfiguration;");
    _builder.newLine();
    _builder.append("import com.bosch.cr.integration.client.configuration.PublicKeyAuthenticationConfiguration;");
    _builder.newLine();
    _builder.append("import com.bosch.cr.integration.client.configuration.TrustStoreConfiguration;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ThingClientFactory {");
    _builder.newLine();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("// Insert your keystore passwords here");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private static final URL KEYSTORE_LOCATION = ThingClientFactory.class.getResource(\"/secret/CRClient.jks\");");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private static final String KEYSTORE_PASSWORD = \"bosch123\"; ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private static final String ALIAS = \"CR\";   // Please change if necessary");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private static final String ALIAS_PASSWORD = \"bosch123\"; ");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private static final URL TRUSTSTORE_LOCATION = ThingClientFactory.class.getResource(\"/secret/bosch-iot-cloud.jks\");");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private static final String TRUSTSTORE_PASSWORD = \"jks\";");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("// optionally configure a proxy server");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public static final String PROXY_HOST = \"localhost\";");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public static final int PROXY_PORT = 8080;");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private static final Logger LOGGER = LoggerFactory.getLogger(ThingClientFactory.class);");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public static IntegrationClient getThingIntegrationClient(String solutionId,String endpointUrl) {");
    _builder.newLine();
    _builder.append("    \t");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("final String CLIENT_ID = solutionId + \":iotconsole_local\";");
    _builder.newLine();
    _builder.append("    \t");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("// Build an authentication configuration");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("final AuthenticationConfiguration authenticationConfiguration = PublicKeyAuthenticationConfiguration");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".newBuilder().clientId(CLIENT_ID)");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".keyStoreLocation(KEYSTORE_LOCATION)");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".keyStorePassword(KEYSTORE_PASSWORD).alias(ALIAS)");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".aliasPassword(ALIAS_PASSWORD).build();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// Optionally configure a proxy server");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("final ProxyConfiguration proxy = ProxyConfiguration.newBuilder()");
    _builder.newLine();
    _builder.append("              ");
    _builder.append(".proxyHost(PROXY_HOST)");
    _builder.newLine();
    _builder.append("              ");
    _builder.append(".proxyPort(PROXY_PORT)");
    _builder.newLine();
    _builder.append("              ");
    _builder.append(".build();");
    _builder.newLine();
    _builder.append("         ");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("//Configure a truststore that contains trusted certificates");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("final TrustStoreConfiguration trustStore = TrustStoreConfiguration");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".newBuilder().location(TRUSTSTORE_LOCATION)");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".password(TRUSTSTORE_PASSWORD).build();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("* Provide required configuration (authentication configuration and CR");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("* URI), optional proxy configuration can be added when needed");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("final IntegrationClientConfiguration integrationClientConfiguration = IntegrationClientConfiguration");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".newBuilder()");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".authenticationConfiguration(authenticationConfiguration)");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".centralRegistryEndpointUrl(endpointUrl)");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".trustStoreConfiguration(trustStore)");
    _builder.newLine();
    _builder.append("//                .proxyConfiguration(proxy)");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".build();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("LOGGER.info(\"Creating CR Integration Client for ClientID: {}\", CLIENT_ID);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// Create a new integration client object to start interacting service");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return IntegrationClientImpl");
    _builder.newLine();
    _builder.append("                ");
    _builder.append(".newInstance(integrationClientConfiguration);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder.toString();
  }
}
