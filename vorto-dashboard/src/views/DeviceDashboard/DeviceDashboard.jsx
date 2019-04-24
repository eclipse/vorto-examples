import React from "react";
import { Grid, Row, Col } from "react-bootstrap";
import { connect } from "react-redux";

import AttributesCard from "../../components/AttributesCard/AttributesCard.jsx"
import CodeCard from "../../components/CodeCard/CodeCard.jsx";
import LocationCard from "../../components/LocationCard/LocationCard";
import GaugeCard from "../../components/GaugeCard/GaugeCard";
import BatteryCard from "../../components/BatteryCard/BatteryCard";

import {
  CATEGORIES,
  mapDeftoCardCategorie
} from "../../util/cardUtils";
import ThermometerCard from "../../components/ThermometerCard/ThermometerCard";

const mapStateToProps = state => {
  return { device: state.selectedDevice };
};

const mapCategorieToCard = (categorieType, device, featureObj, featureName) => {
  switch (categorieType) {
    case CATEGORIES.GAGE:
      return (
        <GaugeCard
          featureName={featureName}
          feature={featureObj} />
      );
    case CATEGORIES.TEMPERATURE:
      return (
        <ThermometerCard
          featureName={featureName}
          feature={featureObj} />
      );

    case CATEGORIES.BATTERY:
      return (
        <BatteryCard
          featureName={featureName}
          feature={featureObj} />
      );
    case CATEGORIES.LOCATION:
      return (
        <LocationCard
          featureName={featureName}
          device={device} />
      );
    default:
      return (
        <CodeCard
          featureName={featureName}
          feature={featureObj} />
      );
  }
}

const ConnetedDeviceDashboard = ({ device }) => {
  const row = Object.keys(device.features)
    .map(featureName => {
      const featureObj = device.features[featureName]
      const featureDefs = featureObj.definition;

      const categorieType = mapDeftoCardCategorie(featureDefs);
      const featureCard = mapCategorieToCard(categorieType, device, featureObj, featureName);

      return (<Col lg={4} sm={6}>{featureCard}</Col>);
    });

  return (
    <div className="content">
      <Grid fluid>
        <Row>
          <Col md={12}>
            <AttributesCard
              device={device}
            />
          </Col>
        </Row>

        <Row>
          {row}
        </Row>
      </Grid>
    </div>
  );
}

const DeviceDashboard = connect(mapStateToProps)(ConnetedDeviceDashboard);

export default DeviceDashboard;
