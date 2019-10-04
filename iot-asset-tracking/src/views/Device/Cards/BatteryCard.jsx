import React, { Component } from 'react'

import { getRepositoryLink } from '../../../util'

export class BatteryCard extends Component {
    render() {
        // TODO adjust retrieval of attributes to be smarter
        // (Get the definition of the function blocks and find attribute values)
        const values = this.props.feature.properties
        const currVal = values.status.remainingCapacity.value || 0
        const minVal = 0
        const maxVal = 100

        const currPerc = ((currVal - minVal) * 100) / (maxVal - minVal)
        const barColor = currPerc <= 20 ? '#f26d60' : currPerc <= 40 ? '#f9f963' : '#a2f260'

        return (
            <div className='card card-stats attr-card'>
                <a href={getRepositoryLink(this.props.feature.definition)} rel="noopener noreferrer" target='_blank' >
                    <div className='header'>
                        {this.props.featureName}
                    </div>
                </a>

                <div className='inner-card-container'>
                    <div className='battery-container'>
                        <div className='battery'>
                            <div className='battery-background' style={{
                                width: `${currPerc}%`,
                                backgroundColor: barColor
                            }} />
                        </div>
                        <div className='battery-percentage'>
                            <span className='data-val'>{currVal}%</span>
                        </div>
                    </div>
                </div>

            </div>
        )
    }
}

export default BatteryCard
