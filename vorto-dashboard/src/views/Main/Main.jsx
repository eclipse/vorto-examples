import React from 'react'
import { Grid, Row, Col } from 'react-bootstrap'
import { connect } from 'react-redux'

import Actions from '../../actions'
import MapCard from '../../components/MapCard/MapCard'

// import kpiConfig from '../../util/kpi_config.json'
import KpiCardSingleValue from '../../components/KpiCard/KpiCardSingleValue';
import KpiCardTwoCol from '../../components/KpiCard/KpiCardTwoCol';

const mapStateToProps = state => {
  return {
    devices: state.devices.devices,

  }
}

function mapDispatchToProps (dispatch) {
  return {
    selectDevice: device => dispatch(Actions.selectDevice(device))
  }
}



const MainView = ({ devices, selectDevice }) => {
  const singleValKpi = (
    <Col xs={6} sm={6} md={6} lg={6} key={0}>
      <KpiCardSingleValue heading={'Number of Water Level Sensors'} feature={'waterlevel'} />
    </Col>)

  const twoColKpi = (
    <Col xs={6} sm={6} md={6} lg={6} key={0}>
      <KpiCardTwoCol heading={'Predominant soil state per Zone'} feature={'waterlevel'} />
    </Col>)



  return (
    <div className='fill-hw-locate'>
      <Grid fluid>
        <Row>
          {singleValKpi}
          {twoColKpi}
        </Row>
        <Row>
          <Col xs={12} sm={12} md={12} lg={12}>
            <MapCard />
          </Col>
        </Row>
      </Grid>
    </div>
  )
}

const Main = connect(mapStateToProps, mapDispatchToProps)(MainView)

export default Main
