import React, { Component } from 'react'

import { getRepositoryLink } from '../../../util'

// To be removed once the Mapping Engine supports nested Function Blocks
export class DemoStateNumberCard extends Component {
    render() {
        const values = this.props.feature.properties
        const state = values.status

        const id = state.id
        let timestamp = state.timestamp
        const fontSize = id ? 5 : (timestamp ? 1.4 : 5)

        let textElement

        if (timestamp) {
            timestamp = new Date(timestamp * 1000).toISOString().slice(0, 19).split('T')

            textElement = <div>
                <span className='data-val'>{timestamp[0]}</span>
                <br />
                <span className='data-val'>{timestamp[1]}</span>
            </div>

        } else if (id) {
            textElement = <span className='data-val'>{id}</span>
        } else {
            textElement = <span className='data-val'>0</span>
        }

        return (
            <div className='card card-stats attr-card'>

                <a href={getRepositoryLink(this.props.feature.definition)} rel="noopener noreferrer" target='_blank' >
                    <div className='header'>
                        {this.props.featureName}
                    </div>
                </a>
                <div className='state-number-container'>

                    <span style={{
                        fontSize: `${fontSize}em`,
                        textAlign: 'center'
                    }}>
                        {textElement}
                    </span>
                </div>


            </div>
        )
    }
}

export default DemoStateNumberCard
