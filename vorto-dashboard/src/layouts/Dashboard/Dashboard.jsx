import React, { Component } from 'react'
import { Route, Switch, Redirect } from 'react-router-dom'
import Header from '../../components/Header/Header'
import Sidebar from '../../components/Sidebar/Sidebar'
import Banner from '../../components/Banner/Banner'
import '../../assets/sass/lbd/_variables.scss';



import dashboardRoutes from '../../routes/dashboard.jsx'
import { connect } from 'react-redux';


const { store } = require('../../store')


const mapStateToProps = function () {
  return {
    selectedDevice: store.getState().selectedDevice,
  }
}

class Dashboard extends Component {

  componentDidUpdate(e) {
    if (
      e.history.location.pathname !== e.location.pathname &&
      document.documentElement.className.indexOf('nav-open') !== -1
    ) {
      document.documentElement.classList.toggle('nav-open')
    }
    if (e.history.action === 'PUSH') {
      document.documentElement.scrollTop = 0
      document.scrollingElement.scrollTop = 0
      this.refs.mainPanel.scrollTop = 0
    }
  }




  render() {
    const routes = dashboardRoutes.map((prop, key) => {
      if (prop.redirect) {
        return (<Redirect from={prop.path} to={prop.to} key={key} />)
      }
      return (<Route path={prop.path} component={prop.component} key={key} />)
    })


    return (
      <div className='wrapper'>
        <Banner />
        <Sidebar {...this.props} />

        <div id='main-panel' className='main-panel' ref='mainPanel'>
        <Header {...this.props} />

          <Switch>
            {routes}
          </Switch>
        </div>
      </div>
    )
  }
}



export default connect(mapStateToProps)(Dashboard)
