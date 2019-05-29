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

> **Note** that we are installing it as a global dependency which will allow you to call `vorto-dashboard` from your command line.

You need to provide a configuration file to the vorto-dashboard call.
```elm
vorto-dashboard config.json
```

This has to be in the format of:
```js
// config.json template
{
  "client_id": "<YOUR_CLIENT_ID>",
  "client_secret": "<YOUR_CLIENT_SECRET",
  "scope": "<YOUR_SCOPE>",
  "intervalMS": 10000
}
```

<br />

#### Setup Things
When integrating devices, pleace consult the following resources:
- [Vorto Dashboard Tutorial](https://github.com/eclipse/vorto/blob/master/docs/tutorials/create_webapp_dashboard.md)
- [Vorto Video Walkthrough](https://www.youtube.com/watch?v=ZuZsNWSUvPY&list=UU9_Bk9247GgJ3k9O7yxctFg)

---

<br />

## Developing the Vorto Dashboard
If you want to extend and modify the source code of the Vorto Dashboard project you need to work on a local setup of the project.

#### Installation
```elm
cd vorto_dashboard
npm install

# start the react dashboard
npm start

# start the "backend"
node index.js <PATH_TO_CONFIG.JSON>
```

<br />

#### Extending the Dashboard with custom Cards
If you want to implement custom cards for your own function blocks, [**this short guide**](https://github.com/eclipse/vorto-examples/blob/master/vorto-dashboard/extending.md) will serve as a starting point to do so.

<br />

#### Publishing a new release
Once changes have been merged into `master` and a new release tag has been created, an automated travis-ci job will be triggered that publishes the new version to [NPM](https://www.npmjs.com/).

Triggering a new release is done by pushing a new tag to master. 
```elm
# updating the version of the dashboard npm package (in package.json)
npm version x.y.z 

# tag new version
git tag x.y.z

# push the tag
git push --tags
```
