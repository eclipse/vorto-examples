import React, { Component } from "react";
import { Grid, Row, Col } from "react-bootstrap";

import { getRepositoryLink } from '../../util';

export class AttributesCard extends Component {
  render() {
    const device = this.props.device;

    return (
      <div className="card card-stats">
        <div className="content">
          <Grid fluid>
            <Row>
              <Col xs={12} sm={12} md={12} lg={12} className="autoScroll">
                <h3>
                  {device.attributes.thingName}
                </h3>
              </Col>
            </Row>
            <Row>
              <Col lg={4} sm={6} xs={12}>
                <div className="attrImgContainer">
                  <img src={device.imgSrc} className="attrImg" alt="IoT device" />
                </div>
              </Col>
              <Col lg={2} sm={0} xs={0} />
              <Col lg={6} sm={6} xs={12}>
                <div className="attrContainer">
                  <p className="attribute"><span className="attrKeyword">Thing ID: </span>{device.thingId}</p>
                  <p className="attribute"><span className="attrKeyword">Policy ID: </span>{device.policyId}</p>
                  <p className="attribute"><span className="attrKeyword">Device ID: </span>{device.thingId}</p>
                  <p className="attribute">
                    <span className="attrKeyword">Definition: </span>
                    <a target="_blank" href={getRepositoryLink(device.attributes.definition)}>
                      {device.attributes.definition}
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
