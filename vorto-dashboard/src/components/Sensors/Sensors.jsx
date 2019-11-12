import React, { Component } from 'react'
import { pollThings } from '../../util/DataPoller'
import log from 'loglevel'
import { Row, Col } from 'react-bootstrap'
import Actions from '../../actions'
import CodeCard from '../../views/Device/Cards/CodeCart'
import BarChart3Card from '../../views/Device/Cards/BarChart3Card'
import BatteryCard from '../../views/Device/Cards/BatteryCard'
import ConnectivityCard from '../../views/Device/Cards/ConnectivityCard'
import LocationCard from '../../views/Device/Cards/LocationCard'
import ThermometerCard from '../../views/Device/Cards/ThermometerCard'
import GaugeCard from '../../views/Device/Cards/GaugeCard'
import PercentageCard from '../../views/Device/Cards/PercentageCard'
import ImageCard from '../../views/Device/Cards/ImageCard'

// to be removed
import DemoThermometerCard from '../../views/Device/Cards/DemoThermometerCard'
import DemoStateNumberCard from '../../views/Device/Cards/DemoStateNumberCard'
import DemoGaugeCard from '../../views/Device/Cards/DemoGaugeCard'

import {
    CATEGORIES,
    mapDeftoCardCategorie
} from '../../util/cardUtils'

const { store } = require('../../store')

const mapCategorieToCard = (categorieType, device, featureObj, featureName) => {
    switch (categorieType) {
        case CATEGORIES.GAUGE:
            return (
                <GaugeCard
                    featureName={featureName}
                    feature={featureObj} />
            )
        case CATEGORIES.BAR3CHART:
            return (
                <BarChart3Card
                    featureName={featureName}
                    feature={featureObj} />
            )
        case CATEGORIES.BATTERY:
            return (
                <BatteryCard
                    featureName={featureName}
                    feature={featureObj} />
            )
        case CATEGORIES.CONNECTION:
            return (
                <ConnectivityCard
                    featureName={featureName}
                    feature={featureObj} />)
        case CATEGORIES.LOCATION:
            return (
                <LocationCard
                    featureName={featureName}
                    device={device} />
            )
        case CATEGORIES.IMAGE:
            return (
                <ImageCard
                    featureName={featureName}
                    feature={featureObj} />
            )
        case CATEGORIES.TEMPERATURE:
            return (
                <ThermometerCard
                    featureName={featureName}
                    feature={featureObj} />
            )
        case CATEGORIES.PERCENTAGE:
            return (
                <PercentageCard
                    featureName={featureName}
                    feature={featureObj} />
            )
        // To be removed once the Mapping Engine supports nested Function Blocks
        case CATEGORIES.DEMO_TEMPERATURE:
            return (
                <DemoThermometerCard
                    featureName={featureName}
                    feature={featureObj} />)
        case CATEGORIES.DEMO_STATE_NUMBER:
            return (
                <DemoStateNumberCard
                    featureName={featureName}
                    feature={featureObj} />)
        case CATEGORIES.DEMO_GAGE:
            return (
                <DemoGaugeCard
                    featureName={featureName}
                    feature={featureObj} />)
        default:
            return (
                <CodeCard
                    featureName={featureName}
                    feature={featureObj} />
            )

    }
}

log.setLevel(process.env.REACT_APP_LOG_LEVEL || 'debug')
const DEVICE_REFRESH_MS = process.env.REACT_APP_DEVICE_REFRESH_MS || 5000
// const PAGE_REFRESH_MS = 0




function pollDevices() {
    pollThings()
        .then(things => {
            this.setState({
                things
            })
        })
        .catch(err => `Could not poll devices... ${err}`)
    this.refreshSelectedDevice()
    this.dispatchDevices()

}


export class Sensors extends Component {
    state = {
        things: [],
        selectedDevice: store.getState().selectedDevice
    }

    componentDidMount() {
        this.unsubscribe = store.subscribe(() => { this.refreshSelectedDevice() })
        this.pollThingInterval = setInterval(pollDevices.bind(this), DEVICE_REFRESH_MS)

    }

    componentWillUnmount() {
        clearInterval(this.pollThingInterval)
        this.unsubscribe()
    }



    refreshSelectedDevice() {
        const thingId = store.getState().selectedDevice.thingId
        const things = this.state.things
        if (things.length > 0) {
            this.setState({ selectedDevice: this.state.things.find(x => x.thingId === thingId) })
        } else {
            this.setState({ selectedDevice: store.getState().selectedDevice })
        }
    }

    dispatchDevices() {
        const devices = this.state.things
        if (devices.length > 0) {
            store.dispatch(Actions.updateDevices(devices, '', []))
        }
    }


    render() {
        var sensors = {}
        const device = this.state.selectedDevice

        if (device) {
            sensors = (device.features === undefined) ? {}
                : Object.keys(device.features)
                    .sort()
                    .map((featureName, index) => {
                        const featureObj = device.features[featureName]
                        const featureDefs = featureObj.definition

                        const categorieType = mapDeftoCardCategorie(featureDefs)

                        if (categorieType === CATEGORIES.NO_WIDGET) {
                            return null
                        }

                        const featureCard = mapCategorieToCard(categorieType, device, featureObj, featureName)

                        return (<Col xs={12} sm={6} md={6} lg={4} key={index}>{featureCard}</Col>)
                    })
        }
        else {
            return <Col xs={12} sm={6} md={6} lg={4}> <h2>Select an asset </h2> </Col>
        }

        return (
            <Row>
                {sensors}
            </Row>
        )
    }
}

export default Sensors
