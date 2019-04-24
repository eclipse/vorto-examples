import React from "react";
import { connect } from "react-redux";

import OSMap from "../../components/OSMap/OSMap";
import Actions from "../../actions"
import { checkDeviceForQuery } from "../../util"
import { CATEGORIES } from "../../util/cardUtils"

function hasLocationFeature(device) {
  return Object.keys(device.features)
    .map(feature => device.features[feature].definition)
    .filter(definitions => {
      for (const definition of definitions) {
        if (CATEGORIES.LOCATION.includes(definition)) {
          return true
        }
      }
      return false
    })
    .length > 0
}

const mapStateToProps = state => {
  return {
    devices: state.devices.devices
      .filter(hasLocationFeature)
      .filter(device => checkDeviceForQuery(device, state.search))
  };
};

function mapDispatchToProps(dispatch) {
  return {
    selectDevice: device => dispatch(Actions.selectDevice(device))
  };
}

const ConnectedMaps = ({ devices, selectDevice }) => {
  return (
    <div className="fillHWLocate">
      <OSMap
        devices={devices}
        displayTooltip={true}
        setSelectedDevice={(device) => selectDevice(device)}
      />
    </div>
  );
}

const Maps = connect(mapStateToProps, mapDispatchToProps)(ConnectedMaps)

export default Maps;