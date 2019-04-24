import { combineReducers } from 'redux';
import {
  selectDevice,
  updateDevices,
  updateSearch
} from "./reducers.js"

export default combineReducers({
  selectedDevice: selectDevice,
  devices: updateDevices,
  search: updateSearch,
})
