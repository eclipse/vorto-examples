# Vorto Dashboard

#### Installation
```elm
cd vorto_dashboard
npm install

# start the react dashboard
npm start

# start the "backend"
node index.js
```

#### Creating a Topology

In order to create a topology that can be displayed in the Sidebar, we need to follow a defined structure.

In addition to all your devices, you have to create at least one meta-thing that defines the root of the topology.
You can use the basic structre of a thing and define the attributes like this:
```json
"attributes": {
    "thingName": "Company",
    "definition": "",
    "topology": {
        "definition": "org.eclipse.vorto:Topology:1.0.0",
        "references": [
            {
                "thingId": "com.bosch.sales.assettracking:ZoneA"
            },
            {
                "thingId": "com.bosch.sales.assettracking:ZoneB"
            }
        ],
        "referenceBy": []
    }
},
```
> **Note** That the `"definition": "org.eclipse.vorto:Topology:1.0.0"` is the most important part since it defines this element as the root.
<br />

In order to add containers that hold entities, simply create another meta-model that holds similar content to the root (e.g. empyt features etc.).
Make sure to define the `references` and `referencedBy` fields in the attributes in order to tell the dashboard the structure.

```json
"attributes": {
    "thingName": "Zone",
    "definition": "",
    "topology": {
        "definition": "org.eclipse.vorto:Referencable:1.0.0",
        "references": [
            {
                "thingId": "com.bosch.sales.assettracking:WaterSensor1"
            },
            {
                "thingId": "com.bosch.sales.assettracking:WaterSensor3"
            }
        ],
        "referenceBy": [
            {
                "thingId": "com.bosch.sales.assettracking:Company"
            }
        ]
    }
},
```

> **Note** The nesting can be repeated several times to create more complex structures.
<br />

By adding the `topology` attribute to regular Vorto Information Model things, you can also group things together visually.

```json
"attributes": {
    "thingName": "ACMEWaterSensor",
    "definition": "vorto.private.somesh:ACMEWaterSensor:1.0.0",
    "topology": {
        "definition": "org.eclipse.vorto:Referencable:1.0.0",
        "references": [],
        "referenceBy": [
            {
                "thingId": "com.bosch.sales.assettracking:ZoneA"
            }
        ]
    }
},
```

#### Postman Script

Create a new local `ENV` setup that contains the following information.

- `SERVICE_INSTANCE_ID`
- `TOKEN`
- `TENANT_ID`
- `PW_TYPE` (simply use `hashed-password`)



# Eclipse Vorto Device Dashboard
React and NodeJS based Dashboard that helps quickly prototype IoT Device integrations with the Bosch IoT Suite. 
Integrated IoT devices can be displayed and data visualized.

The Dashboard uses the [Eclipse Vorto](https://www.eclipse.org/vorto) project to unify the definition of device capabilities and provides out-of-the-box visualization widgets that display sensor data in a visually appealing way.

<img src="https://github.com/eclipse/vorto-examples/raw/master/vorto-dashboard/assets/deviceDashboard.png" width="49%"/> <img src="https://github.com/eclipse/vorto-examples/raw/master/vorto-dashboard/assets/locatePage.png" width="49%"/>

## Installation
Installing the dashboard is as easy as doing

```elm
npm install -g vorto-dashboard
```

> **Note** that we are installing it as a global dependency which will allow you to call `vorto-dashboard` from your command line

You can provide your OAuth2 credentials through environment variables.   
The three environment variables you have to provide are:
```bash
BOSCH_CLIENT_ID
BOSCH_CLIENT_SECRET
BOSCH_SCOPE
REACT_APP_DEVICE_REFRESH_MS
```
> The `REACT_APP_DEVICE_REFRESH_MS` variable defines the interval in which the dashboard is updating the state of the listed devices


In addition to that you can set the port the dashboard should run on by using the `PORT` environment variable.

The `LOG_LEVEL` env var can be used to change the logging level. By default this is `error`.

---

The Simulator has two additional env variables.

**TIME_TO_LIVE** defines the amount of seconds the simulator should run in total.

**TIME_INTERVAL_TELEMETRY** defines the amount of seconds between the sending of data points.

<br />

#### Setup Things
When integrating devices, pleace consult the following resources:
- [Vorto Dashboard Tutorial](https://github.com/eclipse/vorto/blob/master/docs/tutorials/create_webapp_dashboard.md)
- [Vorto Video Walkthrough](https://www.youtube.com/watch?v=ZuZsNWSUvPY&list=UU9_Bk9247GgJ3k9O7yxctFg)
---

<br />

## Developing the Vorto Dashboard
If you want to extend and modify the source code of the Vorto Dashboard project you need to work on a local setup of the project.

<br />

#### Extending the Dashboard with custom Cards
If you want to implement custom cards for your own function blocks, [**this short guide**](https://github.com/eclipse/vorto-examples/blob/master/vorto-dashboard/extending.md) will serve as a starting point to do so.

<br />
