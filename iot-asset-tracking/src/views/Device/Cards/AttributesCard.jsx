import React, { Component } from 'react'
import { Grid, Row, Col } from 'react-bootstrap'

import { getRepositoryLink } from '../../../util'

export class AttributesCard extends Component {
  render() {
   
    const device = (this.props.device.thingId !== undefined) ? this.props.device : {}
    const attributes = (device.attributes !== undefined) ? device.attributes : {}
    const thingName =  (attributes.thingName !== undefined) ? attributes.thingName : "undefined"

    const features = device.features
    let deviceInformation

    for (var feature in features) {
      const featureObj = features[feature]
      let definition = featureObj.definition

      if (!definition) {
        continue
      }

      if (Array.isArray(definition)) {
        definition = definition[0]
      }

      if (!definition.includes('org.eclipse.vorto:DeviceInformation')) {
        continue
      }

      deviceInformation = featureObj.properties.status
    }

    const deviceInformationCol = deviceInformation ?
      <Col lg={7} md={12} xs={12}>

        <div className='attr-container'>
          <div className='attr-img-container'>
            <img src={device.imgSrc} className='attr-img' alt='IoT device' />
          </div>

          <div className="attr-info-block">
            <p className='attribute'>
              <span className='attr-info-block_header'>Physical Device Information</span>
            </p>


            {Object.keys(deviceInformation).map((key, index) => {
              const information = deviceInformation[key]
              if (!information) {
                return null
              }

              const upperCaseKey = key.replace(/./, x => x.toUpperCase())
              return <p className='attribute' key={index}><span className='attr-keyword'>{upperCaseKey}: </span>{information}</p>
            })}

          </div>
        </div>
      </Col> : <Col />


    return (
      <div className='card card_attributes'>
        
        <div className='thing-name'>
        <div className='attr-img-container-mobile'>
            <img src={device.imgSrc} className='attr-img' alt='IoT device' />
          </div>
          <span>{thingName}</span>
        </div>
        <Grid fluid>
          <Row>
            {deviceInformationCol}
            <Col lg={5} md={12} xs={12}>
              <div className='attr-info-block_virtual'>
                <div className="attr-info-block">
                  <p className='attribute'>
                    <span className='attr-info-block_header'>Virtual Device Information</span>
                  </p>
                  <p className='attribute'><span className='attr-keyword'>Thing ID: </span>{device.thingId}</p>
                  <p className='attribute'>
                    <span className='attr-keyword'>Definition: </span>
                    <a target='_blank' rel="noopener noreferrer" href={getRepositoryLink(attributes.definition)}>
                      {attributes.definition}
                    </a>
                  </p>
                </div>
              </div>
            </Col>
          </Row>
        </Grid>
      </div>
    )
  }
}


export default AttributesCard
