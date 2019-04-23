import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";

import { getRepositoryLink } from '../../util';

export class ThermometerCard extends Component {
  render() {
    // TODO adjust retrieval of attributes to be smarter 
    // (Get the definition of the function blocks and find attribute values)
    const values = this.props.feature.properties;
    const currVal = values.status.value.currentMeasured;
    const minVal = values.status.value.minMeasured;
    const maxVal = values.status.value.maxMeasured || currVal;

    const currPerc = ((currVal - minVal) * 100) / (maxVal - minVal)

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
                <div className="thermometer">
                  <span className="thermometer-glass">
                    <span className="thermometer-maxVal">max. <span className="dataVal">{maxVal}°C</span></span>
                    <span className="thermometer-currVal"><span className="dataVal">{currVal}°C</span></span>
                    <span className="thermometer-minVal">min. <span className="dataVal">{minVal}°C</span></span>
                    <span className="thermometer-amount" style={{ height: `${currPerc}%` }}></span>
                  </span>
                  <div className="thermometer-bulb">
                    <span className="thermometer-redCircle"></span>
                    <span className="thermometer-filler">
                      <span></span>
                    </span>
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

export default ThermometerCard;
