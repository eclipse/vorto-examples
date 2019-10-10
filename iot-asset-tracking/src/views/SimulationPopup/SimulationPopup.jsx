import React from 'react';
import request from 'request-promise-native'
import { Button } from 'react-bootstrap'


const PORT = process.env.REACT_APP_PORT || 8080

const log = require('loglevel')
log.setLevel(process.env.LOG_LEVEL || 'error')

const { store } = require('../../store')


/// simulation


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

class SimulationPopup extends React.Component {

    SimulatorButton = (disableCond) => {
        return <Button className='header-button' disabled={disableCond} onClick={() => runSimulatorState()}>
            Simulate things
          </Button>
    }


    render() {
        console.log("is", store.getState())

        const simulatorState = store.getState().simulator
        const stateColor = simulatorState.running ? '#a2f260' : '#f26d60'
        const imgSource = simulatorState.running ? 'https://raw.githubusercontent.com/eclipse/vorto-examples/master/vorto-dashboard/assets/animation.gif'
            : 'https://raw.githubusercontent.com/eclipse/vorto-examples/master/vorto-dashboard/assets/vorto-simulator.png'
        const textInfo = simulatorState.running ? <span>Running - Started at {simulatorState.startTime}</span>
            : <span>Not Running</span>


        return (
            <div>
                <div onClick={this.props.closePopup} className='simulation-popup__background'>
                </div>
                <div className='simulation-popup__container'>
                    <button className="simulation-popup__close-button" onClick={this.props.closePopup}>
                        <span className="background-layer"></span>
                        <img
                            alt="close-icon"
                            src={this.props.closeIconSrc} />
                    </button>
                    <div className="simulation-popup__header">
                        <span>Simulate things</span>
                        <a href='https://github.com/eclipse/vorto-examples/tree/master/vorto-dashboard/docs/AssetTracking.md'
                            target='_blank' rel='noopener noreferrer' className='how-link'>How it works
                        </a>
                    </div>

                    <div className='simulation-popup__simulator-image'>
                        <img alt='simulator pipeline' src={imgSource} />
                    </div>

                    <div className='simulation-popup__simulator-elements'>

                        <div className='simulator-state' style={{
                            backgroundColor: `${stateColor}`
                        }} />
                        {textInfo}
                        <div className='simulator-buttons'>
                            {this.SimulatorButton(simulatorState.running)}
                        </div>
                    </div>
                </div>


            </div>
        );
    }
}

export default SimulationPopup;