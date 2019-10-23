import React, { Component } from 'react'

import { getRepositoryLink } from '../../../util'
import { isNumber } from 'util'


export class PercentageCard extends Component {
  render() {
    const values = this.props.feature.properties
    let currVal = 0
    let minVal = 0
    let maxVal = 100
    if(values.status.value.currentMeasured){
       currVal = (typeof values.status.value.currentMeasured === 'number') ? values.status.value.currentMeasured : 0
       minVal = (typeof values.status.value.minMeasured === 'number') ? values.status.value.minMeasured : 0
       maxVal = (typeof values.status.value.maxMeasured === 'number') ? values.status.value.maxMeasured : 100


    }
    if(values.status.value.currentMeasured && values.status.value.currentMeasured.value){
       currVal =  (typeof values.status.value.currentMeasured.value === 'number') ? values.status.value.currentMeasured.value : currVal
       minVal = (typeof values.status.value.minMeasured.value === 'number') ? values.status.value.minMeasured.value : 0
       maxVal = (typeof values.status.value.maxMeasured.value === 'number') ? values.status.value.maxMeasured.value : 100
    }

  
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
