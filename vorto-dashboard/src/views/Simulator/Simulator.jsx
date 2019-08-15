import React from 'react'
import { Button } from 'react-bootstrap'
import { connect } from 'react-redux'
import request from 'request-promise-native'
const log = require('loglevel')
log.setLevel(process.env.LOG_LEVEL || 'error')

const PORT = process.env.REACT_APP_PORT || 8080

const mapStateToProps = state => {
  return {
    simulatorState: state.simulator
  }
}

const getSimulatorReqOpts = () => ({
  url: `http://${window.location.hostname}:${PORT}/api/v1/simulator`,
  method: 'POST'
})

const runSimulatorState = () => {
  request(getSimulatorReqOpts())
    .then(res => {
      const { started, error } = res

      if (error) {
        log.error(`Could not update simulator state... ${error}`)
      } else if (started) {
        log.info('Successfully started Simulators')
      }
    })
    .catch(err => log.error(`Could not update simulator state... ${err}`))
}

const SimulatorButton = (disableCond, text) => {
  return <Button onClick={() => runSimulatorState()} disabled={disableCond}>{text}</Button>
}

const ConnectedSimulator = ({ simulatorState }) => {
  const stateColor = simulatorState.running ? '#a2f260' : '#f26d60'
  const imgSource = simulatorState.running ? 'https://raw.githubusercontent.com/timgrossmann/vorto-examples/fix/simulator-visible/vorto-dashboard/assets/animation.gif'
    : 'https://raw.githubusercontent.com/timgrossmann/vorto-examples/fix/simulator-visible/vorto-dashboard/assets/vorto-simulator.png'
  const textInfo = simulatorState.running ? <span>Running - Started at {simulatorState.startTime}</span>
    : <span>Not Running</span>

  return (
    <div className='content'>
      <div className='simulator-container'>
        <div className='simulator-image'>
          <img alt='simulator pipeline' src={imgSource} />
        </div>
        <div className='simulator-elements'>
          <div className='simulator-state' style={{
            backgroundColor: `${stateColor}`
          }} />
          {textInfo}
          <div className='simulator-buttons'>
            {SimulatorButton(simulatorState.running, 'Run Simulation')}
          </div>
        </div>
      </div>
    </div >
  )
}

const Simulator = connect(mapStateToProps)(ConnectedSimulator)

export default Simulator
