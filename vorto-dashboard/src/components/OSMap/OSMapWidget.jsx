import React, { Component } from 'react'
import { Map, Marker, TileLayer } from 'react-leaflet'
import log from 'loglevel'
import { CATEGORIES } from "../../util/cardUtils"
log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')




class OSMapWidget extends Component {
  // handle click on marker
  state = {
    selectedStates: []
  }




  
  render() {
    let things = {}
    // on a widget
    if (this.props.devices !== undefined) {
      things = this.props.devices.filter(device => {
        const features = device.features;

        for (var feature in features) {

          const featureObj = features[feature]
          let definition = featureObj.definition

          if (!definition) {
            continue;
          }

          if (Array.isArray(definition)) {
            definition = definition[0]
          }

          if (!CATEGORIES.LOCATION.includes(definition)) {
            continue;
          }

          const { latitude, longitude } = featureObj.properties.status
          if (latitude && longitude) {
            return true

          }
        }
        return false
      });
    }

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


    const geoLocationDevices = things.map((device, index) => {

      // contains location or geolocation?
      if (device.features.location !== undefined || device.features.geolocation !== undefined ) {
        const deviceLocStatus = (device.features.location !== undefined) ?
          device.features.location.properties.status : device.features.geolocation.properties.status.geoposition

        position = [deviceLocStatus.latitude, deviceLocStatus.longitude]


        const deviceId = device.thingId

        
        return (
          <Marker position={position}
            value={deviceId}
            key={index} >
          </Marker>
        )
      }
      return null

    })

    if (position.length !== 0) {
      return (
        <Map className='map-wrapper' center={position} zoom={14}>
          <TileLayer
            url='http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png'
          />
          {geoLocationDevices}
        </Map>
      )
    }

    return null

  }
}



export default OSMapWidget
