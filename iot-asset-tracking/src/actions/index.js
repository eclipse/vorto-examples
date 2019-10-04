export const selectDevice = selectedDevice => ({
  type: 'SELECT_DEVICE',
  selectedDevice
})

export const updateDevices = devices => ({
  type: 'UPDATE_DEVICES',
  devices
})

export const updateSearch = query => ({
  type: 'UPDATE_SEARCH',
  query
})

export const updateSimulator = simulatorState => ({
  type: 'UPDATE_SIMULATOR',
  simulatorState
})

export default {
  selectDevice,
  updateDevices,
  updateSearch,
  updateSimulator
}
