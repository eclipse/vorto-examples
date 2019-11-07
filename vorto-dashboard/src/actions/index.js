export const selectDevice = selectedDevice => ({
  type: 'SELECT_DEVICE',
  selectedDevice
})

export const updateDevices = devices => ({
  type: 'UPDATE_DEVICES',
  devices
})

export const updateTopology = (topologyState) => ({
  type: 'UPDATE_TOPOLOGY',
  topologyState
})

export const countDevices = (totalDevices) => ({
  type: 'COUNT_DEVICES',
  totalDevices
})

export const changingValues = deviceIds => ({
  type: 'VALUES_CHANGING',
  deviceIds
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
  changingValues,
  updateTopology,
  countDevices,
  updateSearch,
  updateSimulator
}
