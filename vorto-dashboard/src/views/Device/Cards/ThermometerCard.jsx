import React, { Component } from 'react'

import { getRepositoryLink } from '../../../util'

export class ThermometerCard extends Component {
    render() {
        // TODO adjust retrieval of attributes to be smarter
        // (Get the definition of the function blocks and find attribute values)
        const values = this.props.feature.properties
        const currVal = Math.round(values.status.value.currentMeasured * 100) / 100 || 0
        const minVal = Math.round(values.status.value.minMeasured * 100) / 100 || 0
        const maxVal = Math.round(values.status.value.maxMeasured * 100) / 100 || currVal

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

export default ThermometerCard
