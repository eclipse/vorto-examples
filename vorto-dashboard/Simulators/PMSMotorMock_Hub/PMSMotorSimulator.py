import os
import csv
import datetime, threading, time
import json
import logging
import netifaces
import paho.mqtt.client as mqtt
import sys
from random import randint
from os import environ

# DEVICE CONFIG GOES HERE
tenantId = os.getenv('TENANT_ID')
device_password = os.getenv('DEVICE_PASSWORD')
hub_adapter_host = "mqtt.bosch-iot-hub.com"
deviceId ="com.bosch.si.sgp:PMSMotor-4"
clientId = deviceId
authId = "com.bosch.si.sgp_PMSMotor-4"
certificatePath = "./iothub.crt"
ditto_topic = "com.bosch.si.sgp/PMSMotor-4"


# put content of csv file in list
time_to_live = int(os.getenv("TIME_TO_LIVE", 60))
simulation_interval = int(os.getenv('TIME_INTERVAL_TELEMETRY', 5))

number_of_items = int(time_to_live / simulation_interval)

dataset = []
with open('./pmsm_temperature_data_modified.csv','rt')as f:
  data = csv.reader(f)
  for index, row in enumerate(data):
      if index >= number_of_items:
          break
          
      dataset.append({
          "ambient": row[0],
          "coolant": row[1],
          "motor_speed": row[2],
          "torque": row[3],
          "stator_yoke": row[4],
          "stator_tooth": row[5],
          "stator_winding": row[6],
          "profile_id": row[7],
      })

# Timer variable for periodic function
next_call = 0
# Period for publishing data to the MQTT broker in seconds
timePeriod = simulation_interval
current_element = 0
running = True
# Configuration of client ID and publish topic	
publishTopic = "telemetry/" + tenantId + "/" + deviceId

# Output relevant information for consumers of our information
print("Connecting client:    ", clientId)
print("Publishing to topic:  ", publishTopic)

# Create the MQTT client
client = mqtt.Client(clientId)

logging.basicConfig(level=logging.ERROR)
logger = logging.getLogger(__name__)
client.enable_logger(logger)

client.tls_set(certificatePath)

username = authId + "@" + tenantId
client.username_pw_set(username, device_password)

# The callback for when the client receives a CONNACK response from the server.
def on_connect(client, userdata, flags, rc):
    global next_call

    if rc != 0:
        print("Connection to MQTT broker failed: " + str(rc))
        return

    # Subscribing in on_connect() means that if we lose the connection and
    # reconnect then subscriptions will be renewed.

    # BEGIN SAMPLE CODE
    client.subscribe("commands/" + tenantId + "/")
    # END SAMPLE CODE

    # Time stamp when the periodAction function shall be called again
    next_call = time.time()

    
    # Start the periodic task for publishing MQTT messages
    periodicAction()


# The function that will be executed periodically once the connection to the MQTT broker was established
def periodicAction():
    global next_call
    global current_element
    global running

    """
        Example (csv):
        profile_id  timestamp       ambient    ...  stator_winding            
        1           1563356987      -1.2224282      -1.2224282
    """
    data_element = dataset[current_element]
    profile_id = data_element["profile_id"]
    curr_timestamp = int(time.time())

    list_payload = [profile_id, curr_timestamp]

    for attribute in data_element:
        if attribute == "profile_id":
            continue

        list_payload.append(data_element[attribute])

    payload = [str(item) for item in list_payload]
    payload = ','.join(payload)
    logger.info(payload)

    print("Publish Payload: ", payload, " to Topic: ", publishTopic)
    client.publish(publishTopic, payload)

    # check for exit
    current_element += 1
    if current_element == number_of_items:
        logger.debug("Simulator ending")
        running = False
        sys.exit(0)
    else: 
        # Schedule next call
        next_call = next_call + timePeriod
        threading.Timer(next_call - time.time(), periodicAction).start()


# Blocking call that processes network traffic, dispatches callbacks and
# handles reconnecting.
# Other loop*() functions are available that give a threaded interface and a
# manual interface.
client.on_connect = on_connect

# Connect to the MQTT broker
client.connect(hub_adapter_host, 8883, 60)

client.loop_start()

while (1):
    if running == True:
        pass
    else:
        sys.exit(0)
