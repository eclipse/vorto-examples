import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";

import { getRepositoryLink } from '../../util';

export class BatteryCard extends Component {
  render() {
    // TODO adjust retrieval of attributes to be smarter 
    // (Get the definition of the function blocks and find attribute values)
    const values = this.props.feature.properties;
    const currVal = values.status.remainingCapacity.value;
    const minVal = 0;
    const maxVal = 100;

    const currPerc = ((currVal - minVal) * 100) / (maxVal - minVal)
    const barColor = currPerc <= 20 ? "#f26d60" : currPerc <= 40 ? "#f9f963" : "#a2f260";

    return (
      <div className="card card-stats attrCard">
        <div className="content">
          <Row>
            <Col xs={12}>
              <div className="text-center">
                <h4>
                  <a href={getRepositoryLink(this.props.feature.definition[0])} target="_blank" >{this.props.featureName}</a>
                </h4>
              </div>
            </Col>
          </Row>

          <Row>
            <Col xs={12}>
              <div className="innerCardContainer">
                <div className="batteryContainer">
                  <div class="battery">
                    <div className="battery-background" style={{
                      width: `${currPerc}%`,
                      backgroundColor: barColor
                    }}>
                    </div>
                  </div>
                  <div className="battery-percentage">
                    <span className="dataVal">{currVal}%</span>
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

export default BatteryCard;
