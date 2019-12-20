# Eclipse Vorto AMQP Plugin

Publishes a Vorto normalized device message as JSON to an AMQP server. 

The AMQP payload contains the Vorto normalized data point, compliant to a Vorto Function Block

The AMQP header contains the following meta fields

* **tag** _function block property name as from the Information Model, e.g. indoorTemperature_
* **modelId** _Function Block Model ID, e.g. org.eclipse.vorto:Temperature:1.0.0_
* **deviceId** _Device ID which sent the messages_


## Configuration

The following environment variables are required by this plugin to run:

* **amqp.url** _AMQP 1.0 Broker url, for the plugin to publish normalized device payload to_
* **amqp.topic.vorto**  _AMQP 1.0 topic name_
* **amqp.username**  _AMQP 1.0 Broker username for authentication_
* **amqp.password**  _AMQP 1.0 Broker password for authentication_

