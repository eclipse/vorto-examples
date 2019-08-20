import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";
import { Redirect } from 'react-router-dom';

import ConnectivityStatus from "../ConnectivityStatus/ConnectivityStatus";

export class DeviceCard extends Component {
  state = {
    redirect: false
  }

  setRedirect = () => {
    this.setState({
      redirect: true
    })

    this.props.setSelectedDevice(this.props.device)
  }

  renderRedirect = () => {
    if (this.state.redirect) {
      return <Redirect to='/devicedashboard' />
    }
  }

  render() {
    const device = this.props.device

    return (
      <div className="card card-stats">
        <button className="device-container" onClick={this.setRedirect}>
          {this.renderRedirect()}
          <div className="content">
            <Row>
              <Col xs={12} sm={12} md={12} lg={12}>
                <div className="img-container">
                  <img className="device-img" alt={device.attributes.definition} src={device.imgSrc} />
                  <ConnectivityStatus device={device} />
                </div>
              </Col>
            </Row>

            <Row className="device-footer">
              <div className="footer-container">
                <hr />
                <Col xs={12} sm={12} md={12} lg={12}>
                  <div className="device-card-attr-container">
                    <p className="text-center device-desc-text"><span className="attr-keyword">{device.thingId}</span></p>
                    <p className="text-center device-desc-text">{device.attributes.thingName}</p>
                  </div>
                </Col>
              </div>
            </Row>
          </div>
        </button>
      </div>
    );
  }
}

export default DeviceCard;
