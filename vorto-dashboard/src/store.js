import { createStore } from "redux";

import Reducers from "./reducers"
import Actions from "./actions"

import { persistStore, persistReducer } from 'redux-persist'
import storage from 'redux-persist/lib/storage' // defaults to localStorage for web and AsyncStorage for react-native

const persistConfig = {
    key: 'root',
    storage,
}

const persistedReducer = persistReducer(persistConfig, Reducers)

export const store = createStore(persistedReducer);
export const persistor = persistStore(store)

store.dispatch(Actions.selectDevice({}))
store.dispatch(Actions.updateDevices([]))
store.dispatch(Actions.updateSearch(""))

console.log(store.getState())

/* State
{
    // TODO update to only thingId instead of full qualified object
    selectedDevice: {
        ...
    },
    devices: {
        lastUpdated: "...",
        devices: [...],
        lastState: [... state of last upadte],
    },
    search: {
        searching: true/false,
        query: "..."
    },
}
*/