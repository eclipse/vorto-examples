const fs = require('fs')
const request = require('request-promise-native')
const AuthToken = require('./AuthToken')
const log = require('loglevel')
log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')

const imgUrls = {}

function getImgUrl (device) {
  const thingDefinition = device.attributes.definition
  const savedImgUrl = imgUrls[thingDefinition]
  const imgFolder = `${__dirname}/../../public/images`

  if (!thingDefinition) {
    return Promise.resolve('https://www.eclipse.org/vorto/images/vorto.png')
  }

  if (savedImgUrl) {
    return Promise.resolve(savedImgUrl)
  }

  log.debug(imgFolder)
  return new Promise((resolve, reject) => {
    fs.readdir(imgFolder, (err, files) => {
      if (err) {
        log.debug(`Could not read image directory, getting image from repository... ${err}`)

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
      } else {
        log.debug(`Trying to find thing image from local assets... ${imgFolder}`)
        const filePath = files.find(file => file.includes(thingDefinition))
        if (filePath) {
          imgUrls[thingDefinition] = `images/${filePath}`
          resolve(`images/${filePath}`)
        }
      }
    })
  })
}

// TODO refactor to use specific things search once implemented
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
function pollThings () {
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
                reject(err)
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

function filterThings (filterString) {
  filterString = filterString.toLowerCase()

  return new Promise((resolve, reject) => {
    pollThings()
      .then(things => {
        const filteredThings = things.filter(thing => {
          if (!filterString) {
            return true
          }

          const hasFeature = Object.keys(thing.features).some(featureName => {
            const feature = thing.features[featureName]
            return feature.definition
              .map(definition => definition.toLowerCase())
              .some(definition => definition.includes(filterString))
          })
          return hasFeature
        })

        log.debug(`${filteredThings.length} things left after filtering with ${filterString}`)
        resolve(filteredThings)
      })
      .catch(err => reject(`Could not get Things... ${err}`))
  })
}

// remove filterThings and add filterQuery parameter to pollThings
module.exports = {
  pollThings: filterThings
}
