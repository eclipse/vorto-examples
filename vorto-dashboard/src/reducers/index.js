import { combineReducers } from 'redux';
import {
  selectDevice,
  updateDevices,
  updateSearch,
  updateSimulator
} from "./reducers.js"

export default combineReducers({
  selectedDevice: selectDevice,
  devices: updateDevices,
  search: updateSearch,
  simulator: updateSimulator
})
