import React, { Component } from 'react'
import OSMapWidget from '../../../components/OSMap/OSMapWidget'

import { getRepositoryLink } from '../../../util'

export class LocationCard extends Component {
    render() {
        return (
            <div className='card card-stats attr-card'>
                <a href={getRepositoryLink(this.props.device.features[this.props.featureName].definition)} rel="noopener noreferrer" target='_blank' >
                    <div className='header'>
                        {this.props.featureName}
                    </div>
                </a>
                <div className="card-map">
                    <OSMapWidget devices={[this.props.device]} displayTooltip={false} />
                </div>
            </div>
        )
    }
}

export default LocationCard