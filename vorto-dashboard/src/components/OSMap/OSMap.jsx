import React, { Component } from "react";
import { Map, Marker, Popup, TileLayer } from 'react-leaflet'
import { Redirect } from 'react-router-dom'

import DeviceTooltip from "../DeviceTooltip/DeviceTooltip"

import { CATEGORIES } from "../../util/cardUtils"

class OSMap extends Component {
  state = {
    redirect: false
  }

  setRedirect = (device) => {
    this.setState({
      redirect: true,
    })

    this.props.setSelectedDevice(device)
  }

  renderRedirect = () => {
    if (this.state.redirect) {
      return <Redirect to='/devicedashboard' />
    }
  }

  render() {
    const validDevices = this.props.devices.filter(device => {
      const features = device.features;

      for (const feature in features) {
        const featureObj = features[feature]
        if (!CATEGORIES.LOCATION.includes(featureObj.definition[0])) {
          continue;
        }

        const { latitude, longitude } = featureObj.properties.status
        if (latitude && longitude) {
          return true
        }
      }

      return false
    });

    if (validDevices.length === 0) {
      return (
        <Map className="mapWrapper" center={[1.347, 103.841]} zoom={11}>
          <TileLayer
            url="http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png"
          />
        </Map>
      );
    }

    // TODO generalize to work with different feature names
    const deviceLocStatus = validDevices[0].features.location.properties.status
    const position = [deviceLocStatus.latitude, deviceLocStatus.longitude]

    const mappedDevices = validDevices.map(device => {
      const deviceLocStatus = device.features.location.properties.status
      const position = [deviceLocStatus.latitude, deviceLocStatus.longitude]

      const popUp = this.props.displayTooltip ?
        (<Popup>
          <DeviceTooltip
            device={device}
            redirect={() => this.setRedirect(device)}
          />
        </Popup>)
        : <div />;

      // TODO fix redirect and history to allow browser back
      return (
        <Marker position={position}>
          {popUp}
        </Marker>
      )
    });

    return (
      <Map className="mapWrapper" center={position} zoom={12}>
        <TileLayer
          url="http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png"
        />
        {mappedDevices}
      </Map>
    );
  }
}

export default OSMap;