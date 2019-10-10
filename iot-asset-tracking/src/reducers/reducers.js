export const selectDevice = (state = {}, action) => {
switch(action.type){
  case 'SELECT_DEVICE':  
    if(action.selectedDevice !== undefined){
      return action.selectedDevice
    }
    break;
    default:
    return state
}
}

export const updateDevices = (state = { devices: [], lastUpdated: '', lastState: [] }, action) => {
  if (action.type === 'UPDATE_DEVICES') {
    return {
      lastState: [...state.devices],
      devices: [...action.devices],
      lastUpdated: new Date().toString()
    }
  }
  return state
}

export const changingValues = (state = { deviceIds: [] }, action) => {
  if (action.type === 'VALUES_CHANGING') {
    return {
      changingValues: [...action.deviceIds]
    }
  }
  return state
}


export const updateSearch = (state = { searching: false, query: '' }, action) => {
  if (action.type === 'UPDATE_SEARCH') {
    if (!action.query) {
      return {
        searching: false,
        query: ''
      }
    }

    return {
      searching: true,
      query: action.query
    }
  }

  return state
}

export const updateSimulator = (state = { running: false, startTime: '' }, action) => {
  if (action.type === 'UPDATE_SIMULATOR') {
    return {
      running: action.simulatorState.running,
      startTime: action.simulatorState.startTime
    }

  }

  return state
}

export default {
  selectDevice,
  updateDevices,
  updateSearch,
  changingValues,
  updateSimulator
}
