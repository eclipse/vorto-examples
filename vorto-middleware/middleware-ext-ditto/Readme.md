# Eclipse Vorto Ditto Plugin

Publishes Eclipse Ditto protocol message, that contains harmonized Vorto payload, to an AMQP server. 

The AMQP payload contains the Vorto normalized data point, compliant to a Vorto Function Block.

## Configuration

The following environment variables are required by this plugin to run:

* **amqp.url** _AMQP 1.0 Broker url, for the plugin to publish normalized device payload to_
* **amqp.topic.ditto**  _AMQP 1.0 topic name_
* **amqp.username**  _AMQP 1.0 Broker username for authentication_
* **amqp.password**  _AMQP 1.0 Broker password for authentication_