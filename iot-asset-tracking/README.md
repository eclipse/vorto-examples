# Asset Tracking Cockpit

#### Installation
```elm
npm install
npm start
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
