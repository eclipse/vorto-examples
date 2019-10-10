import { combineReducers } from 'redux'
import {
  selectDevice,
  updateDevices,
  changingValues,
  updateSearch,
  updateSimulator
} from './reducers.js'

export default combineReducers({
  selectedDevice: selectDevice,
  devices: updateDevices,
  valueChanges: changingValues,
  search: updateSearch,
  simulator: updateSimulator
})
