#!/usr/bin/env node

var fs = require("fs");
var path = require("path");
var mime = require("mime");


var http = require("http");
var opn = require("opn");
var url = require('url');
var argv = require("minimist")(process.argv.slice(2));
const getFilePathFromUrl = require('./get-file-path-from-url');


const NO_PATH_FILE_ERROR_MESSAGE =
    "Error: index.html could not be found in the specified path ";

const basePath = argv.path ? path.resolve(argv.path) : process.cwd();
const baseHref = argv.baseHref ? argv.baseHref : '';
const port = getPort(argv.p);

returnDistFile(true);

let server = http.createServer(requestListener);
start();

function start() {
    server.listen(port, function () {
        if (argv.open == true || argv.o) {
            opn("http://localhost:" + port);
        }
        return console.log("Listening on " + port);
    });
}


function requestListener(req, res) {

    var parsedURL = url.parse(req.url, true, false)

    if (argv.cors) {
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Request-Method", "*");
        res.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET");
        res.setHeader(
            "Access-Control-Allow-Headers",
            "authorization, content-type"
        );
        if (req.method === "OPTIONS") {
            res.writeHead(200);
            res.end();
            return;
        }
    }
    const safeFullFileName = getFilePathFromUrl(req.url, basePath, { baseHref });
    fs.stat(safeFullFileName, function (err, stats) {
        var fileBuffer;
        if (!err && stats.isFile()) {
            fileBuffer = fs.readFileSync(safeFullFileName);
            let ct = mime.lookup(safeFullFileName);
            log(`Sending ${safeFullFileName} with Content-Type ${ct}`);
            res.writeHead(200, { "Content-Type": ct });
        } else {
            log("Route %s, replacing with index.html", safeFullFileName);
            fileBuffer = returnDistFile();
            res.writeHead(200, { "Content-Type": "text/html" });
        }
        res.write(fileBuffer);
        res.end();
    });
}

function getPort(portNo) {
    if (portNo) {
        var portNum = parseInt(portNo);
        if (!isNaN(portNum)) {
            return portNum;
        } else {
            throw new Exception("Provided port number is not a number!");
        }
    } else {
        return 8080;
    }
}

function returnDistFile(displayFileMessages = false) {
    var distPath;

    try {
        if (displayFileMessages) {
            log("Serving from path: %s", basePath);
        }
        distPath = path.join(basePath, 'index.html');
        if (displayFileMessages) {
            log("Using default file: %s", distPath);
        }
        return fs.readFileSync(distPath);
    } catch (e) {
        console.warn(NO_PATH_FILE_ERROR_MESSAGE + "%s", basePath);
        process.exit(1);
    }
}

function log() {
    if (!argv.silent) {
        console.log.apply(console, arguments);
    }
}
