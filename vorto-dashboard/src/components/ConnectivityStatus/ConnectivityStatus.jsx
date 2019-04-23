import React, { Component } from "react";

export class ConnectivityStatus extends Component {
  render() {
    const device = this.props.device
    const deviceConnectivities = Object.keys(device.features)
      .filter(feature => device.features[feature].definition.includes("org.eclipse.vorto:Connectivity:1.0.0"))
    const connectivityFeatures = deviceConnectivities.map(feature => device.features[feature])

    const connected = connectivityFeatures
      .some(connectivity => connectivity.properties.status.status === "Connected")
    const connecting = connectivityFeatures
      .some(connectivity => connectivity.properties.status.status === "Connecting")

    const cloudColor = connected ? "#a2f260" : connecting ? "#f9f963" : "#333333";
    const deviceConnectivityElem = connectivityFeatures.length > 0
      ? <span className="fa fa-cloud connectivityIcon" style={{ color: cloudColor }} />
      : <span />;

    return deviceConnectivityElem;
  }
}

export default ConnectivityStatus;
