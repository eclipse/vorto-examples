const CATEGORIES = {
    LOCATION: [
        "org.eclipse.vorto:Location:1.0.0",
        "org.eclipse.vorto:Geolocation:1.0.0"
    ],
    GAGE: [
        "org.eclipse.vorto:Voltage:1.0.0",
        "org.eclipse.vorto:Illuminance:1.0.0",
        "org.eclipse.vorto:Acoustics:1.0.0",
        "org.eclipse.vorto:BarometricPressure:1.0.0"
    ],
    BAR3CHART: [
        "org.eclipse.vorto:MagneticStrength:1.0.0",
        "org.eclipse.vorto:Acceleration:1.0.0",
        "org.eclipse.vorto:Rotation:1.0.0"
    ],
    PERCENTAGE: [
        "org.eclipse.vorto:Humidity:1.0.0"
    ],
    TEMPERATURE: [
        "org.eclipse.vorto:Temperature:1.0.0"
    ],
    BATTERY: [
        "org.eclipse.vorto:Battery:1.0.0"
    ],
    STATE_NUMBER: [
        "com.ipso.smartobjects:Presence:1.1.0"
    ],
    IMAGE: [
        "org.eclipse.vorto:Image:1.0.0"
    ],
    CONNECTION: [
        "org.eclipse.vorto:Connectivity:1.0.0"
    ],
    NO_WIDGET: [
        "org.eclipse.vorto:DeviceInformation:1.0.0"
    ],
    JSON: [],

    // To be removed once the Mapping Engine supports nested Function Blocks
    DEMO_TEMPERATURE: [
        "org.eclipse.vorto.tutorial:Temperature:1.0.0"
    ],
    DEMO_STATE_NUMBER: [
        "org.eclipse.vorto.tutorial:ID:1.0.0",
        "org.eclipse.vorto.tutorial:UnixTimestamp:1.0.0"
    ],
    DEMO_GAGE: [
        "org.eclipse.vorto.tutorial:RotationalSpeed:1.0.0",
        "org.eclipse.vorto.tutorial:Torque:1.0.0"
    ]
}

function mapDeftoCardCategorie(featureDefs) {
    if (!featureDefs) {
        return CATEGORIES.JSON;
    }

    if (!Array.isArray(featureDefs)) {
        featureDefs = [featureDefs];
    }

    for (const definition of featureDefs) {
        if (CATEGORIES.LOCATION.includes(definition)) {
            return CATEGORIES.LOCATION
        }

        if (CATEGORIES.GAGE.includes(definition)) {
            return CATEGORIES.GAGE
        }

        if (CATEGORIES.BAR3CHART.includes(definition)) {
            return CATEGORIES.BAR3CHART
        }

        if (CATEGORIES.PERCENTAGE.includes(definition)) {
            return CATEGORIES.PERCENTAGE
        }

        if (CATEGORIES.TEMPERATURE.includes(definition)) {
            return CATEGORIES.TEMPERATURE
        }

        if (CATEGORIES.BATTERY.includes(definition)) {
            return CATEGORIES.BATTERY
        }

        if (CATEGORIES.STATE_NUMBER.includes(definition)) {
            return CATEGORIES.STATE_NUMBER
        }

        if (CATEGORIES.IMAGE.includes(definition)) {
            return CATEGORIES.IMAGE
        }

        if (CATEGORIES.CONNECTION.includes(definition)) {
            return CATEGORIES.CONNECTION
        }

        if (CATEGORIES.NO_WIDGET.includes(definition)) {
            return CATEGORIES.NO_WIDGET
        }


        // To be removed once the Mapping Engine supports nested Function Blocks
        if (CATEGORIES.DEMO_TEMPERATURE.includes(definition)) {
            return CATEGORIES.DEMO_TEMPERATURE
        }

        if (CATEGORIES.DEMO_STATE_NUMBER.includes(definition)) {
            return CATEGORIES.DEMO_STATE_NUMBER
        }

        if (CATEGORIES.DEMO_GAGE.includes(definition)) {
            return CATEGORIES.DEMO_GAGE
        }
    }

    return CATEGORIES.JSON
}

module.exports = {
    CATEGORIES,
    mapDeftoCardCategorie,
}