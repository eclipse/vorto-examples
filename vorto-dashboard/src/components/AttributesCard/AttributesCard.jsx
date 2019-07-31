import React, { Component } from "react";
import { Grid, Row, Col } from "react-bootstrap";

import { getRepositoryLink } from '../../util';

export class AttributesCard extends Component {
  render() {
    const device = this.props.device;
    const attributes = device.attributes;
    const thingName = attributes.thingName;

    const features = device.features;
    let deviceInformation;

    for (const feature in features) {
      const featureObj = features[feature]
      let definition = featureObj.definition

      if (!definition) {
        continue;
      }

      if (Array.isArray(definition)) {
        definition = definition[0]
      }

      if (!definition.includes("org.eclipse.vorto:DeviceInformation:1.0.0")) {
        continue;
      }

      deviceInformation = featureObj.properties.status
    }

    const deviceInformationCol = deviceInformation ? <Col lg={4} sm={6} xs={12}>
      <div className="attrContainer">
        {Object.keys(deviceInformation).map(key => {
          const information = deviceInformation[key];
          if (!information) {
            return null;
          }

          const upperCaseKey = key.replace(/./, x => x.toUpperCase());
          return <p className="attribute"><span className="attrKeyword">{upperCaseKey}: </span>{information}</p>
        })}
      </div>
    </Col> : <Col />;

    return (
      <div className="card card-stats">
        <div className="content">
          <Grid fluid>
            <Row>
              <Col xs={12} sm={12} md={12} lg={12} className="autoScroll">
                <h3>
                  {thingName}
                </h3>
              </Col>
            </Row>
            <Row>
              <Col lg={4} sm={6} xs={12}>
                <div className="attrImgContainer">
                  <img src={device.imgSrc} className="attrImg" alt="IoT device" />
                </div>
              </Col>
              {deviceInformationCol}
              <Col lg={4} sm={6} xs={12}>
                <div className="attrContainer">
                  <p className="attribute"><span className="attrKeyword">Thing ID: </span>{device.thingId}</p>
                  <p className="attribute"><span className="attrKeyword">Policy ID: </span>{device.policyId}</p>
                  <p className="attribute"><span className="attrKeyword">Device ID: </span>{device.thingId}</p>
                  <p className="attribute">
                    <span className="attrKeyword">Definition: </span>
                    <a target="_blank" href={getRepositoryLink(attributes.definition)}>
                      {attributes.definition}
                    </a>
                  </p>
                </div>
              </Col>
            </Row>
          </Grid>
        </div>
      </div>
    );
  }
}

export default AttributesCard;
