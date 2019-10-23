import React, { Component } from 'react'

import { getRepositoryLink } from '../../../util'

// To be removed once the Mapping Engine supports nested Function Blocks
export class DemoGaugeCard extends Component {
    render() {
        const values = this.props.feature.properties
        var unit = (values.status.unit) ? values.status.unit : ""
        if(unit === "second"){
            unit += "s"
        }
        const value = Math.round(values.status.value * 100) / 100

        const currVal = value || 0
        const minVal = -4
        const maxVal = 4

        const currDeg = ((currVal - minVal) * 180) / (maxVal - minVal)

        return (
            <div className='card card-stats attr-card'>
                <a href={getRepositoryLink(this.props.feature.definition)} rel="noopener noreferrer" target='_blank' >
                    <div className='header'>
                        {this.props.featureName}
                    </div>
                </a>

    


                <div className='inner-card-container'>
                <div className='gauge-container'>
                    <div className='gauge'>
                        <span className='gauge-min data-val'>{minVal}{` `}{unit}</span>
                        <span className='gauge-max data-val'>{maxVal}{` `}{unit}</span>
                        <div className='gauge-percentage' style={{ transform: `rotate(${currDeg}deg)` }} />

                        <span className='gauge-value data-val'>{`${currVal} ${unit}`}</span>
                         <div className='gauge-mask' />
                    </div>
                   </div>
                </div>

            </div>
        )
    }
}

export default DemoGaugeCard
