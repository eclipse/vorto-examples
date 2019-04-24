let k1 = 5000;

function getDevices() {
    return [
        {
            "thingId": "org.eclipse.vorto.demo:123456789",
            "policyId": "org.eclipse.vorto.demo:123456789",
            "attributes": {
                "schema": {
                    "connectivity": "org.eclipse.vorto:Connectivity:1.0.0",
                    "contact": "org.eclipse.vorto:BinaryState:1.0.0",
                    "temperature": "org.eclipse.vorto:Temperature:1.0.0",
                    "voltage": "org.eclipse.vorto:Voltage:1.0.0",
                    "oledScreen": "com.bosch.si.egm:SSD1306OledScreen:1.0.0"
                },
                "createdBy": "S-1-5-21-1937855695-3964793637-879644401-644964",
                "thingName": "MyWemos32Oled",
                "definition": [
                    "com.bosch.si.egm:Wemos32Oled:1.0.0"
                ],
                "createdOn": "2019-03-25 03:50:22+0000",
                "deviceId": "org.eclipse.vorto.demo:123456789"
            },
            "features": {
                "temperature": {
                    "definition": [
                        "org.eclipse.vorto:Temperature:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "minMeasured": 0,
                                "currentMeasured": 0,
                                "maxMeasured": 0
                            }
                        }
                    }
                },
                "connectivity": {
                    "definition": [
                        "org.eclipse.vorto:Connectivity:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "rssi": 0,
                            "lastSeen": 0,
                            "snr": 0,
                            "status": {}
                        }
                    }
                },
                "contact": {
                    "definition": [
                        "org.eclipse.vorto:BinaryState:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "lastChange": "2019-03-25 03:50:22+0000",
                            "isTrue": false
                        }
                    }
                },
                "oledScreen": {
                    "definition": [
                        "com.bosch.si.egm:SSD1306OledScreen:1.0.0"
                    ],
                    "properties": {
                        "configuration": {
                            "screenWidth": 0,
                            "screenHeight": 0
                        }
                    }
                },
                "voltage": {
                    "definition": [
                        "org.eclipse.vorto:Voltage:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "minMeasured": 0,
                                "currentMeasured": 0,
                                "maxMeasured": 0
                            }
                        }
                    }
                }
            }
        },
        {
            "thingId": "org.eclipse.vorto.demo:754623",
            "policyId": "org.eclipse.vorto.demo:754623",
            "attributes": {
                "schema": {
                    "acceleration": "org.eclipse.vorto:Acceleration:1.0.0",
                    "magneticStrength": "org.eclipse.vorto:MagneticStrength:1.0.0",
                    "bluetoothConnectivity": "org.eclipse.vorto:Connectivity:1.0.0",
                    "lorawanConnectivity": "org.eclipse.vorto:Connectivity:1.0.0",
                    "temperature": "org.eclipse.vorto:Temperature:1.0.0",
                    "location": "org.eclipse.vorto:Geolocation:1.0.0",
                    "battery": "org.eclipse.vorto:Battery:1.0.0"
                },
                "createdBy": "S-1-5-21-1937855695-3964793637-879644401-644964",
                "thingName": "MyTraci",
                "definition": [
                    "com.bosch.ps:Traci:1.0.0"
                ],
                "createdOn": "2019-03-25 07:35:38+0000",
                "deviceId": "org.eclipse.vorto.demo:754623"
            },
            "features": {
                "magneticStrength": {
                    "definition": [
                        "org.eclipse.vorto:MagneticStrength:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "x": 0,
                                "y": 0,
                                "z": 0
                            }
                        }
                    }
                },
                "bluetoothConnectivity": {
                    "definition": [
                        "org.eclipse.vorto:Connectivity:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "rssi": 0,
                            "lastSeen": 0,
                            "snr": 0,
                            "status": {}
                        }
                    }
                },
                "acceleration": {
                    "definition": [
                        "org.eclipse.vorto:Acceleration:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "x": 0,
                                "y": 0,
                                "z": 0
                            }
                        }
                    }
                },
                "lorawanConnectivity": {
                    "definition": [
                        "org.eclipse.vorto:Connectivity:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "rssi": 0,
                            "lastSeen": 0,
                            "snr": 0,
                            "status": {}
                        }
                    }
                },
                "temperature": {
                    "definition": [
                        "org.eclipse.vorto:Temperature:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "minMeasured": 0,
                                "currentMeasured": 0,
                                "maxMeasured": 0
                            }
                        }
                    }
                },
                "location": {
                    "definition": [
                        "org.eclipse.vorto:Geolocation:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "altitude": 0
                        }
                    }
                },
                "battery": {
                    "definition": [
                        "org.eclipse.vorto:Battery:1.0.0"
                    ],
                    "properties": {
                        "configuration": {
                            "remainingCapacityAmpHour": 0
                        },
                        "status": {
                            "remainingCapacity": {
                                "value": 0
                            }
                        }
                    }
                }
            }
        },
        {
            "thingId": "org.eclipse.vorto.demo:1234123",
            "policyId": "org.eclipse.vorto.demo:1234123",
            "attributes": {
                "schema": {
                    "acceleration": "org.eclipse.vorto:Acceleration:1.0.0",
                    "magneticStrength": "org.eclipse.vorto:MagneticStrength:1.0.0",
                    "illuminance": "org.eclipse.vorto:Illuminance:1.0.0",
                    "connectivity": "org.eclipse.vorto:Connectivity:1.0.0",
                    "rotation": "org.eclipse.vorto:Rotation:1.0.0",
                    "acoustics": "org.eclipse.vorto:Acoustics:1.0.0",
                    "temperature": "org.eclipse.vorto:Temperature:1.0.0",
                    "humidity": "org.eclipse.vorto:Humidity:1.0.0",
                    "pressure": "org.eclipse.vorto:BarometricPressure:1.0.0",
                    "battery": "org.eclipse.vorto:Battery:1.0.0"
                },
                "createdBy": "S-1-5-21-1937855695-3964793637-879644401-644964",
                "thingName": "MyXDK",
                "definition": [
                    "com.bosch.bcds:XDK:2.0.0"
                ],
                "createdOn": "2019-04-04 05:13:54+0000",
                "deviceId": "org.eclipse.vorto.demo:1234123"
            },
            "features": {
                "magneticStrength": {
                    "definition": [
                        "org.eclipse.vorto:MagneticStrength:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "x": 0,
                                "y": 0,
                                "z": 0
                            }
                        }
                    }
                },
                "acceleration": {
                    "definition": [
                        "org.eclipse.vorto:Acceleration:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "x": 0,
                                "y": 0,
                                "z": 0
                            }
                        }
                    }
                },
                "illuminance": {
                    "definition": [
                        "org.eclipse.vorto:Illuminance:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "minMeasured": 0,
                                "currentMeasured": 0,
                                "maxMeasured": 0
                            }
                        }
                    }
                },
                "connectivity": {
                    "definition": [
                        "org.eclipse.vorto:Connectivity:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "rssi": 0,
                            "lastSeen": 0,
                            "snr": 0,
                            "status": {}
                        }
                    }
                },
                "rotation": {
                    "definition": [
                        "org.eclipse.vorto:Rotation:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "x": 0,
                                "y": 0,
                                "z": 0
                            }
                        }
                    }
                },
                "acoustics": {
                    "definition": [
                        "org.eclipse.vorto:Acoustics:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "minMeasured": 0,
                                "currentMeasured": 0,
                                "maxMeasured": 0
                            }
                        }
                    }
                },
                "temperature": {
                    "definition": [
                        "org.eclipse.vorto:Temperature:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "minMeasured": 0,
                                "currentMeasured": 0,
                                "maxMeasured": 0
                            }
                        }
                    }
                },
                "humidity": {
                    "definition": [
                        "org.eclipse.vorto:Humidity:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "minMeasured": {
                                    "value": 0
                                },
                                "currentMeasured": {
                                    "value": 0
                                },
                                "maxMeasured": {
                                    "value": 0
                                }
                            }
                        }
                    }
                },
                "pressure": {
                    "definition": [
                        "org.eclipse.vorto:BarometricPressure:1.0.0"
                    ],
                    "properties": {
                        "configuration": {
                            "seaLevel": 0
                        },
                        "status": {
                            "value": {
                                "minMeasured": 0,
                                "currentMeasured": 0,
                                "maxMeasured": 0
                            }
                        }
                    }
                },
                "battery": {
                    "definition": [
                        "org.eclipse.vorto:Battery:1.0.0"
                    ],
                    "properties": {
                        "configuration": {
                            "remainingCapacityAmpHour": 0
                        },
                        "status": {
                            "remainingCapacity": {
                                "value": 0
                            }
                        }
                    }
                }
            }
        },
        {
            "thingId": "org.eclipse.vorto.demo:1254345234534",
            "policyId": "org.eclipse.vorto.demo:1254345234534",
            "attributes": {
                "schema": {
                    "acceleration": "org.eclipse.vorto:Acceleration:1.0.0",
                    "magneticStrength": "org.eclipse.vorto:MagneticStrength:1.0.0",
                    "connectivity": "org.eclipse.vorto:Connectivity:1.0.0",
                    "batteryStatus": "org.eclipse.vorto:Battery:1.0.0",
                    "rotation": "org.eclipse.vorto:Rotation:1.0.0",
                    "acoustics": "org.eclipse.vorto:Acoustics:1.0.0",
                    "humidity": "org.eclipse.vorto:Humidity:1.0.0",
                    "pressure": "org.eclipse.vorto:BarometricPressure:1.0.0",
                    "ambientIlluminance": "org.eclipse.vorto:Illuminance:1.0.0",
                    "ambientTemperature": "org.eclipse.vorto:Temperature:1.0.0"
                },
                "createdBy": "S-1-5-21-1937855695-3964793637-879644401-644964",
                "thingName": "MyCISS",
                "definition": [
                    "com.bosch.bcds:CISS:1.0.0"
                ],
                "createdOn": "2019-04-05 03:18:08+0000",
                "deviceId": "org.eclipse.vorto.demo:1254345234534"
            },
            "features": {
                "magneticStrength": {
                    "definition": [
                        "org.eclipse.vorto:MagneticStrength:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "x": 0,
                                "y": 0,
                                "z": 0
                            }
                        }
                    }
                },
                "acceleration": {
                    "definition": [
                        "org.eclipse.vorto:Acceleration:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "x": 0,
                                "y": 0,
                                "z": 0
                            }
                        }
                    }
                },
                "connectivity": {
                    "definition": [
                        "org.eclipse.vorto:Connectivity:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "rssi": 0,
                            "lastSeen": 0,
                            "snr": 0,
                            "status": {}
                        }
                    }
                },
                "batteryStatus": {
                    "definition": [
                        "org.eclipse.vorto:Battery:1.0.0"
                    ],
                    "properties": {
                        "configuration": {
                            "remainingCapacityAmpHour": 0
                        },
                        "status": {
                            "remainingCapacity": {
                                "value": 0
                            }
                        }
                    }
                },
                "rotation": {
                    "definition": [
                        "org.eclipse.vorto:Rotation:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "x": 0,
                                "y": 0,
                                "z": 0
                            }
                        }
                    }
                },
                "acoustics": {
                    "definition": [
                        "org.eclipse.vorto:Acoustics:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "minMeasured": 0,
                                "currentMeasured": 0,
                                "maxMeasured": 0
                            }
                        }
                    }
                },
                "humidity": {
                    "definition": [
                        "org.eclipse.vorto:Humidity:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "minMeasured": {
                                    "value": 0
                                },
                                "currentMeasured": {
                                    "value": 0
                                },
                                "maxMeasured": {
                                    "value": 0
                                }
                            }
                        }
                    }
                },
                "pressure": {
                    "definition": [
                        "org.eclipse.vorto:BarometricPressure:1.0.0"
                    ],
                    "properties": {
                        "configuration": {
                            "seaLevel": 0
                        },
                        "status": {
                            "value": {
                                "minMeasured": 0,
                                "currentMeasured": 0,
                                "maxMeasured": 0
                            }
                        }
                    }
                },
                "ambientIlluminance": {
                    "definition": [
                        "org.eclipse.vorto:Illuminance:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "minMeasured": 0,
                                "currentMeasured": 0,
                                "maxMeasured": 0
                            }
                        }
                    }
                },
                "ambientTemperature": {
                    "definition": [
                        "org.eclipse.vorto:Temperature:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "minMeasured": 0,
                                "currentMeasured": 0,
                                "maxMeasured": 0
                            }
                        }
                    }
                }
            }
        },
        {
            "thingId": "org.eclipse.vorto.demo:231092",
            "policyId": "org.eclipse.vorto.demo:231092",
            "attributes": {
                "schema": {
                    "cpuTemperature": "org.eclipse.vorto:Temperature:1.0.0",
                    "location": "org.eclipse.vorto:Location:1.0.0",
                    "battery": "org.eclipse.vorto:Battery:1.0.0"
                },
                "createdBy": "S-1-5-21-1937855695-3964793637-879644401-644964",
                "thingName": "MyRaspberryPiTutorial",
                "definition": [
                    "org.eclipse.vorto.tutorials:RaspberryPiTutorial:1.0.0"
                ],
                "createdOn": "2019-04-08 03:22:50+0000",
                "deviceId": "org.eclipse.vorto.demo:231092"
            },
            "features": {
                "cpuTemperature": {
                    "definition": [
                        "org.eclipse.vorto:Temperature:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "value": {
                                "minMeasured": 27.4,
                                "currentMeasured": Math.round((Math.random() * 10 + 45) * 10) / 10,
                                "maxMeasured": 56.3
                            }
                        }
                    }
                },
                "location": {
                    "definition": [
                        "org.eclipse.vorto:Location:1.0.0"
                    ],
                    "properties": {
                        "status": {
                            "latitude": 0,
                            "longitude": 0
                        }
                    }
                },
                "battery": {
                    "definition": [
                        "org.eclipse.vorto:Battery:1.0.0"
                    ],
                    "properties": {
                        "configuration": {
                            "remainingCapacityAmpHour": 0
                        },
                        "status": {
                            "remainingCapacity": {
                                "value": Math.round(0.017 * k1--)
                            }
                        }
                    }
                }
            }
        }
    ]
}

module.exports = getDevices