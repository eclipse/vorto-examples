const request = require('request-promise-native')
const AuthToken = require('./authenticate')

const imgUrls = {}

function getImgUrl(device) {
  const savedImgUrl = imgUrls[device.attributes.definition]

  if (!device.attributes.definition) {
    return Promise.resolve('https://www.eclipse.org/vorto/images/vorto.png')
  }

  if (savedImgUrl) {
    return Promise.resolve(savedImgUrl)
  }

  const url = `http://vorto.eclipse.org/rest/models/${device.attributes.definition}/images`
  const reqOpts = {
    url,
    method: 'GET'
  }

  return new Promise((resolve) => request(reqOpts)
    .then(res => {
      imgUrls[device.attributes.definition] = url
      resolve(url)
    })
    .catch(err => {
      console.log(`Could not get device img, using default vorto logo... ${err}`)
      imgUrls[device.attributes.definition] = 'https://www.eclipse.org/vorto/images/vorto.png'
      resolve('https://www.eclipse.org/vorto/images/vorto.png')
    }))
}

const getReqOpts = (accessToken) => ({
  url: 'https://things.eu-1.bosch-iot-suite.com/api/2/search/things',
  method: 'GET',
  headers: {
    accept: 'application/json',
    Authorization: `Bearer ${accessToken}`
  },
  json: true
})

let authToken = new AuthToken()
function getUpdatedDevices() {
  return new Promise((resolve, reject) => {
    authToken
      .getToken()
      .then(token => {
        /* console.log(token) */

        // request all things the user has created and have a policy
        request(getReqOpts(token))
          .then(res => {
            const devices = res.items.map(device => new Promise((resol) => {
              getImgUrl(device)
                .then(imgSrc => resol({ ...device, imgSrc }))
            }))

            Promise.all(devices)
              .then(resDevices => {
                /* console.log(JSON.stringify(devices, null, 2)) */
                console.log(`=> Successfully pulled ${devices.length} things.`)
                resolve(resDevices)
              })
              .catch(err => {
                console.log(`Could not enrich devices with images, dropping this call... ${err}`)
              })
          })
          .catch(err => {
            console.log(`JWT expired, getting new Token ${new Date()}... ${err}`)
            authToken = new AuthToken()
          })
      })
      .catch(err => reject(`Could not get token - ${err}`))
  })
}

module.exports = {
  getUpdatedDevices
}
