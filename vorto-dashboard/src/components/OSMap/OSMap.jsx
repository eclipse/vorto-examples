import React, { Component } from 'react'
import { Map, Marker, Popup, TileLayer } from 'react-leaflet'
import log from 'loglevel'
import DeviceTooltip from '../DeviceTooltip/DeviceTooltip'
import { store } from '../../store'
import { getThingsInTopology } from '../../util'


log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')


class OSMap extends Component {
  // handle click on marker
  handleMarkerClick = deviceId => e => {
    log.debug("marker clicked", { deviceId })
  };

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

      // contains location or geolocation?
      if (this.isInTopology(device.thingId, thingsInTopology) &&
        (device.features.location || device.features.geolocation)) {

        const deviceLocStatus = (device.features.location !== undefined) ?
          device.features.location.properties.status : device.features.geolocation.properties.status.geoposition
        if(deviceLocStatus){
        if(deviceLocStatus.latitude && deviceLocStatus.longitude){
          position = [parseFloat(deviceLocStatus.latitude), parseFloat(deviceLocStatus.longitude) ]
        }
      }
        const deviceId = device.thingId
        positionGroup.push(position)
        if(positionGroup.length === 1){
          //if only one entry add another bound close to the one
          positionGroup.push([positionGroup[0][0]+ 0.005 , positionGroup[0][1]+ 0.00001 ])
        }

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
            key={index} >
            {popUp}
          </Marker>
        )
      }
    
    })
   

    if (position.length !== 0 && positionGroup.length > 0) {
      return (<Map className='map-wrapper'
        bounds={[positionGroup]}
        boundsOptions={{padding: [150, 150]}}
        maxZoom={15}
        >
       <TileLayer
            url='http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png'
          />
        <div className="device-counter">Located Devices: {positionGroup.length - 1}</div>
        {geoLocationDevices}
      </Map>)
    }
    
    return null
  }
}



export default OSMap
