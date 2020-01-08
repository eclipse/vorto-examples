import React, {Component} from 'react'
import {Map, Marker, Popup, TileLayer} from 'react-leaflet'
import log from 'loglevel'
import DeviceTooltip from '../DeviceTooltip/DeviceTooltip'
import {store} from '../../store'
import {getThingsInTopology, getTextAfterColon} from '../../util'
import {CATEGORIES} from "../../util/cardUtils"


log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')


class OSMap extends Component {
    state = {
        selectedStates: []
    }

    isInTopology(thingId, thingsInTopology) {
        let isIn = false
        thingsInTopology.forEach(thingIdInTopology => {
            if (thingId.includes(thingIdInTopology)) {
                isIn = true
            }
        })
        return isIn
    }


    render() {

        var things = store.getState().devices.devices ? store.getState().devices.devices : {}


        /// show empty map
        if (things.length === 0 || !things[0].features) {

            return (
                <Map className='map-wrapper' center={[1.347, 103.841]} zoom={11}>
                    <TileLayer
                        url='http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png'
                    />
                </Map>
            )
        }

        let position = []

        var positionGroup = []
        const topology = (store.getState().topologyState) ? store.getState().topologyState : {}
        var selectedDevice = (store.getState().selectedDevice.thingId) ? store.getState().selectedDevice.thingId : ''
        const thingsInTopology = getThingsInTopology(topology, selectedDevice)

        const geoLocationDevices = things.map((device, index) => {

                const features = device.features
                const deviceId = device.thingId

                //check only for all items in the selected topology
                if (thingsInTopology.includes(getTextAfterColon(deviceId))) {

                    for (var feature in features) {
                        const featureObj = features[feature]
                        if (featureObj) {
                            let definition = featureObj.definition ? featureObj.definition : []
                            if (definition[0]) {
                                if (CATEGORIES.LOCATION.includes(definition[0])) {

                                    const latitude = featureObj.properties.status.latitude ? featureObj.properties.status.latitude : 1.347
                                    const longitude = featureObj.properties.status.longitude ? featureObj.properties.status.longitude : 103.841

                                    if (latitude && longitude) {
                                        position = []
                                        position.latitude = parseFloat(latitude)
                                        position.longitude = parseFloat(longitude)
                                        positionGroup.push(position)

                                        const popUp = this.props.displayTooltip
                                            ? (<Popup>
                                                <DeviceTooltip
                                                    device={device}
                                                    latitude={latitude}
                                                    longitude={longitude}
                                                />
                                            </Popup>)
                                            : <div></div>

                                        return (
                                            <Marker position={[
                                                position.latitude,
                                                position.longitude]}
                                                    value={deviceId}
                                                    key={index}>
                                                {popUp}
                                            </Marker>
                                        )
                                    } else {
                                        return null
                                    }
                                }
                            }
                        }

                    }
                }

            }
        )
        const averageLocation = averageGeolocation(positionGroup)

        if (positionGroup.length !== 0) {

            return (<Map className='map-wrapper'
                         center={[averageLocation.latitude, averageLocation.longitude]}
                         zoom={12}
                         fitBounds={[averageLocation]}
                         padding={10}

            >
                <TileLayer
                    url='http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png'
                />
                <div className="device-counter">Located Devices: {positionGroup.length}</div>
                {geoLocationDevices}
            </Map>)
        } else {
            return (<Map  className='map-wrapper' center={[1.347, 103.841]} zoom={11}
            >
                <TileLayer
                    url='http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png'
                />
                <div className="device-counter">No Devices with geolocation found</div>

            </Map>)
        }
    }
}

function averageGeolocation(coords) {
    if (coords.length === 1) {
        return coords[0];
    }

    let x = 0.0;
    let y = 0.0;
    let z = 0.0;

    for (let coord of coords) {
        let latitude = coord.latitude * Math.PI / 180;
        let longitude = coord.longitude * Math.PI / 180;

        x += Math.cos(latitude) * Math.cos(longitude);
        y += Math.cos(latitude) * Math.sin(longitude);
        z += Math.sin(latitude);
    }

    let total = coords.length;

    x = x / total;
    y = y / total;
    z = z / total;

    let centralLongitude = Math.atan2(y, x);
    let centralSquareRoot = Math.sqrt(x * x + y * y);
    let centralLatitude = Math.atan2(z, centralSquareRoot);

    return {
        latitude: centralLatitude * 180 / Math.PI,
        longitude: centralLongitude * 180 / Math.PI
    };
}


export default OSMap
