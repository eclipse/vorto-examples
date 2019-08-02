import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";

import { getRepositoryLink } from '../../util';

// To be removed once the Mapping Engine supports nested Function Blocks
export class DemoStateNumberCard extends Component {
  render() {
    const values = this.props.feature.properties;
    const state = values.status;

    const id = state.id;
    let timestamp = state.timestamp;
    const fontSize = id ? 5 : (timestamp ? 2 : 5);

    let textElement;

    if (timestamp) {
      timestamp = new Date(timestamp * 1000).toISOString().slice(0, 19).split('T');

      textElement = <div>
        <span className="dataVal">{timestamp[0]}</span>
        <br />
        <span className="dataVal">{timestamp[1]}</span>
      </div>

    } else if (id) {
      textElement = <span className="dataVal">{id}</span>;
    } else {
      textElement = <span className="dataVal">0</span>;
    }

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
                  fontSize: `${fontSize}em`,
                  textAlign: "center"
                }}>
                  {textElement}
                </span>
              </div>
            </Col>
          </Row>
        </div>
      </div>
    );
  }
}

export default DemoStateNumberCard;
