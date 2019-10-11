import React, { Component } from 'react'
import Spinner from '../Spinner/Spinner';

import { pollThings } from '../../util/DataPoller'
import { applyFilters } from '../../util/ViewFilters'
// import { buildKpiInfo } from '../../util/KPIConfigInterpreter'

import log from 'loglevel'
log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')
const DEVICE_REFRESH_MS = process.env.REACT_APP_DEVICE_REFRESH_MS || 5000

function pollDevices () {
  const query = this.props.config.modelId
  log.debug(query)

  if (!query) {
    return
  }

  pollThings(query)
    .then(things => applyFilters(things))
    .then(things => {
      this.setState({
        things
      })
    })
    .catch(err => `Could not poll devices... ${err}`)
}

export class KpiCard extends Component {
  state = {
    loading: true,
    things: []
  }

  componentDidMount () {
    this.thingInterval = setInterval(pollDevices.bind(this), DEVICE_REFRESH_MS)
  }

  componentWillUnmount () {
    clearInterval(this.thingInterval)
  }

  getKpiCard (content) {
    return (
      <div className='card card-stats attr-card'>
        <div className='content no-overflow'>
          <h4>{this.props.heading}</h4>
          {content}
        </div>
      </div>
    )
  }

  render () {
    if (this.state.loading && this.state.things.length !== 0) {
      this.setState({
        ...this.state,
        loading: false
      })
    }

    if (this.state.loading) {
      return this.getKpiCard(<Spinner />)
    }

    return this.getKpiCard(
      <div className='attr-container'>
        {this.props.content}
      </div>
    )
  }
}

export default KpiCard
