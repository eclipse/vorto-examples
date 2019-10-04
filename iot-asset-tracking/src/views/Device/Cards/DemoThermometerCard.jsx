import React, { Component } from 'react'

import { getRepositoryLink } from '../../../util'

// To be removed once the Mapping Engine supports nested Function Blocks
export class DemoThermometerCard extends Component {
    render() {
        const values = this.props.feature.properties
        const currVal = Math.round(values.status.value * 100) / 100 || 0
        const unit = values.status.unit
        const minVal = -5
        const maxVal = 5

        const currPerc = ((currVal - minVal) * 100) / (maxVal - minVal)

        return (
            <div className='card card-stats attr-card'>
                <a href={getRepositoryLink(this.props.feature.definition)} rel="noopener noreferrer" target='_blank' >
                    <div className='header'>
                        {this.props.featureName}
                    </div>
                </a>

                <div className='inner-card-container'>
                    <div className='thermometer'>
                        <span className='thermometer-glass'>
                            <span className='thermometer-maxVal'>max. <span className='data-val'>{maxVal}°C</span></span>
                            <span className='thermometer-minVal'>min. <span className='data-val'>{minVal}°C</span></span>
                            <div className='thermometer-amount' style={{ height: `${currPerc}%` }} >

                                <span className='thermometer-currVal'>
                                    <span className='data-val'>{currVal}°C</span>
                                </span>
                            </div>
                        </span>
                        <div className='thermometer-bulb'>
                            <span className='thermometer-redCircle' />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default DemoThermometerCard
