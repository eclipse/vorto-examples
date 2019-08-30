import React from 'react'
import { connect } from 'react-redux'

import Actions from '../../actions'
import DeviceDashboard from '../../components/DeviceDashboard/DeviceDashboard'

const mapStateToProps = state => {
  return {
    devices: state.devices.devices
  }
}

function mapDispatchToProps (dispatch) {
  return {
    selectDevice: device => dispatch(Actions.selectDevice(device))
  }
}

const ConnectedMaps = ({ devices, selectDevice }) => {
  return <DeviceDashboard deviceId={'com.bosch.sales.assettracking:WaterSensor2'} />
}

const Maps = connect(mapStateToProps, mapDispatchToProps)(ConnectedMaps)

export default Maps
