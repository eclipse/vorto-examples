import React from 'react'
import Actions from '../../actions'
import { connect } from 'react-redux'
import MapCard from '../../components/MapCard/MapCard'
import { getThingsInTopology, countDevicesInTopoloy } from '../../util'



const { store } = require('../../store')


const mapStateToProps = () => {
    return {
        selectedDevice: store.getState().selectedDevice,
    }
}

function mapDispatchToProps(dispatch) {
    return {
        selectDevice: selectedDevice => dispatch(Actions.selectDevice(selectedDevice))
    }
}


const MainView = () => {
    
   var device = store.getState().selectedDevice

   const topology = (store.getState().topologyState) ? store.getState().topologyState : {}
   var selectedDevice = (device.thingId) ? device.thingId : ''


   const numDevices = countDevicesInTopoloy(getThingsInTopology(topology, selectedDevice), store.getState().devices.devices)

   const numDevicesString = (numDevices > 1) ? String(numDevices + " Devices") : String(numDevices + " Device")

    if (device.thingId) {
        return (
            <div className='content'>
                <div className="content-header"><span>{device.attributes.thingName}</span>
                <span className="device-count">Contains {numDevicesString}</span>
                </div>
                <MapCard />
            </div>
        )
    }
    return (
        <div className='content'>
                <div className="content-header"><span>No device selected</span></div>
            </div>
    )
    
}

const Main = connect(mapStateToProps, mapDispatchToProps)(MainView)

export default Main
