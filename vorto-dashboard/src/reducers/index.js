import { combineReducers } from 'redux'
import {
  selectDevice,
  updateDevices,
  changingValues,
  updateTopology,
  countDevices,
  updateSearch,
  updateSimulator
} from './reducers.js'

export default combineReducers({
  selectedDevice: selectDevice,
  devices: updateDevices,
  topologyState: updateTopology,
  totalDevices: countDevices,
  valueChanges: changingValues,
  search: updateSearch,
  simulator: updateSimulator
})
