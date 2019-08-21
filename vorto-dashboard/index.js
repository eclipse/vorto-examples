#!/usr/bin/env node

const path = require('path')
const express = require('express')
const bodyParser = require('body-parser')
const spawn = require('child_process').spawn
const exec = require('child_process').exec
const log = require('loglevel')
log.setLevel(process.env.LOG_LEVEL || 'error')

const app = express()
const server = require('http').createServer(app)
// const io = require('socket.io')(server);

const { getUpdatedDevices } = require('./things')

// get PORT and set for Frontend to get device data from
const PORT = process.env.PORT || 8080
const TRACI_SIM_PATH = process.env.TRACI_SIM_PATH || path.join(__dirname, 'Simulators/TraciMock_Hub')
const PMSM_SIM_PATH = process.env.PMSM_SIM_PATH || path.join(__dirname, '/Simulators/PMSMotorMock_Hub')

// Cross origin fix
const allowCrossDomain = (req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*')
  res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE')
  res.header('Access-Control-Allow-Headers', 'Content-Type')
  next()
}

const apiRouter = express.Router()

app.use(allowCrossDomain)
app.use(express.static(path.join(__dirname, 'build')))
app.use(bodyParser.urlencoded({ extended: true }))
app.use(bodyParser.json())

app.use('/api/v1', apiRouter)

// global endpoint that serves the React app
app.get('/', function (req, res) {
  res.sendFile(path.join(__dirname, 'build', 'index.html'))
  res.end()
})

// API for devices and simulator
apiRouter.get('/devices', (req, res) => {
  getUpdatedDevices()
    .then(devices => {
      res.send({ status: 'OK', data: devices })
      res.end()
    })
    .catch(err => {
      res.send({ status: 'FAILED', data: err })
      res.end()
    })
})

function checkSimulatorsRunning () {
  return new Promise((resolve, reject) => {
    exec("kill -0 $(ps aux | grep -E '[p]ython PMSMotorSimulator.py|TraciTagSimulator.py' | grep -v grep | awk '{print $2}')",
      { shell: '/bin/bash' },
      (e) => {
        if (e instanceof Error) {
          reject(e)
        } else {
          resolve()
        }
      })
  })
}

function delay (t, v) {
  return new Promise((resolve, reject) => {
    setTimeout(resolve.bind(null, v), t)
  })
}

let simulatorStartTime = ''
apiRouter.get('/simulator', (req, res) => {
  checkSimulatorsRunning()
    .then(() => {
      res.send({ running: true, startTime: simulatorStartTime })
      res.end()
    })
    .catch(err => {
      log.debug(`Simulator not running ${err}`)
      res.send({ running: false, startTime: '' })
      res.end()
    })
})

apiRouter.post('/simulator', (req, res) => {
  log.info('Starting Simulator Process...')
  simulatorStartTime = new Date()

  const pmsmProcess = spawn('python', ['PMSMotorSimulator.py'], {
    cwd: PMSM_SIM_PATH
  })

  const traciProcess = spawn('python', ['TraciTagSimulator.py'], {
    cwd: TRACI_SIM_PATH
  })

  // wait 3 sec until processes are started
  delay(3000)
    .then(checkSimulatorsRunning)
    .then(() => {
      log.info('Simulator successfully started')
      res.send({ started: true, error: null })
      res.end()
    })
    .catch(err => {
      log.error(`Couldn't start the simulator... ${err}`)

      res.send({ started: false, error: `Couldn't start the simulator... ${err}` })
    })

  pmsmProcess.on('error', (err) => {
    log.error(`Couldn't start the simulator... ${err}`)

    res.send({ started: false, error: `Couldn't start the simulator... ${err}` })
  })

  traciProcess.on('error', (err) => {
    log.error(`Couldn't start the simulator... ${err}`)

    res.send({ started: false, error: `Couldn't start the simulator... ${err}` })
  })

  pmsmProcess.stderr.on('data', (data) => {
    log.error(`pmsm stderr: ${data}`)
  })

  traciProcess.stderr.on('data', (data) => {
    log.error(`traci stderr: ${data}`)
  })

  pmsmProcess.on('close', (code) => {
    if (code !== 0) {
      log.info(`pmsm process exited with code ${code}`)
    }
    pmsmProcess.stdin.end()
  })

  traciProcess.on('close', (code) => {
    if (code !== 0) {
      log.info(`traci process exited with code ${code}`)
    }
    traciProcess.stdin.end()
  })
})

/* io.on('connection', function (socket) {
 setInterval(() => {
    getUpdatedDevices()
        .then(devices => {
            log.debug(devices)
        })
        .catch(err => log.error(`Could not fetch things... - ${err}`))
}, config.things.intervalMS)

  socket.emit('event', { hello: 'world' });
  socket.on('my other event', function (data) {
    log.debug(data);
  });
}); */

server.listen(PORT, () => log.info(`App running on port ${PORT}`))
