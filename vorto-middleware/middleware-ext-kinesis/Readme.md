# AWS Kinesis Data Stream Plugin

Publishes Vorto normalized device messages as an AWS Kinesis Data Stream.

The Kinesis Data stream contains data point records for each mapped Vorto Function Block.

Each records is serialized as JSON. Here is an example of how a record JSON could look like, that is mapped according to a [Temperature Function Block](https://vorto.eclipse.org/#/details/org.eclipse.vorto:Temperature:1.0.0):

```
{
	"payload" : { 
		"value: {
			"currentMeasured" : 30.2
		}
	},
	"modelId" : "org.eclipse.vorto:Temperature:1.0.0",
	"tag" : "indoorTemperature",
	"deviceId" : "4711"
}
```

## Configuration

The following environment variables are required by this plugin to run:

* **kinesis.accessKey** _AWS Access Key credential_
* **kinesis.secretKey**  _AWS Secret Key credential_
* **kinesis.streamName**  _Kinesis Stream Name_