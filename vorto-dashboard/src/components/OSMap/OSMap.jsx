import React, { Component } from 'react'
import { Map, Marker, Popup, TileLayer } from 'react-leaflet'
import log from 'loglevel'
import DeviceTooltip from '../DeviceTooltip/DeviceTooltip'
import { store } from '../../store'
import { getThingsInTopology, getTextAfterColon } from '../../util'
import { CATEGORIES } from "../../util/cardUtils"



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

    var things = (store.getState().devices.devices) ? store.getState().devices.devices : {}

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
          let definition = featureObj.definition
          if (definition[0]) {
            if (CATEGORIES.LOCATION.includes(definition[0])) {
              const latitude = featureObj.properties.status.latitude
              const longitude = featureObj.properties.status.longitude

              if (latitude, longitude !== null) {

                position = [parseFloat(latitude), parseFloat(longitude)]
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
                  <Marker position={position}
                    value={deviceId}
                    key={index} >
                    {popUp}
                  </Marker>
                )
              }
            }
          }
        }
      }

    }
    )

    if (position.length !== 0 && positionGroup.length > 0) {
      return (<Map className='map-wrapper'
        bounds={[positionGroup]}
        boundsOptions={{ padding: [150, 150] }}
        maxZoom={15}
      >
        <TileLayer
          url='http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png'
        />
        <div className="device-counter">Located Devices: {positionGroup.length}</div>
        {geoLocationDevices}
      </Map>)
    }

    return null
  }
}



export default OSMap
