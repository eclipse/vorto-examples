const request = require("request-promise-native");
const AuthToken = require("./authenticate");

const getReqOpts = (accessToken) => ({
  url: "https://things.eu-1.bosch-iot-suite.com/api/2/search/things",
  method: "GET",
  headers: {
    "accept": "application/json",
    "Authorization": `Bearer ${accessToken}`
  },
  json: true
})

const authToken = new AuthToken()
function getUpdatedDevices() {
  return new Promise((resolve, reject) => {
    authToken
      .getToken()
      .then(token => {
        /* console.log(token) */

        // request all things the user has created and have a policy
        request(getReqOpts(token))
          .then(res => {
            const devices = res.items
            /* console.log(JSON.stringify(devices, null, 2)) */
            console.log(`=> Successfully pulled ${devices.length} things.`)
            resolve(devices)
          })
      })
      .catch(err => reject(`Could not get token - ${err}`))
  })
}

module.exports = {
  getUpdatedDevices
}