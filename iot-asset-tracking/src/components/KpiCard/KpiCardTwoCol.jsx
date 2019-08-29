import React, { Component } from 'react'
import Spinner from '../Spinner/Spinner';

import { pollThings } from '../../util/DataPoller'
import { applyFilters } from '../../util/ViewFilters'
// import { buildKpiInfo } from '../../util/KPIConfigInterpreter'
import { getTextAfterColon } from '../../util'

import log from 'loglevel'
log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')
const DEVICE_REFRESH_MS = process.env.REACT_APP_DEVICE_REFRESH_MS || 5000

function pollDevices () {
  pollThings()
    .then(things => applyFilters(things))
    .then(things => {
      this.setState({
        things
      })
    })
    .catch(err => `Could not poll devices... ${err}`)
}

export class KpiCardTwoCol extends Component {
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

    const filteredThings = this.state.things.filter(thing => {
      return Object.keys(thing.features).some(feature => {
        return thing.features[feature].definition.some(def => def.toLowerCase().includes(featureDef))
      })
    })

    const soilStatesZones = {}
    filteredThings.forEach(thing => {
      const zones = thing.attributes.topology.referenceBy
      const waterLevelFeatureName = Object.keys(thing.features).find(featureName => {
        const feature = thing.features[featureName]

        return feature.definition.some(def => def.toLowerCase().includes(featureDef))
      })

      const soilState = thing.features[waterLevelFeatureName].properties.status.soilState

      zones.forEach(zone => {
        const zoneName = zone.thingId
        const zoneState = soilStatesZones[zoneName]

        if (zoneState) {
          soilStatesZones[zoneName][soilState]++
        } else {
          const zoneSoilState = {}
          zoneSoilState[soilState] = 1
          soilStatesZones[zoneName] = zoneSoilState 
        }
      })
    })

    log.debug(soilStatesZones)

    const zoneStateElems = Object.keys(soilStatesZones).map(zoneName => {
      const zoneValuesObj = soilStatesZones[zoneName]

      let majorState
      let majorStateAmount = 0
      for (const state in zoneValuesObj) {
        if (zoneValuesObj[state] > majorStateAmount) {
          majorStateAmount = zoneValuesObj[state]
          majorState = state
        }
      }

      return {zone: zoneName, state: majorState}
    })
    .map(zoneState => {
      const zoneName = getTextAfterColon(zoneState.zone)

      return (
        <h3>{zoneName}: <span className='data-val'>{zoneState.state}</span></h3>
      )
    })

    return this.getKpiCard(
      <div className='kpi-item-container'>
        <div className='kpi-item-col'>
          {zoneStateElems}          
        </div>
      </div>
    )
  }
}

export default KpiCardTwoCol
