import React, { Component } from 'react'
import { Row, Col } from 'react-bootstrap'

import { getRepositoryLink } from '../../util'

export class StateNumberCard extends Component {
  render () {
    const values = this.props.feature.properties
    const state = values.status.digitalInputState || false
    const currVal = values.status.digitalInputCounter || 0

    const stateDisplay = state ? <span className='fa fa-bell state-number-icon' />
      : <span className='fa fa-bell state-number-icon state-number-iconOff' />

    return (
      <div className='card card-stats attr-card'>
        <div className='content'>
          <Row>
            <Col xs={12} sm={12} md={12} lg={12}>
              <div className='text-center'>
                <h4>
                  <a href={getRepositoryLink(this.props.feature.definition)} target='_blank' >{this.props.featureName}</a>
                </h4>
              </div>
            </Col>
          </Row>

          <Row>
            <Col xs={12} sm={12} md={12} lg={12}>
              <div className='inner-card-container'>
                {stateDisplay}
                <span className='state-number-val'><span className='data-val'>{currVal}</span></span>
              </div>
            </Col>
          </Row>
        </div>
      </div>
    )
  }
}

export default StateNumberCard
