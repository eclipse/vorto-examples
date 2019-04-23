# Vorto Examples

This repository contains example code that have been implemented using the [Eclipse Vorto SDK](https://www.eclipse.org/vorto).

## Vorto Web Dashboard

Node.js/React based web dashboard, that displays device data, which are modelled with Vorto. [Read more](vorto-dashboard/Readme.md)

<img src="vorto-dashboard/assets/deviceDashboard.png" width="49%"/> <img src="vorto-dashboard/assets/locatePage.png" width="49%"/>

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
