import React, { Component } from 'react'
import { Navbar, Nav } from 'react-bootstrap'
import SimulationPopup from '../../views/SimulationPopup/SimulationPopup'
import Actions from '../../actions'
import { store } from '../../store'
import request from 'request-promise-native'

const PORT = process.env.REACT_APP_PORT || 8080

const SHOW_SIMULATOR = process.env.REACT_APP_SHOW_SIMULATOR || true

const simReqOpts = {
  url: `http://${window.location.hostname}:${PORT}/api/v1/simulator`,
  method: 'GET',
  json: true
}

function pollSimulatorState () {
  request(simReqOpts)
    .then(res => {
      const running = res.running
      const startTime = res.startTime
      store.dispatch(Actions.updateSimulator({ running, startTime }))
    })
    .catch(err => `Could not poll simulator data from backend... ${err}`)
}


class Header extends Component {
  constructor(props) {
    super(props)
    this.mobileSidebarToggle = this.mobileSidebarToggle.bind(this)
    this.state = {
      sidebarExists: true,
      showPopup: false
    }
  }


  mobileSidebarToggle(e) {
    if (this.state.sidebarExists === false) {
      this.setState({
        sidebarExists: true
      })
    }
    e.preventDefault()
    document.documentElement.classList.toggle('nav-open')
    var node = document.createElement('div')
    node.id = 'bodyClick'
    node.onclick = function () {
      this.parentElement.removeChild(this)
      document.documentElement.classList.toggle('nav-open')
    }
    document.body.appendChild(node)
  }


  SimulatorButton = () => {
    if (SHOW_SIMULATOR) {
      return <button className='header-button' onClick={this.togglePopup.bind(this)}>
      Simulate things
      </button>
    } return null
    
  }

  togglePopup() {
    this.setState({
      showPopup: !this.state.showPopup
    });
  }
  componentDidMount () {
      // only set polling for simulator if it is displayed
      if (SHOW_SIMULATOR) {
        this.simulatorInterval = setInterval(pollSimulatorState, 10000)
      }
  }
  componentWillUnmount () {

    if (SHOW_SIMULATOR) {
      clearInterval(this.simulatorInterval)
    }
  }


  render() {


    return (
      <Navbar fluid>
        {this.state.showPopup ?
          <SimulationPopup
            closePopup={this.togglePopup.bind(this)}
            closeIconSrc = {require('../../assets/img/close-icon.svg')}
          />
          : null
        }
        <Navbar.Header>
          <Nav pullRight>
            <Navbar.Toggle onClick={this.mobileSidebarToggle} />
            <div className='navbar-header__button-row'>
              { this.SimulatorButton() }
              {/* <button className='header-button'>color Toggle</button> */}
            </div>
          </Nav>
        </Navbar.Header>
      </Navbar >
    )
  }
}


export default Header
