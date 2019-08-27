import React, { Component } from 'react'

export class KpiCard extends Component {
  render () {
    return (
      <div className='card card-stats attr-card'>
        <div className='content'>
          <div className='attr-container'>
            <p className='attribute'><span className='attr-keyword header-keyword'>Virtual Device Information</span></p>
            <p className='attribute'><span className='attr-keyword'>Thing ID: </span></p>
            <p className='attribute'>
              <span className='attr-keyword'>Definition: </span>
            </p>
          </div>
        </div>
      </div>
    )
  }
}

export default KpiCard
