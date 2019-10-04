import React from 'react'
import Actions from '../../actions'
import { connect } from 'react-redux'
import { Grid, Row, Col } from 'react-bootstrap'

import AttributesCard from '../Device/Cards/AttributesCard'
import CodeCard from '../Device/Cards/CodeCart'
import BarChart3Card from '../Device/Cards/BarChart3Card'
import BatteryCard from '../Device/Cards/BatteryCard'
import ConnectivityCard from '../Device/Cards/ConnectivityCard'
import LocationCard from '../Device/Cards/LocationCard'
import ThermometerCard from '../Device/Cards/ThermometerCard'
import GaugeCard from '../Device/Cards/GaugeCard'
import PercentageCard from '../Device/Cards/PercentageCard'

// to be removed
import DemoThermometerCard from '../Device/Cards/DemoThermometerCard'
import DemoStateNumberCard from '../Device/Cards/DemoStateNumberCard'
import DemoGaugeCard from '../Device/Cards/DemoGaugeCard'


import {
  CATEGORIES,
  mapDeftoCardCategorie
} from '../../util/cardUtils'

const { store } = require('../../store')

const mapStateToProps = state => {
  return {
    devices: state.devices.devices,
    selectedDevice: store.getState().selectedDevice,
    loading: state.loading
  }
}

function mapDispatchToProps(dispatch) {
  return {
    selectDevice: selectedDevice => dispatch(Actions.selectDevice(selectedDevice))
  }
}

const mapCategorieToCard = (categorieType, device, featureObj, featureName) => {
  switch (categorieType) {
    case CATEGORIES.GAUGE:
      return (
        <GaugeCard
          featureName={featureName}
          feature={featureObj} />
      )
    case CATEGORIES.BAR3CHART:
      return (
        <BarChart3Card
          featureName={featureName}
          feature={featureObj} />
      )
    case CATEGORIES.BATTERY:
      return (
        <BatteryCard
          featureName={featureName}
          feature={featureObj} />
      )
    case CATEGORIES.CONNECTION:
      return (
        <ConnectivityCard
          featureName={featureName}
          feature={featureObj} />)
    case CATEGORIES.LOCATION:
      return (
        <LocationCard
          featureName={featureName}
          device={device} />
      )
    case CATEGORIES.TEMPERATURE:
      return (
        <ThermometerCard
          featureName={featureName}
          feature={featureObj} />
      )
    case CATEGORIES.PERCENTAGE:
      return (
        <PercentageCard
          featureName={featureName}
          feature={featureObj} />
      )
    // To be removed once the Mapping Engine supports nested Function Blocks
    case CATEGORIES.DEMO_TEMPERATURE:
      return (
        <DemoThermometerCard
          featureName={featureName}
          feature={featureObj} />)
    case CATEGORIES.DEMO_STATE_NUMBER:
      return (
        <DemoStateNumberCard
          featureName={featureName}
          feature={featureObj} />)
    case CATEGORIES.DEMO_GAGE:
      return (
        <DemoGaugeCard
          featureName={featureName}
          feature={featureObj} />)
    default:
      return (
        <CodeCard
          featureName={featureName}
          feature={featureObj} />
      )

  }
}



const DeviceView = (props) => {

  var sensors = {}
  var device = props.selectedDevice

  if (device.thingId !== undefined) {
    sensors = (device.features === undefined) ? {}
      : Object.keys(device.features)
        .sort()
        .map((featureName, index) => {
          const featureObj = device.features[featureName]
          const featureDefs = featureObj.definition

          const categorieType = mapDeftoCardCategorie(featureDefs)

          if (categorieType === CATEGORIES.NO_WIDGET) {
            return null
          }

          const featureCard = mapCategorieToCard(categorieType, device, featureObj, featureName)

          return (<Col xs={12} sm={6} md={6} lg={4} key={index}>{featureCard}</Col>)
        })
  }

  else {
    return <Col xs={12} sm={6} md={6} lg={4}> <h2>No asset selected </h2> </Col>

  }

  return (
    <div className='content'>
      <Grid fluid>

        <Row>
          <Col xs={12} sm={12} md={12} lg={12}>
            <AttributesCard device={device} />
          </Col>
        </Row>

        <Row>
          {sensors}
        </Row>
      </Grid>
    </div>
  )
}

const Device = connect(mapStateToProps, mapDispatchToProps)(DeviceView)

export default Device
