# Vorto Device Dashboard
React and NodeJS based Dashboard that helps quickly prototype IoT Device integrations with the Bosch IoT Suite. 
Integrated IoT devices can be displayed and data visualized.


<img src="./assets/deviceDashboard.png" width="49%"/> <img src="./assets/locatePage.png" width="49%"/>

## Installation
```bash
cd vorto_dashboard
yarn install # please use yarn here to avoid any problems

# start the react dashboard
npm start

# start the "backend"
node index.js
```

<br />

## Setup Things
1. Create BoschID account
1. Subscribe to Suite for Asset Communication (Beta)
1. Set the namespace in the Asset Communication Dashbard. This is needed to create things.
1. Go to profile -> OAuth2 Clients
1. Create new client (Check the Things checkbox)
1. Copy Client ID, Client secret, scope into the config.json file.
1. Create Things
1. For each device, add an Entry at the policies tab with type "suite-auth" and the clientID from your auth client. Grant thing READ access.

> Once you start the dashboard, all your devices should now be listed and by default, their values will be updated every 5 seconds.

<br />

## Extending the Dashboard with custom Cards
If you want to implement custom cards for your own function blocks, [**this short guide**](./extending.md) will serve as a starting point to do so.

<br />

## Creating a new release
Since development on this project is done using a development server with tools like hot-module reloading etc. to allow for a convenient development environment, we have to ... TODO

**1.** Build the project at the current state by executing `npm run build`. This will create/update the content in the `build` folder with the latest state of the project.

**2.** Copy the following elements into the `dist` folder.
- `build` folder
- `things` folder
- `config.json` file
- `index.js` file
- `package.json` file

**3.** Edit the `package.json` file and remove the `devDependencies` attribute. Replace the `scripts` section with
```json
"scripts": {
    "start": "node index.js"
  }
```

**4.** Make sure to remove the OAuth2 Credentials from the `config.json` file

**5.** Select all the files and zip them into a package called `vorto_dashboard.zip`.

**6.** Create a Pull Request to update the version that get's distributed through the Vorto Repository Generators
