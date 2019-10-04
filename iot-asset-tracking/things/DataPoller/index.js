const request = require('request-promise-native')
const AuthToken = require('./AuthToken')
const log = require('loglevel')
log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')

const imgUrls = {}

function getImgUrl (device) {
  if (!device.attributes) {
    return Promise.resolve('https://www.eclipse.org/vorto/images/vorto.png')
  }
  
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
        
            const devices = res.items
              .filter(device => device.attributes)
              .map(device => new Promise((resol) => {
                // enrich with img source to be displayed
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


// TODO: Remove this 
// Get rid of all the things that don't have topology attribute
function removeNonTopology (things) {
  return Promise.resolve(things.filter(thing => thing.attributes.topology))
}

function filterThings (filterString) {
  filterString = filterString.toLowerCase()

  return new Promise((resolve, reject) => {
    pollThings()
      // .then(things => removeNonTopology(things))
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

        log.info(`Found ${filteredThings.length} things for filter ${filterString}`)
        resolve(filteredThings)
      })
      .catch(err => reject(`Could not get Things... ${err}`))
  })
}

// remove filterThings and add filterQuery parameter to pollThings
module.exports = {
  pollThings: filterThings
}
