const path = require('path');
const express = require('express');

const app = express();
const server = require('http').createServer(app);
const io = require('socket.io')(server);

const { getUpdatedDevices } = require("./things");

// TODO make port used from config file
const PORT = 8080;

// Cross origin fix
const allowCrossDomain = (req, res, next) => {
  res.header('Access-Control-Allow-Origin', "*");
  res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
  res.header('Access-Control-Allow-Headers', 'Content-Type');
  next();
}

app.use(allowCrossDomain);
app.use(express.static(path.join(__dirname, 'build')));

app.get('/', function (req, res) {
  res.sendFile(path.join(__dirname, 'build', 'index.html'));
  res.end()
});

app.get('/devices', (req, res) => {
  getUpdatedDevices()
    .then(devices => {
      res.send({ status: "OK", data: devices })
      res.end()
    })
    .catch(err => {
      res.send({ status: "FAILED", data: err })
      res.end()
    })
})

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
