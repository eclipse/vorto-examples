import React, { Component } from 'react'
import { Row, Col } from 'react-bootstrap'

const PORT = process.env.REACT_APP_PORT || 8080

export class DeviceTooltip extends Component {
  render () {
    const locationFeature = this.props.device.features.location
    const latitude = locationFeature.properties.status.latitude
    const longitude = locationFeature.properties.status.longitude

    // decide whether to use full web url pointing to either vorto or default, or to local file 
    const thingImgSrc = this.props.device.imgSrc
    const imgSrc = thingImgSrc.startsWith('http') ? thingImgSrc : `http://${window.location.hostname}:${PORT}/${this.props.device.imgSrc}`

    return (
      <div className='content'>
        <div>
          <h4>{this.props.device.thingId}</h4>
        </div>
        <Row>
          <Col xs={12} sm={12} md={12} lg={12}>
            <div className='text-center center-text'>
              <img alt='img of the device' src={imgSrc} height='100px' />
            </div>
          </Col>
        </Row>
        <Row>
          <Col xs={12} sm={12} md={12} lg={12}>
            <div className=''>
              <p><span className='attr-keyword'>Thing ID: </span>{this.props.device.attributes.thingName}</p>
              <p><span className='attr-keyword'>Location: </span>{latitude}lat, {longitude}lon</p>
            </div>
          </Col>
        </Row>
      </div >
    )
  }
}

export default DeviceTooltip
