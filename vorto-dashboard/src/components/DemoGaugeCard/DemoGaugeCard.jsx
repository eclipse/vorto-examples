import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";

import { getRepositoryLink } from '../../util';

// To be removed once the Mapping Engine supports nested Function Blocks
export class DemoGaugeCard extends Component {
  render() {
    const values = this.props.feature.properties;
    const unit = values.status.unit;
    const value = Math.round(values.status.value * 100) / 100;

    const currVal = value || 0;
    const minVal = -4;
    const maxVal = 4;

    const currDeg = ((currVal - minVal) * 180) / (maxVal - minVal)

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
                <div className="gauge">
                  <div className="gauge-percentage" style={{ transform: `rotate(${currDeg}deg)` }} />
                  <span className="gauge-value data-val" style={{
                    fontSize: "1.8em",
                    top: "60%",
                    left: "25%"
                  }}>{`${currVal}${unit}`}</span>
                </div>
                <div className="gauge-mask" />
                <span className="gauge-min data-val">{minVal}{unit}</span>
                <span className="gauge-max data-val">{maxVal}{unit}</span>
              </div>
            </Col>
          </Row>
        </div>
      </div>
    );
  }
}

export default DemoGaugeCard;
