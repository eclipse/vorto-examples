import React, { Component } from "react";
import { HashRouter, Route, Switch } from "react-router-dom";
import { Provider } from "react-redux";
import { store, persistor } from "../store";
import Actions from "../actions"
import { PersistGate } from "redux-persist/integration/react"
import request from "request-promise-native"

/* import io from 'socket.io-client'; */

import indexRoutes from "../routes/index.jsx";
import { getImgUrl } from "../util";

// TODO make port used from config file
const reqOpts = {
    url: `http://localhost:${8080}/devices`,
    method: "GET",
    json: true
}

function pollDevices() {
    request(reqOpts)
        .then(res => {
            const devices = res.data.map(device => ({
                ...device,
                imgSrc: getImgUrl(device)
            }))

            store.dispatch(Actions.updateDevices(devices))
        })
        .catch(err => `Could not poll data from backend... ${err}`)
}

export class App extends Component {
    componentDidMount() {
        // TODO replace with WS
        this.interval = setInterval(pollDevices, 5000);

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
        clearInterval(this.interval);
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