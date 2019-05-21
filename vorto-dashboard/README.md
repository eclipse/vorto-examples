# Vorto Device Dashboard
React and NodeJS based Dashboard that helps quickly prototype IoT Device integrations with the Bosch IoT Suite. 
Integrated IoT devices can be displayed and data visualized.


<img src="./assets/deviceDashboard.png" width="49%"/> <img src="./assets/locatePage.png" width="49%"/>

## Installation
```bash
cd vorto_dashboard
npm install

# start the react dashboard
npm start

# start the "backend"
node index.js <PATH_TO_CONFIG.JSON>
```

<br />

## Setup Things
When integrating devices, pleace consult the following resources:
- [Vorto Tutorials](https://github.com/eclipse/vorto/tree/development/docs/tutorials)
- [Vorto Video Walkthrough](https://www.youtube.com/watch?v=ZuZsNWSUvPY&list=UU9_Bk9247GgJ3k9O7yxctFg)

<br />

## Extending the Dashboard with custom Cards
If you want to implement custom cards for your own function blocks, [**this short guide**](./extending.md) will serve as a starting point to do so.

<br />

## Publishing a new release
Once changes have been merged into `master` and a new release tag has been created, an automated travis-ci job will be triggered that publishes the new version to [NPM](https://www.npmjs.com/).

Triggering a new release is done by pushing a new tag to master. 
```bash
# updating the version of the dashboard npm package (in package.json)
npm version x.y.z 

# tag new version
git tag x.y.z

# push the tag
git push --tags
```
