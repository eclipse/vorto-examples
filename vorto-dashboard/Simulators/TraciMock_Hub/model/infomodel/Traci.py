# com.bosch.ps.Traci generated by Vorto Python MQTT Generator

import model.functionblock.Battery as battery
import model.functionblock.Geolocation as location
import model.functionblock.Acceleration as acceleration
import model.functionblock.Temperature as temperature
import model.functionblock.MagneticStrength as magneticStrength
import model.functionblock.Connectivity as bluetoothConnectivity
import model.functionblock.Connectivity as lorawanConnectivity

class Traci(object):
    def __init__(self):
        self.battery = battery.Battery()
        self.location = location.Geolocation()
        self.acceleration = acceleration.Acceleration()
        self.temperature = temperature.Temperature()
        self.magneticStrength = magneticStrength.MagneticStrength()
        self.bluetoothConnectivity = bluetoothConnectivity.Connectivity()
        self.lorawanConnectivity = lorawanConnectivity.Connectivity()
