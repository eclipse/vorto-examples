import React from "react";
import { Button } from "react-bootstrap";
import { connect } from "react-redux";
import request from "request-promise-native";

const PORT = process.env.REACT_APP_PORT || 8080;

const mapStateToProps = state => {
  return {
    simulatorState: state.simulator,
  };
};

const getDeviceReqOpts = (reqBody) => ({
  url: `http://${window.location.hostname}:${PORT}/api/v1/simulator`,
  method: "POST",
  json: reqBody,
});

const setSimulatorState = (newSimulatorState) => {
  request(getDeviceReqOpts(newSimulatorState))
    .then(res => {
      console.log("success", res);
    })
    .catch(err => `Could not update simulator state with state ${newSimulatorState}... ${err}`)
}

const SimulatorButton = (onClickState, disableCond, text) => {
  return <Button onClick={() => setSimulatorState(onClickState)} disabled={disableCond}>{text}</Button>;
}

const ConnectedSimulator = ({ simulatorState }) => {
  const stateColor = simulatorState.running ? "#a2f260" : "#f26d60";
  const textInfo = simulatorState.running ? <span>Running - Started at {simulatorState.startTime}</span>
    : <span>Not Running</span>;

  return (
    <div className="content">
      <div className="simulator-container">
        <div className="simulator-elements">
          <div className="simulator-state" style={{
            backgroundColor: `${stateColor}`
          }} />
          {textInfo}
          <div className="simulator-buttons">
            {SimulatorButton({ running: true }, simulatorState.running, "Start Simulation")}
            {SimulatorButton({ running: false }, !simulatorState.running, "Stop Simulation")}
          </div>
        </div>
      </div>
    </div >
  );
}

const Simulator = connect(mapStateToProps)(ConnectedSimulator);

export default Simulator;