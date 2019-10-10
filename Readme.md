# Vorto Examples

This repository contains example code that have been implemented using the [Eclipse Vorto SDK](https://www.eclipse.org/vorto).

## Vorto Web Dashboard

Node.js/React based web dashboard, that displays device data, which are modelled with Vorto. [Read more](vorto-dashboard/README.md)
<br/>
<br/>
<img src="vorto-dashboard/assets/deviceDashboard.png" width="49%"/> <img src="vorto-dashboard/assets/locatePage.png" width="49%"/>

## Eclipse Vorto Semantic Middleware

The Eclipse Vorto Semantic Middleware is a small and light-weight microservice that is able to convert any device telemetry payload (binary, json, xml, ...) to semantic Vorto compliant data structures and exposes this "harmonized API" via AMQP. Other micro services can easily consume the datav to further process it, e.g. for running analytics on the data. 

The middleware provides an API, where additional forward-handlers can be plugged in, e.g. forward the "harmonized data" to AWS Timeseries etc. 

[Read more](vorto-middleware/Readme.md)

<img src="vorto-middleware/overview.png"/>

## Vorto Generator Plugins

Vorto Generators convert Vorto Models to any platform specific source code. This code can be a simple platform descriptor, device , or backend code. They are stateless in nature and can be easily developed using the [Vorto Plugin SDK](https://github.com/eclipse/vorto/tree/master/plugin-sdk). 

To get started, checkout the [hello world generator](vorto-generators/v2/helloworld-example), that is deployed as a serverless AWS Lambda function.

[Checkout other Example Generators](vorto-generators/Readme.md)

## Vorto Importer Plugins

Vorto Importers convert 3rd party platform descriptions to Vorto models. Just as Generator plugins, they are stateless and can easily be developed using the [Vorto Plugin SDK](https://github.com/eclipse/vorto/tree/master/plugin-sdk). 

To get started, checkout the [LwM2M example](vorto-importers/lwm2m), that converts LwM2M/IPSO XML descriptions to Vorto Function Block - and Mapping models. This example is deployed as a AWS Lambda service.

## Using Vorto Models from your custom DSL

If you would like to use and reference Vorto Models from your own custom DSL, the following tutorial shows you how that is done.
 
[Read tutorial](vorto-dsl-integration/Readme.md)
