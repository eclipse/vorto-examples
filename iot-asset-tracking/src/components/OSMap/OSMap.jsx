import React, { Component } from 'react'
import { Map, Marker, Popup, TileLayer } from 'react-leaflet'
import log from 'loglevel'
import DeviceTooltip from '../DeviceTooltip/DeviceTooltip'
import { CATEGORIES } from "../../util/cardUtils"
log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')

// const geoFeatureTypes = {
//   geolocation: 'geolocation',
//   location: 'location',
//   none: 'none'
// }



class OSMap extends Component {
  // handle click on marker
  handleMarkerClick = deviceId => e => {
    log.debug("marker clicked", { deviceId })

  };

  render() {
    var things = {}

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

    if (this.props.things !== undefined) {
      things = this.props.things
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



// contains features?
    const featuresOfFirst = (things[0].features !== undefined) ? things[0].features : {}
// contains location or geolocation?
    const deviceLocStatus = (featuresOfFirst.location !== undefined) ?
      featuresOfFirst.location.properties.status : featuresOfFirst.geolocation.properties.status.geoposition

    const position = [deviceLocStatus.latitude, deviceLocStatus.longitude]

    const mappedDevices = things.map((device, index) => {
      
// contains location or geolocation?
      const deviceLocStatus = (device.features.location !== undefined) ?
        device.features.location.properties.status : device.features.geolocation.properties.status.geoposition

      const position = [deviceLocStatus.latitude, deviceLocStatus.longitude]

      const deviceId = device.thingId

      const popUp = this.props.displayTooltip
        ? (<Popup>
          <DeviceTooltip
            device={device}
          />
        </Popup>)
        : <div></div>

      return (
        <Marker position={position}
          value={deviceId}
          onClick={this.handleMarkerClick(deviceId)}
          // icon={regularIcon}
          key={index} >
          {popUp}
        </Marker>
      )
    })



    return (
      <Map className='map-wrapper' center={position} zoom={12}>
        <TileLayer
          url='http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png'
        />
        {mappedDevices}
      </Map>
    )
  }
}

export default OSMap
