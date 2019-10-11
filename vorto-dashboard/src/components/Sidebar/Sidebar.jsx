import React, { Component } from 'react'

import { pollThings } from '../../util/DataPoller'
import TreeViewNav from './TreeViewNav'
import log from 'loglevel'
import logo from '../../assets/img/vorto_logo.png'


log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')
const DEVICE_REFRESH_MS = process.env.REACT_APP_DEVICE_REFRESH_MS || 5000



function pollDevices () {
  pollThings()
    .then(things => {
      this.setState({ ...this.state, things })
    })
    .catch(err => `Could not poll devices... ${err}`)
}

class Sidebar extends Component {
  constructor (props) {
    super(props)
    this.state = {
      things: [],
      width: window.innerWidth
    }
  }



  activeRoute (routeName) {
    return this.props.location.pathname.indexOf(routeName) > -1 ? 'active' : ''
  }

  updateDimensions () {
    this.setState({ width: window.innerWidth })
  }

  componentDidMount () {
    this.updateDimensions()
    window.addEventListener('resize', this.updateDimensions.bind(this))
    this.thingInterval = setInterval(pollDevices.bind(this), DEVICE_REFRESH_MS)
  }

  componentWillUnmount () {
    clearInterval(this.thingInterval)
  }

  render () {
    return (
      <div
        id='sidebar'
        className='sidebar'
        data-color='black'>
        <div className='logo'>
          <a
            target="_blank" rel="noopener noreferrer"
            href='https://www.eclipse.org/vorto/'
            className='simple-text logo-mini'
          >
            <div className='logo-img'>
              <img src={logo} alt='logo_image' />
            </div>
          </a>
          <a
            target="_blank" rel="noopener noreferrer"
            href='https://www.eclipse.org/vorto/'
            className='simple-text logo-normal'>
            Eclipse Vorto
          </a>
        </div>
        <div className='sidebar-wrapper'>
          <TreeViewNav 
            things={this.state.things} 
            />
        </div>
      </div>
    )
  }
  
}

export default Sidebar
