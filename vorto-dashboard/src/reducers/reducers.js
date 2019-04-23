export const selectDevice = (state = {}, action) => {
  if (action.type === "SELECT_DEVICE") {
    return action.device
  }

  // return the updated version of the currently selected
  // TODO think about only saving the idea to avoid updating the selectedDevice
  // object on update of all device (reference instead of copy of device)
  if (action.type === "UPDATE_DEVICES") {
    const selectedId = state.attributes ? state.attributes.deviceId : "";

    if (selectedId) {
      const updatedSelected = action.devices.filter(device => {
        if (device.attributes.deviceId === selectedId) {
          return true
        }

        return false
      })

      return updatedSelected[0];
    }
  }

  return state;
}

export const updateDevices = (state = { devices: [], lastUpdated: "", lastState: [] }, action) => {
  if (action.type === "UPDATE_DEVICES") {
    return {
      lastState: [...state.devices],
      devices: [...action.devices],
      lastUpdated: new Date().toString()
    }
  }

  return state;
}

export const updateSearch = (state = { searching: false, query: "" }, action) => {
  if (action.type === "UPDATE_SEARCH") {
    if (!action.query) {
      return {
        searching: false,
        query: ""
      }
    }

    return {
      searching: true,
      query: action.query
    }
  }

  return state;
}

export default {
  selectDevice,
  updateDevices,
  updateSearch
}