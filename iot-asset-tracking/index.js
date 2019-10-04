#!/usr/bin/env node

const path = require('path')
const express = require('express')
const bodyParser = require('body-parser')
const log = require('loglevel')
log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'error')

const { pollThings } = require('./things/DataPoller')

const app = express()
const server = require('http').createServer(app)

const PORT = process.env.PORT || 8080

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
  const filterString = req.query.filterString !== 'undefined' ? req.query.filterString : ''



  log.debug(`Backend queried with filterString... ${filterString}`)

  pollThings(filterString)
    .then(devices => {
      res.send({ status: 'OK', data: devices })
      res.end()
    })
    .catch(err => {
      res.send({ status: 'FAILED', data: err })
      res.end()
    })
})

server.listen(PORT, () => log.info(`App running on port ${PORT}`))
