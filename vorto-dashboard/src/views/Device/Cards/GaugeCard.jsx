import React, { Component } from 'react'

import { getRepositoryLink } from '../../../util'

export class GaugeCard extends Component {
    render() {
        // TODO adjust retrieval of attributes to be smarter
        // (Get the definition of the function blocks and find attribute values)
        const values = this.props.feature.properties
        const currVal = values.status.value.currentMeasured || 0
        const minVal = values.status.value.minMeasured || 0
        const maxVal = values.status.value.maxMeasured || currVal

        const currDeg = ((currVal - minVal) * 180) / (maxVal - minVal)

        const fontSize = `${currVal}`.length > 4 ? 28 : 38

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
                        <span className='gauge-min data-val'>{minVal}</span>
                        <span className='gauge-max data-val'>{maxVal}</span>
                        <div className='gauge-percentage' style={{ transform: `rotate(${currDeg}deg)` }} />

                        <span className='gauge-value data-val' style={{
                            fontSize: `${fontSize}px`
                        }}>{currVal}</span>
                         <div className='gauge-mask' />
                    </div>
                   </div>
                </div>
            </div>
        )
    }
}

export default GaugeCard
