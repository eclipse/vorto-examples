import React, { Component } from 'react'
import { Map, Marker, Popup, TileLayer } from 'react-leaflet'
import log from 'loglevel'
import DeviceTooltip from '../DeviceTooltip/DeviceTooltip'

log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')




class OSMap extends Component {
// handle click on marker
  handleMarkerClick = deviceId => e => {
    log.debug("marker clicked", {deviceId})    

};

  render () {
    const things = this.props.things

    if (things.length === 0 || !things[0].features) {
      return (
        <Map className='map-wrapper' center={[1.347, 103.841]} zoom={11}>
          <TileLayer
            url='http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png'
          />
        </Map>
      )
    }

    const deviceLocStatus = things[0].features.location.properties.status
    const position = [deviceLocStatus.latitude, deviceLocStatus.longitude]

    const mappedDevices = things.map((device, index) => {
      const deviceLocStatus = device.features.location.properties.status
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
