import React, { Component } from 'react'
import { HashRouter, Route, Switch } from 'react-router-dom'
import { Provider } from 'react-redux'
import { store, persistor } from '../store'
import { PersistGate } from 'redux-persist/integration/react'

import indexRoutes from '../routes/index.jsx'

export class App extends Component {
  render () {
    const routes = indexRoutes.map((prop, key) => {
      return <Route to={prop.path} component={prop.component} key={key} />
    })

    return (
      <Provider store={store}>
        <PersistGate loading={null} persistor={persistor}>
          <HashRouter>
            <Switch>
              {routes}
            </Switch>
          </HashRouter>
        </PersistGate>
      </Provider>
    )
  }
}

export default App
