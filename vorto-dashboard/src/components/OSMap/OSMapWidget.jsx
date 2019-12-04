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
    let device = this.props.device

    const features = device[0].features
    const deviceId = device.thingId


    //check only for all items in the selected topology 
    let position = []

      for (var feature in features) {
        const featureObj = features[feature]
        let definition = featureObj.definition

        if (definition[0]) {
          if (CATEGORIES.LOCATION.includes(definition[0])) {
            const latitude = featureObj.properties.status.latitude
            const longitude = featureObj.properties.status.longitude
            if (latitude, longitude !== null) {
             position = [parseFloat(latitude), parseFloat(longitude)]
            }
          }
        } 
      }

      const locationMarker = 
      <Marker position={position}
      value={deviceId}>
      </Marker>


    if (position.length !== 0) {
      return (
        <Map className='map-wrapper' center={position} zoom={14}>
          <TileLayer
            url='http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png'
          />
          {locationMarker}
        </Map>
      )
    }else{
      return (
        <Map className='map-wrapper' center={[0,0]} zoom={14}>
          <span className='invalid-location-note'>invalid geolocation</span>
        </Map>
      )
    }
  }
}

export default OSMapWidget
