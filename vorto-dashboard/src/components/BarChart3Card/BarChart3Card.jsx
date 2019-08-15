import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";

import { getRepositoryLink } from '../../util';

export class BarChart3Card extends Component {
  render() {
    const feature = this.props.feature;
    const featureName = this.props.featureName;
    const featureStatus = feature.properties.status;

    const xValue = (Math.round(featureStatus.value.x * 100) / 100) || 0;
    const yValue = (Math.round(featureStatus.value.y * 100) / 100) || 0;
    const zValue = (Math.round(featureStatus.value.z * 100) / 100) || 0;

    const minVal = 15;
    let maxVal = 100;

    if (xValue > maxVal) {
      maxVal = xValue;
    }

    if (yValue > maxVal) {
      maxVal = yValue;
    }

    if (zValue > maxVal) {
      maxVal = zValue;
    }

    const xPerc = ((xValue - minVal) * 100) / (maxVal - minVal);
    const yPerc = ((yValue - minVal) * 100) / (maxVal - minVal);
    const zPerc = ((zValue - minVal) * 100) / (maxVal - minVal);

    return (
      <div className="card card-stats attr-card">
        <div className="content">
          <Row>
            <Col xs={12} sm={12} md={12} lg={12}>
              <div className="text-center">
                <h4>
                  <a href={getRepositoryLink(feature.definition)} target="_blank" >{featureName}</a>
                </h4>
              </div>
            </Col>
          </Row>

          <Row>
            <Col xs={12} sm={12} md={12} lg={12}>
              <div className="inner-card-container">
                <div className="chart-container">
                  <div className="barchart-container">
                    <div className="bar-chart" style={{
                      height: `${xPerc}%`,
                      backgroundColor: "#015c95"
                    }}>
                      <span className="data-val">{xValue}</span>
                    </div>
                    X
                  </div>
                  <div className="barchart-container">
                    <div className="bar-chart" style={{
                      height: `${yPerc}%`,
                      backgroundColor: "#156e91"
                    }}>
                      <span className="data-val">{yValue}</span>
                    </div>
                    Y
                  </div>
                  <div className="barchart-container">
                    <div className="bar-chart" style={{
                      height: `${zPerc}%`,
                      backgroundColor: "#053f6f"
                    }}>
                      <span className="data-val">{zValue}</span>
                    </div>
                    Z
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

export default BarChart3Card;
