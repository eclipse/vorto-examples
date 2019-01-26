# Vorto IoT Add-ons

This repository contains [Eclipse Vorto](https://www.eclipse.org/vorto) plugins that have been implemented using the Vorto SDK. 

## Vorto Generator Plugins

[Read more](vorto-generators/Readme.md)

## Vorto Connector for Bosch Security Cameras

The Vorto Connector integrates Bosch Security Cameras with the Bosch IoT Suite. 
The following [Blog Post](https://blog.bosch-si.com/developer/avoid-tight-coupling-of-devices-in-iot-solutions/) gives a general overview about Vorto and this integration. 

In short, the connector processes Vorto Information Models to retrieve meta-data about the cameras on the one hand, as well as uses the [Vorto Mapping Engine](https://github.com/eclipse/vorto/tree/development/mapping-engine) to convert Camera Binary data to Vorto/Ditto compliant data.

[Read tutorial](vorto-connector/Readme.md) to set it up and run it.

## Using Vorto Models from your custom DSL

If you would like to use and reference Vorto Models from your own custom DSL, the following tutorial shows you how that is done.
 
[Read tutorial](vorto-dsl-integration/Readme.md)