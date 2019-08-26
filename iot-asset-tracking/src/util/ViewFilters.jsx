const log = require('loglevel')
log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')
const { store } = require('../store')

// add viewFilters such as only display workers, vehicles, or sensors etc.
function applyFilters (things) {
  const viewFilters = store.viewFilters
  log.debug(`ViewFilters are... ${viewFilters}`)

  return Promise.resolve(things)
}

module.exports = {
  applyFilters
}
