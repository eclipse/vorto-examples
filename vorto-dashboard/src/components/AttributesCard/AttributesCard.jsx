import React, { Component } from "react";
import { Grid, Row, Col } from "react-bootstrap";

import ConnectivityStatus from "../ConnectivityStatus/ConnectivityStatus"
import { getRepositoryLink } from '../../util';

export class AttributesCard extends Component {
  render() {
    const device = this.props.device;

    return (
      <div className="card card-stats">
        <div className="content">
          <Grid fluid>
            <Row>
              <Col xs={12} className="autoScroll">
                <h3>
                  {device.attributes.thingName}
                  <ConnectivityStatus device={device} />
                </h3>
              </Col>
            </Row>
            <Row>
              <Col lg={4} sm={8} xs={12}>
                <div className="attrImgContainer">
                  <img src={device.imgSrc} className="attrImg" alt="IoT device" />
                </div>
              </Col>
              <Col lg={4} sm={8} xs={12}>
                <div className="attrContainer">
                  <p className="attribute"><span className="attrKeyword">Thing ID: </span>{device.thingId}</p>
                  <p className="attribute"><span className="attrKeyword">Policy ID: </span>{device.policyId}</p>
                  <p className="attribute"><span className="attrKeyword">Device ID: </span>{device.attributes.deviceId}</p>
                </div>
              </Col>
              <Col lg={4} sm={8} xs={12}>
                <div className="attrContainer">
                  <p className="attribute"><span className="attrKeyword">Created by: </span>{device.attributes.createdBy}</p>
                  <p className="attribute">
                    <span className="attrKeyword">Definition: </span>
                    <a target="_blank" href={getRepositoryLink(device.attributes.definition)}>
                      {device.attributes.definition}
                    </a>
                  </p>
                </div>
              </Col>
            </Row>
            <hr />
            <Row>
              <Col xs={12}>
                <div className="">
                  <p><span className="attrKeyword">Created on: </span>{device.attributes.createdOn}</p>
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
