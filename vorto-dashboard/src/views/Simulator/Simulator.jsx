import React from "react";
import { Button } from "react-bootstrap";
import { connect } from "react-redux";

const mapStateToProps = state => {
  return {
    simulatorState: state.simulator,
  };
};

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
            <Button>Start Simulation</Button>
            <Button>Stop Simulation</Button>
          </div>
        </div>
      </div>
    </div>
  );
}

const Simulator = connect(mapStateToProps)(ConnectedSimulator);

export default Simulator;