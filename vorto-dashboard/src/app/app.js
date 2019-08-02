import React, { Component } from "react";
import { HashRouter, Route, Switch } from "react-router-dom";
import { Provider } from "react-redux";
import { store, persistor } from "../store";
import Actions from "../actions"
import { PersistGate } from "redux-persist/integration/react"
import request from "request-promise-native"

/* import io from 'socket.io-client'; */

import indexRoutes from "../routes/index.jsx";

const PORT = process.env.REACT_APP_PORT || 8080;
const DEVICE_REFRESH_MS = process.env.REACT_APP_DEVICE_REFRESH_MS || 5000;
const SHOW_SIMULATOR = process.env.REACT_APP_SHOW_SIMULATOR || false;

const deviceReqOpts = {
    url: `http://${window.location.hostname}:${PORT}/api/v1/devices`,
    method: "GET",
    json: true
};

const simReqOpts = {
    url: `http://${window.location.hostname}:${PORT}/api/v1/simulator`,
    method: "GET",
    json: true
};

function pollDevices() {
    request(deviceReqOpts)
        .then(res => {
            const devices = res.data;
            store.dispatch(Actions.updateDevices(devices));
        })
        .catch(err => `Could not poll device data from backend... ${err}`)
}

function pollSimulatorState() {
    request(simReqOpts)
        .then(res => {
            const running = res.running;
            const startTime = res.startTime;
            store.dispatch(Actions.updateSimulator({ running, startTime }));
        })
        .catch(err => `Could not poll simulator data from backend... ${err}`)
}

export class App extends Component {
    componentDidMount() {
        // TODO replace with WS
        this.deviceInterval = setInterval(pollDevices, DEVICE_REFRESH_MS);

        // only set polling for simulator if it is displayed
        if (SHOW_SIMULATOR) {
            this.simulatorInterval = setInterval(pollSimulatorState, 10000);
        }

        // TODO setup store dispatch with new device data over WS

        /* const options = {
            secure: false,
            rememberUpgrade:true,
            rejectUnauthorized: false,
            transportOptions: {
                polling: {
                    extraHeaders: {
                        "Access-Control-Allow-Origin": "http://www.localhost:8080"
                    }
                }   
            }
        }
        
        const socket = io('http://www.localhost:8080', options);

        socket.on('connect', () => {
            console.log("Connected")
        });

        socket.on('event', (data) => {
            console.log("Event", data)
        });

        socket.on('disconnect', () => {
            console.log("Disconnected")
        }); */
    }

    componentWillUnmount() {
        clearInterval(this.deviceInterval);

        if (SHOW_SIMULATOR) {
            clearInterval(this.simulatorInterval);
        }
    }

    render() {
        const routes = indexRoutes.map((prop, key) => {
            return <Route to={prop.path} component={prop.component} key={key} />;
        });

        return (
            <Provider store={store}>
                <PersistGate loading={null} persistor={persistor}>
                    <HashRouter>
                        <Switch>
                            {routes}
                        </Switch>
                    </HashRouter>
                </PersistGate>
            </Provider>
        );
    }
}

export default App;