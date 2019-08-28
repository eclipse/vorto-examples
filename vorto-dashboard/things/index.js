const request = require('request-promise-native')
const AuthToken = require('./authenticate')
const log = require('loglevel')
log.setLevel(process.env.LOG_LEVEL || 'error')

const imgUrls = {}

function getImgUrl (device) {
  const thingImageUrl = device.attributes['img-url']
  const thingDefinition = device.attributes.definition
  const savedImgUrl = imgUrls[thingDefinition]

  if (thingImageUrl) {
    return Promise.resolve(thingImageUrl)
  }

  if (!thingDefinition) {
    return Promise.resolve('https://www.eclipse.org/vorto/images/vorto.png')
  }

  if (savedImgUrl) {
    return Promise.resolve(savedImgUrl)
  }

  return new Promise((resolve, reject) => {
    const url = `http://vorto.eclipse.org/rest/models/${thingDefinition}/images`
    const reqOpts = {
      url,
      method: 'GET'
    }

    request(reqOpts)
      .then(res => {
        imgUrls[thingDefinition] = url
        resolve(url)
      })
      .catch(err => {
        log.warn(`Could not get device img, using default vorto logo... ${err}`)
        imgUrls[thingDefinition] = 'https://www.eclipse.org/vorto/images/vorto.png'
        resolve('https://www.eclipse.org/vorto/images/vorto.png')
      })
  })
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
function getUpdatedDevices () {
  return new Promise((resolve, reject) => {
    authToken
      .getToken()
      .then(token => {
        // request all things the user has created and have a policy
        request(getReqOpts(token))
          .then(res => {
            const devices = res.items.map(device => new Promise((resol) => {
              getImgUrl(device)
                .then(imgSrc => resol({ ...device, imgSrc }))
            }))

            Promise.all(devices)
              .then(resDevices => {
                log.info(`=> Successfully pulled ${devices.length} things.`)
                resolve(resDevices)
              })
              .catch(err => {
                log.error(`Could not enrich devices with images, dropping this call... ${err}`)
              })
          })
          .catch(err => {
            log.warn(`JWT expired, getting new Token ${new Date()}... ${err}`)
            authToken = new AuthToken()
          })
      })
      .catch(err => reject(`Could not get token - ${err}`))
  })
}

module.exports = {
  getUpdatedDevices
}
