#!/usr/bin/env node

const path = require('path');
const express = require('express');
const bodyParser = require('body-parser');

const app = express();
const server = require('http').createServer(app);
// const io = require('socket.io')(server);

const { getUpdatedDevices } = require("./things");

// get PORT and set for Frontend to get device data from
const PORT = process.env.PORT || 8080;

// Cross origin fix
const allowCrossDomain = (req, res, next) => {
  res.header('Access-Control-Allow-Origin', "*");
  res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
  res.header('Access-Control-Allow-Headers', 'Content-Type');
  next();
}

const apiRouter = express.Router();

app.use(allowCrossDomain);
app.use(express.static(path.join(__dirname, 'build')));
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.use('/api/v1', apiRouter);

// global endpoint that serves the React app
app.get('/', function (req, res) {
  res.sendFile(path.join(__dirname, 'build', 'index.html'));
  res.end()
});

// API for devices and simulator
apiRouter.get('/devices', (req, res) => {
  getUpdatedDevices()
    .then(devices => {
      res.send({ status: "OK", data: devices })
      res.end()
    })
    .catch(err => {
      res.send({ status: "FAILED", data: err })
      res.end()
    })
});

apiRouter.get('/simulator', (req, res) => {
  // check if running
  const running = false;

  if (running) {
    const startTime = "test";
    res.send({ running: true, startTime: startTime });
  } else {
    res.send({ running: false, startTime: "" });
  }

  res.end();
});

apiRouter.post('/simulator', (req, res) => {
  // get the new state from the post body  
  const newState = req.body.state;

  res.send({ state: "running" });
  res.end("updated")
});

/* io.on('connection', function (socket) {
 setInterval(() => {
    getUpdatedDevices()
        .then(devices => {
            console.log(devices)
        })
        .catch(err => console.log(`Could not fetch things... - ${err}`))
}, config.things.intervalMS)

  socket.emit('event', { hello: 'world' });
  socket.on('my other event', function (data) {
    console.log(data);
  });
}); */

server.listen(PORT, () => console.log(`App running on port ${PORT}`));
