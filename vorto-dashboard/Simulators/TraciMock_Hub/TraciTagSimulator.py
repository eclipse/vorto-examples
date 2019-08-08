import os
import csv
import sys
import logging
import netifaces
import random
import time
import paho.mqtt.client as mqtt
import datetime, threading, time
import model.functionblock.Battery as battery 
import model.functionblock.Geolocation as location 
import model.functionblock.Acceleration as acceleration 
import model.functionblock.Temperature as temperature 
import model.functionblock.MagneticStrength as magneticStrength 
import model.functionblock.Connectivity as bluetoothConnectivity 
import model.functionblock.Connectivity as lorawanConnectivity 
import model.infomodel.Traci as Traci
import model.DittoSerializer as DittoSerializer

# Time in seconds for simulator to run
TIME_TO_LIVE = int(os.getenv('TIME_TO_LIVE', 60))

# Period for publishing data to the MQTT broker in seconds
TIME_INTERVAL_TELEMETRY = int(os.getenv('TIME_INTERVAL_TELEMETRY', 5))

# Calculate number of times to send data
repeat_count = int(TIME_TO_LIVE/TIME_INTERVAL_TELEMETRY)

logging.basicConfig(level=logging.DEBUG)
logger = logging.getLogger(__name__)

# Create a serializer for the MQTT payload from the Information Model
ser = DittoSerializer.DittoSerializer()

# DEVICE CONFIG GOES HERE
########################### Fix this
tenantId = os.getenv('TENANT_ID')
device_password = os.getenv('DEVICE_PASSWORD')
hub_adapter_host = "mqtt.bosch-iot-hub.com"
certificatePath = "./iothub.crt"

