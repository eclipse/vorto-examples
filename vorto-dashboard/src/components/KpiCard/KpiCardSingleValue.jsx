import React, { Component } from 'react'
import Spinner from '../Spinner/Spinner';

import { pollThings } from '../../util/DataPoller'
// import { buildKpiInfo } from '../../util/KPIConfigInterpreter'

import log from 'loglevel'
log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')
const DEVICE_REFRESH_MS = process.env.REACT_APP_DEVICE_REFRESH_MS || 5000

function pollDevices () {
  pollThings()
    .then(things => {
      this.setState({
        things
      })
    })
    .catch(err => `Could not poll devices... ${err}`)
}

export class KpiCardSingleValue extends Component {
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
          <h4 className='kpi-heading'>{this.props.heading}</h4>
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

    log.debug(this.state.things)

    if (this.state.loading) {
      return this.getKpiCard(<div className='spinner-half'><Spinner /></div>)
    }

    const featureDef = this.props.feature.toLowerCase()

    const dataValue = this.state.things.filter(thing => {
      return Object.keys(thing.features).some(feature => {
        return thing.features[feature].definition.some(def => def.toLowerCase().includes(featureDef))
      })
    }).length

    return this.getKpiCard(
      <div className='kpi-item-container'>
        <div className='kpi-item-row'>
          <h1 className='data-val'>{dataValue}</h1>
        </div>
      </div>
    )
  }
}

export default KpiCardSingleValue
