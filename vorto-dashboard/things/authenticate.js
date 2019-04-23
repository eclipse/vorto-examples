const request = require("request-promise-native");
const config = require("../config.json")

class AuthToken {
  /*Intitally get the token and assign its value to the object*/
  constructor() {
    const tokenPromise = this.getInitialToken()
    this.updateToken(tokenPromise)
  }

  getInitialToken() {
    const tokenForm = {
      "grant_type": "client_credentials",
      "client_id": config.bosch_iot_suite.client_id,
      "client_secret": config.bosch_iot_suite.client_secret,
      "scope:service": config.bosch_iot_suite.scope
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
    const tokenForm = {
      "grant_type": "refresh_token",
      "client_id": config.bosch_iot_suite.client_id,
      "client_secret": config.bosch_iot_suite.client_secret,
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
        }.bind(this), this.expires_in * 950)
        // don't wait the full amount of sec until invalidate (has to be < 1000)
      })
      .catch(err => console.log(`Could not get token with given credentials. - ${err}`))
  }
}

module.exports = AuthToken