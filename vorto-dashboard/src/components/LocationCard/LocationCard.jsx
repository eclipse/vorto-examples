import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";
import OSMap from "../OSMap/OSMap";

import { getRepositoryLink } from '../../util';

export class LocationCard extends Component {
  render() {
    return (
      <div className="card card-stats attrCard">
        <div className="content">
          <Row>
            <Col xs={12}>
              <div className="text-center">
                <h4>
                  <a href={getRepositoryLink(this.props.device.features[this.props.featureName].definition[0])}
                    target="_blank" >{this.props.featureName}</a>
                </h4>
              </div>
            </Col>
          </Row>

          <Row>
            <Col xs={12} className="mapCard">
              <OSMap devices={[this.props.device]} displayTooltip={false} />
            </Col>
          </Row>
        </div>
      </div>
    );
  }
}

export default LocationCard;
