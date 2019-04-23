import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";

export class DeviceTooltip extends Component {
  render() {
    const locationFeature = this.props.device.features.location
    const latitude = locationFeature.properties.status.latitude
    const longitude = locationFeature.properties.status.longitude

    return (
      <div className="content">
        <div>
          <h4><button className="textButton" onClick={this.props.redirect}>{this.props.device.attributes.thingName}</button></h4>
        </div>
        <Row>
          <Col xs={12}>
            <div className="text-center center-text">
              <img alt="img of the device" src={this.props.device.imgSrc} height="100px" />
            </div>
          </Col>
        </Row>
        <Row>
          <Col xs={12}>
            <div className="">
              <p><span className="attrKeyword">Thing ID: </span>{this.props.device.thingId}</p>
              <p><span className="attrKeyword">Location: </span>{latitude}lat, {longitude}lon</p>
            </div>
          </Col>
        </Row>
        <hr />
        <Row>
          <Col xs={12}>
            <div className="">
              <p><span className="attrKeyword">Created on: </span>{this.props.device.attributes.createdOn}</p>
            </div>
          </Col>
        </Row>
      </div>
    );
  }
}

export default DeviceTooltip;
