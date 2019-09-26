import React from 'react'
import Actions from '../../actions'
import { connect } from 'react-redux'

import AttributesCard from '../Device/Cards/AttributesCard'


const { store } = require('../../store')

const mapStateToProps = state => {
  return {
    devices: state.devices.devices,
    selectedDevice: store.getState().selectedDevice
  }
}



function mapDispatchToProps (dispatch) {
  return {
    selectDevice: device => dispatch(Actions.selectDevice(device))
  }
}



const DeviceView = (props) => {
  return (
    <div className='content'>
      <AttributesCard
              device={props.selectedDevice}
            />
      
     
    </div>
  )
}

const Device = connect(mapStateToProps, mapDispatchToProps)(DeviceView)

export default Device
