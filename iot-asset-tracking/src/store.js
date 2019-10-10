import { createStore } from 'redux'

import Reducers from './reducers'
import Actions from './actions'

import { persistStore, persistReducer } from 'redux-persist'
// defaults to localStorage for web and AsyncStorage for react-native
import storage from 'redux-persist/lib/storage'

const persistConfig = {
  key: 'root',
  storage: storage
}

const persistedReducer = persistReducer(persistConfig, Reducers)



export const store = createStore(persistedReducer)

export const persistor = persistStore(store)


store.dispatch(Actions.selectDevice({}))
store.dispatch(Actions.updateDevices([]))
store.dispatch(Actions.changingValues([]))
store.dispatch(Actions.updateSearch(''))
store.dispatch(Actions.updateSimulator({}))

/* State
{
    // TODO update to only thingId instead of full qualified object
    selectedDevice: {
        ...
    },
    assets: {
        lastUpdated: "...",
        devices: [...],
        lastState: [... state of last upadte],
    },
    search: {
        searching: true/false,
        query: "..."
    }
}
*/
