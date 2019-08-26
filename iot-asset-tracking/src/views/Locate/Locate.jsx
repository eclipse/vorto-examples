import React from 'react'
import { Grid, Row, Col } from 'react-bootstrap'
import { connect } from 'react-redux'

import KpiCard from '../../components/KpiCard/KpiCard'
import Actions from '../../actions'
import MapCard from '../../components/MapCard/MapCard';

const mapStateToProps = state => {
  return {
    devices: state.devices.devices
  }
}

function mapDispatchToProps (dispatch) {
  return {
    selectDevice: device => dispatch(Actions.selectDevice(device))
  }
}

const ConnectedMaps = ({ devices, selectDevice }) => {
  const kpiCards = [1, 2, 3].map((kpi, index) => {
    return <Col xs={4} sm={4} md={4} lg={4} key={index}><KpiCard /></Col>
  })

  return (
    <div className='fill-hw-locate'>
      <Grid fluid>
        <Row>
          {kpiCards}
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

const Maps = connect(mapStateToProps, mapDispatchToProps)(ConnectedMaps)

export default Maps
