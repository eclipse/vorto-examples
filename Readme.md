# Vorto Code Generator Examples

Vorto Code Generators convert Vorto Information Models into platform-specific source code that integrates a device with the IoT Backend.
For more information about Eclipse Vorto, check out the [Eclipse Vorto Web Site](https://www.eclipse.org/vorto)

 - [AWS IoT Generator](org.eclipse.vorto.codegen.aws/Readme.md)
 - [Eclipse Californium Generator](org.eclipse.vorto.codegen.coap/Readme.md)
 - [iOS Generator](org.eclipse.vorto.codegen.ios/Readme.md)
 - [Javabean Generator](org.eclipse.vorto.codegen.javabean/Readme.md)
 - [LaTeX Documentation Generator](org.eclipse.vorto.codegen.latex/Readme.md)
 - [Eclipse Leshan Generator](org.eclipse.vorto.codegen.lwm2m/Readme.md)
 - [Markdown Documentation Generator](org.eclipse.vorto.codegen.markdown/Readme.md)
 - [Eclipse Paho Generator](org.eclipse.vorto.codegen.mqtt/Readme.md)
 - [PTC ThingWorx Generator](org.eclipse.vorto.codegen.thingworx/Readme.md)
 - [Device Web Dashboard Generator](org.eclipse.vorto.codegen.webui/Readme.md)
 - [AngularJS Web UI Generator](org.eclipse.vorto.codegen.webdevice/Readme.md)
 - [Eclipse Kura Generator](org.eclipse.vorto.codegen.kura/Readme.md)
 - [Google Protobuf Generator](org.eclipse.vorto.codegen.protobuf/Readme.md)
 - [Samsung ARTIK Generator](org.eclipse.vorto.codegen.artik/Readme.md)

## Quickstart
Eager to try out the example generators? Head over to the [repository quickstart section](https://github.com/eclipse/vorto/tree/development/repository/repository-web/#Quickstart)
There is a short tutorial on spinning up Docker conatiners that run the whole package.

## Docker

This Docker image expects a bind mount of the docker folder into `/gen/config/` to read the configuration.
The configuration is provided using the [`config.json`](https://github.com/eclipse/vorto/tree/development/repository/repository-web/docker/config.json) file, it contains the configuration for the repoisitory, [the generators](../repository-generators/Readme.md) and [the 3rd party generators](https://github.com/eclipse/vorto-examples).
The proxy settings are read and then the complete file is passed to [Spring Boot](https://spring.io/projects/spring-boot) using the `SPRING_APPLICATION_JSON` env var.
