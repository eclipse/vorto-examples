import React, { Component } from 'react'

import { getRepositoryLink } from '../../../util'

export class ConnectivityCard extends Component {
  render () {
    const feature = this.props.feature
    const featureStatus = feature.properties.status

    const connected = featureStatus.status === 'Connected'
    const connecting = featureStatus.status === 'Connecting'
    const cloudColor = connected ? '#a2f260' : connecting ? '#f9f963' : '#333333'
    const connectionText = connected ? 'Connected' : connecting ? 'Connecting...' : 'Offline'
    const connectionSub = (!connected && !connecting) ? `Last Seen: ${featureStatus.lastSeen}`
      : `SNR: ${featureStatus.snr}`

    return (
      <div className='card card-stats attr-card'>
        <a href={getRepositoryLink(this.props.feature.definition)} rel="noopener noreferrer" target='_blank' >
                    <div className='header'>
                        {this.props.featureName}
                    </div>
                </a>

        
              <div className='inner-card-container'>
                <div className='connectivity-container'>
                  <span className='fa fa-cloud connectivity' style={{ color: cloudColor }} />
                  <div className='data-val connectivity-state'>{connectionText}</div>
                  <div className='connectivitySub'>{connectionSub}</div>
                </div>
              </div>
      </div>
    )
  }
}

export default ConnectivityCard
