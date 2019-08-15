import React, { Component } from 'react'
import { Row, Col } from 'react-bootstrap'

import { getRepositoryLink } from '../../util'

export class ConnectivityCard extends Component {
  render () {
    const feature = this.props.feature
    const featureName = this.props.featureName
    const featureStatus = feature.properties.status

    const connected = featureStatus.status === 'Connected'
    const connecting = featureStatus.status === 'Connecting'
    const cloudColor = connected ? '#a2f260' : connecting ? '#f9f963' : '#333333'
    const connectionText = connected ? 'Connected' : connecting ? 'Connecting...' : 'Offline'
    const connectionSub = (!connected && !connecting) ? `Last Seen: ${featureStatus.lastSeen}`
      : `SNR: ${featureStatus.snr}`

    return (
      <div className='card card-stats attr-card'>
        <div className='content'>
          <Row>
            <Col xs={12} sm={12} md={12} lg={12}>
              <div className='text-center'>
                <h4>
                  <a href={getRepositoryLink(feature.definition)} target='_blank' >{featureName}</a>
                </h4>
              </div>
            </Col>
          </Row>

          <Row>
            <Col xs={12} sm={12} md={12} lg={12}>
              <div className='inner-card-container'>
                <div className='connectivity-container'>
                  <span className='fa fa-cloud connectivity' style={{ color: cloudColor }} />
                  <div className='data-val connectivity-state'>{connectionText}</div>
                  <div className='connectivitySub'>{connectionSub}</div>
                </div>
              </div>
            </Col>
          </Row>
        </div>
      </div>
    )
  }
}

export default ConnectivityCard
