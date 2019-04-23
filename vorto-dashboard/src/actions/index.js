export const selectDevice = device => ({
  type: 'SELECT_DEVICE',
  device
})

export const updateDevices = devices => ({
  type: 'UPDATE_DEVICES',
  devices
})

export const updateSearch = query => ({
  type: 'UPDATE_SEARCH',
  query
})

export default {
  selectDevice,
  updateDevices,
  updateSearch
}