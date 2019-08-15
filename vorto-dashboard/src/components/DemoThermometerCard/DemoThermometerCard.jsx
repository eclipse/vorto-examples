import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";

import { getRepositoryLink } from '../../util';

// To be removed once the Mapping Engine supports nested Function Blocks
export class DemoThermometerCard extends Component {
  render() {
    const values = this.props.feature.properties;
    const currVal = Math.round(values.status.value * 100) / 100 || 0;
    const unit = values.status.unit;
    const minVal = -5;
    const maxVal = 5;

    const currPerc = ((currVal - minVal) * 100) / (maxVal - minVal)

    return (
      <div className="card card-stats attr-card">
        <div className="content">
          <Row>
            <Col xs={12} sm={12} md={12} lg={12}>
              <div className="text-center">
                <h4>
                  <a href={getRepositoryLink(this.props.feature.definition)} target="_blank" >{this.props.featureName}</a>
                </h4>
              </div>
            </Col>
          </Row>

          <Row>
            <Col xs={12} sm={12} md={12} lg={12}>
              <div className="inner-card-container">
                <div className="thermometer">
                  <span className="thermometer-glass">
                    <span className="thermometer-maxVal">max. <span className="data-val">{maxVal}°{unit}</span></span>
                    <span className="thermometer-currVal"><span className="data-val">{currVal}°{unit}</span></span>
                    <span className="thermometer-minVal">min. <span className="data-val">{minVal}°{unit}</span></span>
                    <span className="thermometer-amount" style={{ height: `${currPerc}%` }}></span>
                  </span>
                  <div className="thermometer-bulb">
                    <span className="thermometer-redCircle"></span>
                    <span className="thermometer-filler"></span>
                  </div>
                </div>
              </div>
            </Col>
          </Row>
        </div>
      </div>
    );
  }
}

export default DemoThermometerCard;
