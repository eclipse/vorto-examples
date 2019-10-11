import React, { Component } from 'react'
import { Row, Col } from 'react-bootstrap'
import OSMap from '../OSMap/OSMap'
import { pollThings } from '../../util/DataPoller'
import { applyFilters } from '../../util/ViewFilters'
import log from 'loglevel'
import Actions from '../../actions'



log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')
const DEVICE_REFRESH_MS = process.env.REACT_APP_DEVICE_REFRESH_MS || 5000

const { store } = require('../../store')


function pollDevices() {
  pollThings('location')
    .then(things => applyFilters(things))
    .then(things => {
      this.setState({
        things
      })
    })
    .catch(err => `Could not poll devices... ${err}`)
    this.dispatchDevices()

}



export class MapCard extends Component {
  state = {
    things: [],
    selectedDevice: store.getState().selectedDevice
  }

  dispatchDevices() {
    const devices = this.state.things
    if (devices.length > 0) {
      store.dispatch(Actions.updateDevices(devices, '', []))
    }
  }


  componentDidMount() {
    this.thingInterval = setInterval(pollDevices.bind(this), DEVICE_REFRESH_MS)
    store.subscribe(() => { this.refreshSelectedDevice() })

  }

  componentWillUnmount() {
    clearInterval(this.thingInterval)
  }

  
  refreshSelectedDevice() {
    const thingId = store.getState().selectedDevice.thingId
    const things = this.state.things
    if (things.length > 0) {
        this.setState({ selectedDevice: this.state.things.find(x => x.thingId === thingId) })
    }else{
        this.setState({ selectedDevice: store.getState().selectedDevice })
    }
}

  render() {

    return (
      <div className='card card-stats map-attr-card'>
        <div className='content'>
          <Row>
            <Col xs={12} sm={12} md={12} lg={12} className='map-card'>
              <OSMap displayTooltip={true} things={this.state.things} />
            </Col>
          </Row>
        </div>
      </div>
    )

  }
}

export default MapCard
