const request = require("request-promise-native");
const path = require('path');
const ArgumentParser = require("argparse").ArgumentParser;
const parser = new ArgumentParser({
  addHelp: true,
  description: 'Eclipse Vorto Dashboard'
});

parser.addArgument(
  ["-c", "-C", "--config"],
  {
    help: "Relative or absolute path to config file with OAuth2 Client credentials"
  }
);

const cliArgs = process.argv.slice(2);
let configFilePath

// handle case of user only typing "vorto-dashboard config.json"
if (cliArgs.length > 1 || cliArgs[0].startsWith("-")) {
  const args = parser.parseArgs();
  configFilePath = args.config
} else {
  configFilePath = cliArgs[0]
}

// check path to config file, if absolute, keep it, otherwise use working dir and create abs path
const configPath = path.isAbsolute(configFilePath) ? configFilePath : path.join(process.cwd(), configFilePath);
const configFile = require(configPath);
console.log("Using config.json file from path: ", configPath)

class AuthToken {
  /*Intitally get the token and assign its value to the object*/
  constructor() {
    const tokenPromise = this.getInitialToken()
    this.updateToken(tokenPromise)
  }

  getInitialToken() {
    const tokenForm = {
      "grant_type": "client_credentials",
      "client_id": configFile.client_id,
      "client_secret": configFile.client_secret,
      "scope:service": configFile.scope
    }

    return request(this.getReqOpts(tokenForm))
  }

  /*gets the value of this.token, if it's undefined, waits 1 sec and checks again*/
  getToken() {
    return new Promise(function (res) {
      if (this.token) {
        res(this.token)
        return
      }

      setInterval(function () {
        if (this.token) {
          res(this.token)
          return
        }
      }.bind(this), 1000)
    }.bind(this))
  }

  /*getter for request options with dynamic form content*/
  getReqOpts(form) {
    return {
      url: 'https://access.bosch-iot-suite.com/token',
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      form: form,
      json: true
    }
  }

  /*refreshes the access token by making a refresh token call to the auth provider*/
  refreshToken(refreshToken) {
    console.log("Refreshing token")
    const tokenForm = {
      "grant_type": "refresh_token",
      "client_id": configFile.client_id,
      "client_secret": configFile.client_secret,
      "refresh_token": refreshToken
    }

    const tokenPromise = request(this.getReqOpts(tokenForm))
    this.updateToken(tokenPromise)
  }

  /*updates the value of this.token with the new access token*/
  updateToken(tokenPromise) {
    tokenPromise
      .then(response => {
        this.token = response.access_token
        this.expires_in = response.expires_in
        this.refresh_token = response.refresh_token

        setTimeout(function () {
          this.refreshToken(this.refresh_token)
        }.bind(this), this.expires_in * 300)
        // don't wait the full amount of sec until invalidate every third of the time it will be renewed to ensure 
        // failure tolerance for one renewal 
      })
      .catch(err => console.log(`Could not get token with given credentials. - ${err}`))
  }
}

module.exports = AuthToken