class Device(object):
    location_dataset = []
    publishTopic = None
    client = None
    username = None

    # Initialization of Information Model
    infomodel = None

    # constructor
    def __init__(self, deviceId, clientId=None, ditto_topic=None, authId=None, location_dataset_file=None):
        self.deviceId = deviceId
        self.clientId = clientId
        self.ditto_topic = ditto_topic
        self.authId = authId
        self.location_dataset_file = location_dataset_file
        self.prepare_location_data()
        # Configuration of client ID and publish topic	
        self.publishTopic = "telemetry/" + tenantId + "/" + deviceId
        # Create the MQTT client
        self.username = authId + "@" + tenantId
        
        self.client = mqtt.Client(clientId)
        self.client.username_pw_set(self.username, device_password)
        self.client.tls_set(certificatePath)


        self.client.enable_logger(logger)

        self.infomodel = Traci.Traci()

    # load and read co-ordinates for all devices
    # put content of csv file in list
    def prepare_location_data(self):
        print("device.location_dataset_file ", self.location_dataset_file)
        with open(self.location_dataset_file,'rt')as f:
            dataset_size = sum(1 for row in f)
        
        print("dataset size is: ", dataset_size)

        # store just enough coordinates to complete the simulation within the defined period        
        coordinates_to_skip = int(dataset_size / repeat_count)
        ctr = 1
        dataset = []
        with open(self.location_dataset_file,'rt')as f:
            data = csv.reader(f)
            for row in data:
                if(data.line_num == ctr):
                    dataset.append({
                        "latitude": row[0],
                        "longitude": row[1]
                    })
                    ctr+=coordinates_to_skip
                
        self.location_dataset = dataset    
        print("filtered dataset size is: ", len(self.location_dataset))

    # The functions to publish the functionblocks data
    def publishBattery(self):
        payload = ser.serialize_functionblock("battery", self.infomodel.battery, self.ditto_topic, self.deviceId)
        print("Publish Payload: ", payload, " to Topic: ", self.publishTopic)
        self.client.publish(self.publishTopic, payload)

    def publishLocation(self):
        payload = ser.serialize_functionblock("location", self.infomodel.location, self.ditto_topic, self.deviceId)
        print("Publish Payload: ", payload, " to Topic: ", self.publishTopic)
        self.client.publish(self.publishTopic, payload)

    def publishAcceleration(self):
        payload = ser.serialize_functionblock("acceleration", self.infomodel.acceleration, self.ditto_topic, self.deviceId)
        print("Publish Payload: ", payload, " to Topic: ", self.publishTopic)
        self.client.publish(self.publishTopic, payload)

    def publishTemperature(self):
        payload = ser.serialize_functionblock("temperature", self.infomodel.temperature, self.ditto_topic, self.deviceId)
        print("Publish Payload: ", payload, " to Topic: ", self.publishTopic)
        self.client.publish(self.publishTopic, payload)

    def publishMagneticStrength(self):
        payload = ser.serialize_functionblock("magneticStrength", self.infomodel.magneticStrength, self.ditto_topic, self.deviceId)
        print("Publish Payload: ", payload, " to Topic: ", self.publishTopic)
        self.client.publish(self.publishTopic, payload)

    def publishBluetoothConnectivity(self):
        payload = ser.serialize_functionblock("bluetoothConnectivity", self.infomodel.bluetoothConnectivity, self.ditto_topic, self.deviceId)
        print("Publish Payload: ", payload, " to Topic: ", self.publishTopic)
        self.client.publish(self.publishTopic, payload)

    def publishLorawanConnectivity(self):
        payload = ser.serialize_functionblock("lorawanConnectivity", self.infomodel.lorawanConnectivity, self.ditto_topic, self.deviceId)
        print("Publish Payload: ", payload, " to Topic: ", self.publishTopic)
        self.client.publish(self.publishTopic, payload)

    # The function that will be executed periodically once the connection to the MQTT broker was established
    def publish_data(self, location_index):

        ### BEGIN READING SENSOR DATA
        
        self.infomodel.battery.remainingCapacity = {
            "value" : 80
        }
        self.infomodel.battery.value = {
            "currentMeasured" : 80,
            "minMeasured" : 0,
            "maxMeasured" : 100
        }
        self.infomodel.battery.remainingCapacityAmpHour = 80
        
        self.infomodel.location.altitude = 0
        self.infomodel.location.latitude = self.location_dataset[location_index]["latitude"]
        self.infomodel.location.longitude = self.location_dataset[location_index]["longitude"]

        self.infomodel.acceleration.value = {
            "x" : 0,
            "y" : 0,
            "z" : 0
        }
        self.infomodel.temperature.value = {
            "currentMeasured" : random.randint(26,40),
            "minMeasured" : 26,
            "maxMeasured" : 40
        }
        self.infomodel.magneticStrength.value = {
            "x" : 0,
            "y" : 0,
            "z" : 0
        }
        self.infomodel.bluetoothConnectivity.rssi = 0
        self.infomodel.bluetoothConnectivity.snr = 0
        self.infomodel.bluetoothConnectivity.lastSeen = 0
        self.infomodel.bluetoothConnectivity.status = "Disconnected"
        self.infomodel.lorawanConnectivity.rssi = -75
        self.infomodel.lorawanConnectivity.snr = 24
        self.infomodel.lorawanConnectivity.lastSeen = 0
        self.infomodel.lorawanConnectivity.status = "Connected"

        ### END READING SENSOR DATA

        # Publish payload
        self.publishBattery()
        self.publishLocation()
        self.publishAcceleration()
        self.publishTemperature()
        self.publishMagneticStrength()
        self.publishBluetoothConnectivity()
        self.publishLorawanConnectivity()


device_list = []
device_list.append(Device("com.bosch.si.sgp:Truck-SG1021H", "com.bosch.si.sgp:Truck-SG1021H", \
                            "com.bosch.si.sgp/Truck-SG1021H", "com.bosch.si.sgp_Truck-SG1021H", \
                            "./changi2bosch.csv"))
device_list.append(Device("com.bosch.si.sgp:Truck-SG1022H", "com.bosch.si.sgp:Truck-SG1022H", \
                            "com.bosch.si.sgp/Truck-SG1022H", "com.bosch.si.sgp_Truck-SG1022H", \
                            "./tuas2bosch.csv"))
device_list.append(Device("com.bosch.si.sgp:Truck-SG1023H", "com.bosch.si.sgp:Truck-SG1023H", \
                            "com.bosch.si.sgp/Truck-SG1023H", "com.bosch.si.sgp_Truck-SG1023H", \
                            "./tanjong2bosch.csv"))

# Setup connections
for device in device_list:
    # Connect to the MQTT broker
    device.client.connect(hub_adapter_host, 8883, 60)

# Send data
for i in range(repeat_count):
    for device in device_list:
        device.publish_data(i)
    time.sleep(TIME_INTERVAL_TELEMETRY)
    if(i == (repeat_count-1)):
        sys.exit(0)


