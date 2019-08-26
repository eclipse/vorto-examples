import React, { Component } from 'react'
import { Row, Col } from 'react-bootstrap'
import OSMap from '../OSMap/OSMap'

import { pollThings } from '../../util/DataPoller'
import { applyFilters } from '../../util/ViewFilters'
import log from 'loglevel'
log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')
const DEVICE_REFRESH_MS = process.env.REACT_APP_DEVICE_REFRESH_MS || 5000

function pollDevices () {
  pollThings("location")
    .then(things => applyFilters(things))
    .then(things => {
      log.debug(things)
      this.setState({
        things
      })
    })
    .catch(err => `Could not poll devices... ${err}`)
}

export class MapCard extends Component {
  state = {
    things: []
  }

  componentDidMount () {
    this.thingInterval = setInterval(pollDevices.bind(this), DEVICE_REFRESH_MS)
  }

  componentWillUnmount () {
    clearInterval(this.thingInterval)
  }

  render () {
    return (
      <div className='card card-stats map-attr-card'>
        <div className='content'>
          <Row>
            <Col xs={12} sm={12} md={12} lg={12} className='map-card'>
              <OSMap displayTooltip={true} things={this.state.things}/>
            </Col>
          </Row>
        </div>
      </div>
    )
  }
}

export default MapCard
