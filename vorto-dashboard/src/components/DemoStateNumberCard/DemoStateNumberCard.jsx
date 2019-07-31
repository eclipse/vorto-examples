import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";

import { getRepositoryLink } from '../../util';

// To be removed once the Mapping Engine supports nested Function Blocks
export class DemoStateNumberCard extends Component {
  render() {
    const values = this.props.feature.properties;
    const state = values.status;

    const id = state.id;
    const timestamp = state.timestamp;
    const currVal = id ? id : (timestamp ? timestamp : 0);
    const fontSize = id ? 5 : (timestamp ? 3 : 5);

    return (
      <div className="card card-stats attrCard">
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
              <div className="innerCardContainer">
                <span style={{
                  fontSize: `${fontSize}em`
                }}><span className="dataVal">{currVal}</span></span>
              </div>
            </Col>
          </Row>
        </div>
      </div>
    );
  }
}

export default DemoStateNumberCard;
