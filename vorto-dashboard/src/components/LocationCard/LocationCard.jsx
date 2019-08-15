import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";
import OSMap from "../OSMap/OSMap";

import { getRepositoryLink } from '../../util';

export class LocationCard extends Component {
  render() {
    return (
      <div className="card card-stats attr-card">
        <div className="content">
          <Row>
            <Col xs={12} sm={12} md={12} lg={12}>
              <div className="text-center">
                <h4>
                  <a href={getRepositoryLink(this.props.device.features[this.props.featureName].definition)}
                    target="_blank" >{this.props.featureName}</a>
                </h4>
              </div>
            </Col>
          </Row>

          <Row>
            <Col xs={12} sm={12} md={12} lg={12} className="map-card">
              <OSMap devices={[this.props.device]} displayTooltip={false} />
            </Col>
          </Row>
        </div>
      </div>
    );
  }
}

export default LocationCard;
