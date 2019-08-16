#Vorto Dashboard - Behind the Scenes

## What is Vorto Dashboard?
Vorto Dashboard was developed to help the Vorto community members or interested parties to quickly visualize their IoT devices (real or mocked) and rapidly prototype some use-cases, e.g. asset tracking with dashboard. 

### Asset Tracking
In this use-case we are going to track a truck picking up goods from the city port and delivering them to our warehouse. For this purpose we would be using the Bosch TRACI tag which has the sensors required for the job.

You can try out the solution yourself at [Vorto Dashboard](http://vorto-dashboard.eu-central-1.elasticbeanstalk.com). If you are curious to know how it works, read on to know more. 

## Ok, I am interested, how does it work?
Before we dive-in and uncover the details let's look at the architecture diagram.

<img src="./images/Vorto-Dashboard-Architecture.svg" width="80%"/>

### Step 1: 

- The [Bosch TRACI](https://www.bosch-mobility-solutions.com/en/products-and-services/mobility-services/asset-tracing-solution/) tracking device is fixed on the truck and provisioned in the [Bosch IoT Suite](https://www.bosch-iot-suite.com/). It has sensors for geolocation, temperature, magnetic field and shock monitoring. 

### Step 2:

- The device connects to the [Bosch IoT Hub](https://www.bosch-iot-suite.com/service/hub/) via LoRa (Low Power Wide Area Network). Hub provides a plethora of connectivity options such as HTTP, MQTT, AMQP, LoRaWAN, CoAP etc.
- Hub then relays all incoming traffic to the Vorto Middleware.

### Step 3:

- The Vorto Middleware listens for messages from Hub, it figures out which device does the message belong to and transforms the binary data into Vorto compliant human readable JSON format (using [Vorto Mappings]((https://github.com/eclipse/vorto/blob/development/docs/tutorials/create_mapping_pipeline.md)) for conversion).
- The converted data is then sent to Bosch IoT Things using a message queue.

### Step 4:

- The [Bosch IoT Things](https://www.bosch-iot-suite.com/service/things/) is a digital-twin store, it stores the last known state of our TRACI device (geolocation, temperature etc.). 

### Step 5:

- Vorto Dashboard periodically polls for registered devices and their last known state from Bosch IoT Things (every 5 secs, configurable).
- The received location co-ordinates and other sensor data are then plotted on a map and other charts respectively.

## I would like to know more, how do I get in touch?

Are you looking to build a solution using Vorto Middleware, get in touch with us at [vorto-support@bosch.com](mailto:vorto-support@bosch.com). We help enterprises by hosting production grade infrastructure to transform and normalize your IoT data at scale.

If you would like to host it yourself we provide consulting for your IoT solutions from our team of experienced IoT consultants.


