import React, { Component } from 'react'

export class Spinner extends Component {
  render () {
    return (
      <div className='spinner-container'>
        <div className='spinner'>Loading...</div>
      </div>
    )
  }
}

export default Spinner
