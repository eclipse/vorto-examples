import React, { Component } from 'react'

import { getRepositoryLink } from '../../../util'


export class PercentageCard extends Component {
  render() {
    const values = this.props.feature.properties
    const currVal = values.status.value.currentMeasured.value || 0
    const minVal = 0
    const maxVal = 100

    const currPerc = ((currVal - minVal) * 100) / (maxVal - minVal)

    return (
      <div className='card card-stats attr-card'>

        <a href={getRepositoryLink(this.props.feature.definition)} rel="noopener noreferrer" target='_blank' >
          <div className='header'>
            {this.props.featureName}
          </div>
        </a>

        <div className='inner-card-container'>
          <div className='percentage-container'>
            <div className='percentage'>
              <div className='percentage-background' style={{
                height: `${currPerc}%`
              }} />
            </div>
            <div className='percentage-percentage'>
              <span className='data-val'>{currVal}%</span>
            </div>
          </div>
        </div>

      </div>
    )
  }
}

export default PercentageCard
