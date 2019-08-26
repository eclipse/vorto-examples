const request = require('request-promise-native')
const log = require('loglevel')
log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'error')

const PORT = process.env.REACT_APP_PORT || 8080

const thingsReqOpts = (filterString) => ({
  url: `http://${window.location.hostname}:${PORT}/api/v1/devices?filterString=${filterString}`,
  method: 'GET',
  json: true
})

function pollThings (filterString) {
  return new Promise((resolve, reject) => {
    request(thingsReqOpts(filterString))
      .then(res => {
        log.debug(res)
        resolve(res.data)
      })
      .catch(err => reject(err))
  })
}

export {
  pollThings
}
