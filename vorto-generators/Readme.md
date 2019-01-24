# Vorto Code Generator Plugins

Here is a list of code generator Vorto plugins that have been implemented by the Vorto community for you to try and use as a reference to get inspired and implement your own code generator or simply use it in your project:

 - [AWS IoT Generator](org.eclipse.vorto.codegen.aws/Readme.md)
 - [Eclipse Californium Generator](org.eclipse.vorto.codegen.coap/Readme.md)
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

Eager to try out these generators? Head over to the [repository quickstart section](https://github.com/eclipse/vorto/tree/development/repository/repository-server/#Quickstart)
There is a short tutorial on spinning up Docker containers that run the whole package.

### Docker

This Docker image expects a bind mount of the docker folder into `/gen/config/` to read the configuration.
The configuration is provided using the [`config.json`](https://github.com/eclipse/vorto/tree/development/repository/repository-server/docker/config.json) file, it contains the configuration for the repository, [the generators](../repository-generators/Readme.md) and [the 3rd party generators](https://github.com/eclipse/vorto-examples).
The proxy settings are read and then the complete file is passed to [Spring Boot](https://spring.io/projects/spring-boot) using the `SPRING_APPLICATION_JSON` env var.

## Creating a new generator

The [Eclipse Vorto project](https://www.eclipse.org/vorto) provides a plugin SDK, that allows you to easily develop, build and run your own Vorto Code Generator. 

---> [Read more](https://github.com/eclipse/vorto/blob/development/plugin-sdk/Readme.md)