const request = require('request-promise-native')
const path = require('path')

const ArgumentParser = require('argparse').ArgumentParser
const parser = new ArgumentParser({
  addHelp: true,
  description: 'Eclipse Vorto Dashboard'
})

const log = require('loglevel')
log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'error')



parser.addArgument(
  ['-c', '-C', '--config'],
  {
    help: 'Relative or absolute path to config file with OAuth2 Client credentials'
  }
)

let clientId
let clientSecret
let scope


try {
  const cliArgs = process.argv.slice(2)
  let configFilePath

  // handle case of user only typing "vorto-dashboard config.json"
  if (cliArgs.length > 1 || cliArgs[0].startsWith('-')) {
    const args = parser.parseArgs()
    configFilePath = args.config
  } else {
    configFilePath = cliArgs[0]
  }

// check path to config file, if absolute, keep it, otherwise use working dir and create abs path
const configPath = path.isAbsolute(configFilePath) ? configFilePath : path.join(process.cwd(), configFilePath)

const configFile = require(configPath)
  log.info('Using config.json file from path: ', configPath)

  clientId = configFile.client_id
  clientSecret = configFile.client_secret
  scope = configFile.scope
} catch (err) {
  log.warn('No config file provided, checking for environment variables...')

  // get environment variables
  const envId = process.env.BOSCH_CLIENT_ID
  const envSecret = process.env.BOSCH_CLIENT_SECRET
  const envScope = process.env.BOSCH_SCOPE


  if (!envId || !envSecret || !envScope) {
    log.error('No credentials given in either config file or environment, stopping dashboard!')
    process.exit(1)
  }



  if (!envId || !envSecret || !envScope) {
    log.error('No credentials given, can not get things')
  }else{
    clientId = envId
    clientSecret = envSecret
    scope = envScope
  }
}


class AuthToken {
  /* Intitally get the token and assign its value to the object */
  constructor () {
    const tokenPromise = this.getInitialToken()
    this.updateToken(tokenPromise)
  }

  getInitialToken () {
    const tokenForm = {
      grant_type: 'client_credentials',
      client_id: clientId,
      client_secret: clientSecret,
      'scope': scope
    }

    return request(this.getReqOpts(tokenForm))
  }

  /* gets the value of this.token, if it's undefined, waits 1 sec and checks again */
  getToken () {
    return new Promise(function (res) {
      if (this.token) {
        res(this.token)
        return
      }

      setInterval(function () {
        if (this.token) {
          res(this.token)
        }
      }.bind(this), 1000)
    }.bind(this))
  }

  /* getter for request options with dynamic form content */
  getReqOpts (form) {
    return {
      url: 'https://access.bosch-iot-suite.com/token',
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      form: form,
      json: true
    }
  }

  /* refreshes the access token by making a refresh token call to the auth provider */
  refreshToken (refreshToken) {
    log.info('Refreshing token')
    const tokenForm = {
      grant_type: 'refresh_token',
      client_id: clientId,
      client_secret: clientSecret,
      refresh_token: refreshToken
    }

    const tokenPromise = request(this.getReqOpts(tokenForm))
    this.updateToken(tokenPromise)
  }

  /* updates the value of this.token with the new access token */
  updateToken (tokenPromise) {
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
      .catch(err => log.error(`Could not get token with given credentials. - ${err}`))
  }
}

module.exports = AuthToken

