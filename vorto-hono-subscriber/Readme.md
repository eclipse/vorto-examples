# Getting started

In this getting started, we are going to configure and run a Eclipse Hono subscribing app that maps incoming arbitrary AWS IoT Button device data (as JSON) to normalized/semantic payload according to Vorto Information Model. 

If you would like to make it work for your Information Model and mapping, follow the instructions in the appendix.

## Pre-requisite

* Bosch IoT Suite Account
* Subscription to Bosch IoT Hub
* Maven installed
* Java 1.8+ installed

## Steps to take 

### 1. Configure and Run the Payload Mapper Hono Subscription Client

2. Modify application.yml and configure your tenantId of your Bosch IoT Hub subscription
3. Modify qpid.properties and adjust the connectionfactory.localhost URL to your Bosch IoT Hub configuration
4. Run the app as a Spring Boot application

### 2. Register a device in Bosch IoT Suite

You can easily register a test device ID in the Bosch IoT Hub using the Swagger API https://apidocs.bosch-iot-suite.com

1. Register device with a device ID, e.g. '4711'

		{
			"enabled": true,
			"device-id": "4711"
		}
	
2. Add credentials for the device

		{
		  "device-id": "4711",
		  "type": "hashed-password",
		  "auth-id": "4711",
		  "enabled": true,
		  "secrets": [
		    {
		      "password": "secret"
		    }
		  ]
		}

### 3. Send arbitrary telemetry data to Bosch IoT Hub

Using the device ID and credentials from step 2. , you can now send telemetry data of the device to Bosch IoT Hub:

Example AWS IoT Button json payload: 

	{
	  "clickType": "DOUBLE",
	  "batteryVoltage" : "2323mV"
	} 

Verify the mapped normalized output in the console. 

## Appendix

If you want to make it work for your own Information Model and Payload Mapping Spec, follow these steps

### Pre-requisite

- Github ID

### Steps to take

1. Create an Information Model via the Repository Cloud Editor
2. Create a Payload Mapping Specification for your Information Model
3. Test the mapping specification in the provided test window of the mapping editor
4. Save and Download the mapping specification as json and store it into the payload mapping app under src/main/resources/specs
5. Modify the application.yml and point the modelID to your Information Model ID



