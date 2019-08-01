# Getting started with Vorto Normalizer

The Vorto Normalizer is a nice little micro service that receives any device telemetry data from [Eclipse Hono](https://www.eclipse.org/hono) and normalizes it according to [Vorto Information Models](https://github.com/eclipse/vorto/blob/master/docs/vortolang-1.0.md). A payload handler API lets you easily provide integrators to forward the normalized for various IoT platforms, such as Digital Twin services. 

![](overview.png)

In this example, we are going to use the [Bosch IoT Hub](https://www.bosch-iot-suite.com/service/hub/) which uses the Eclipse Hono service. 

## Pre-requisite

* Bosch IoT Suite Account
* [Subscription](https://www.bosch-iot-suite.com/service/hub/) to Bosch IoT Hub (free plan)
* Maven installed
* Java 1.8+ installed
* [Mosquitto](http://www.steves-internet-guide.com/mosquitto_pub-sub-clients/) to send data via MQTT

## Steps to take 

### 1. Configure and Run the Payload Mapper Hono Subscription Client

2. Modify application.yml and configure your tenantId of your Bosch IoT Hub subscription
3. Modify qpid.properties and adjust the connectionfactory.localhost URL to your Bosch IoT Hub configuration
4. Run the app as a Spring Boot application with `mvn clean install springBoot:run`

### 2. Register a device in Bosch IoT Suite

You can easily register a test device ID in the Bosch IoT Hub using the [Swagger API](https://apidocs.bosch-iot-suite.com)

1. Register device with a device ID, e.g. '4711' as well as vorto model. In this example we use the AWS IoT Button Vorto model:
```js
{
  "enabled": true,
  "device-id": "4711",
  "defaults": {
    "vorto": "devices.aws.button:AWSIoTButton:1.0.0"
  }
}
```

2. Add credentials for the device
```js
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
```

### 3. Send arbitrary telemetry data to Bosch IoT Hub

Using the device ID and credentials from step 2. , you can now send telemetry data of the device to Bosch IoT Hub.

1. Download the Bosch IoT Hub server certificate 
```bash
curl -o iothub.crt https://docs.bosch-iot-hub.com/cert/iothub.crt
```

2. Send the device data via MQTT
```bash
mosquitto_pub -h mqtt.bosch-iot-hub.com -p 8883 -u {auth-id}@{tenant-id} -P {password} -t telemetry -m '{"clickType": "DOUBLE", "batteryVoltage": "2323mV"}' --cafile iothub.crt
```

3. Verify the mapped normalized output in the system console. You should see something like this:
```js
--> Normalized json for device ID 4711
{
  "button": {
    "status": {
      "digital_input_count": 2,
      "digital_input_state": true
    }
  },
  "batteryVoltage": {
    "status": {
      "sensor_units": "mV",
      "sensor_value": 2323.0
    }
  }
}
``` 

## Appendix

### Configure data normalizer for custom Vorto Model

If you want to make it work for your own Information Model and Payload Mapping Spec, follow these steps

#### Pre-requisite

- Github ID

#### Steps to take

1. Create an Information Model via the Repository Cloud Editor
2. Create a Payload Mapping Specification for your Information Model
3. Test the mapping specification in the provided test window of the mapping editor
4. Save and Download the mapping specification as json and store it into the payload mapping app under src/main/resources/specs

### Adding custom handler


If you want to process the normalized data, e.g. forwarding it to InfluxDB or a Digital Twin Service, you would need to implement the `IPayloadHandler` interface

1. Implement the Handler:
```java
import org.eclipse.vorto.example.mapping.handler.Context;
import org.eclipse.vorto.example.mapping.handler.IPayloadHandler;

public class InfluxDBHandler implements IPayloadHandler {

	private ConnectionProperties connectionProps = null;

	public InfluxDBHandler(ConnectionProperties connectionProps) {
		this.connectionProps = connectionProps;			
	}
	@Override
    public void handlePayload(JsonObject normalizedPayload, Context context) {
       // write data to Influx DB
    }
}
```

2. Configure your handler
Add your handler to the `org.eclipse.vorto.example.mapping.config.LocalConfiguration` 
```java
@Bean
public IPayloadHandler influxDBHandler() {
	return new InfluxDBHandler(connectionProperties);
}
```
