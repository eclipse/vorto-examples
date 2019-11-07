import React from 'react'
import Actions from '../../actions'
import { connect } from 'react-redux'
import { Grid, Row, Col } from 'react-bootstrap'

import AttributesCard from '../Device/Cards/AttributesCard'

import Sensors from '../../components/Sensors/Sensors'

const { store } = require('../../store')


const mapStateToProps = () => {
  return {
    selectedDevice: store.getState().selectedDevice,
  }
}

function mapDispatchToProps(dispatch) {
  return {
    selectDevice: selectedDevice => dispatch(Actions.selectDevice(selectedDevice))
  }
}


const DeviceView = (props) => {
  var device = props.selectedDevice
  if (device.thingId) {
    return (
      <div className='content'>
        <Grid fluid>
          <Row>
            <Col xs={12} sm={12} md={12} lg={12}>
              <AttributesCard device={device} />
            </Col>
          </Row>
          <Sensors></Sensors>
        </Grid>
      </div>
    )
  }
  return (
    <div className='content'>
      <div className="content-header"><span>no device selected</span></div>
    </div>
  )
}

const Device = connect(mapStateToProps, mapDispatchToProps)(DeviceView)

export default Device
