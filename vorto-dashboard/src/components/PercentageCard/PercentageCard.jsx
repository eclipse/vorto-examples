import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";

import { getRepositoryLink } from '../../util';

export class PercentageCard extends Component {
  render() {
    const values = this.props.feature.properties;
    const currVal = values.status.value.currentMeasured.value || 0;
    const minVal = 0;
    const maxVal = 100;

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
                <div className="percentage-container">
                  <div className="percentage">
                    <div className="percentage-background" style={{
                      height: `${currPerc}%`
                    }}>
                    </div>
                  </div>
                  <div className="percentage-percentage">
                    <span className="data-val">{currVal}%</span>
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

export default PercentageCard;
