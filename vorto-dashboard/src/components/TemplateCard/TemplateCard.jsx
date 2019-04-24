import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";

import { getRepositoryLink } from '../../util';

export class TemplateCard extends Component {
  render() {
    // TODO - Unpack passed props here
    // this.props.featureName - holds the given name of the feature
    // this.props.feature - holdsthe JSON for the current feature

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
                {/* TODO - Create to be displayed information here */}
              </div>
            </Col>
          </Row>
        </div>
      </div>
    );
  }
}

export default TemplateCard;
