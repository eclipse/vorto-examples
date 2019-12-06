
  

  

  

# Getting Started with the Eclipse Vorto Semantic Normalizer middleware

  

  

  

  

The Eclipse Vorto Semantic Normalizer middleware is a lightweight, stateless micro service that receives any device telemetry data from [Eclipse Hono](https://www.eclipse.org/hono) and normalizes it according to [Vorto Information Models](https://github.com/eclipse/vorto/blob/master/docs/vortolang-1.0.md). Northbound IoT applications can leverage the semantics of the normalized data, e.g. for data analytics.

  

  

  

  

The service provides an extension point API lets you easily implement custom logic to process the normalized device payload, such as storing it in a Digital Twin service or run analytics on the data.

  

  

  

  

The service supports the publish of normalized data to an AMQP message broker, e.g. Amazon MQ.

  

  

  

  

Please follow [this link](https://github.com/eclipse/vorto/blob/development/docs/tutorials/create_mapping_pipeline.md) to use the Vorto Normalizer middleware for a simple IoT geolocation use case.

  

  

  

  

![](overview.png)

  

  

  

  

## Vorto Normalizer Frontend

  

  

  

  

The Eclipse Vorto Middleware Frontend is an additional lightweight Angular 8 application, able to visualize the in-and outbounding device payload as well as listing the configured plugins. You can see a running example of what to expect under [this link](http://vorto-middleware.eu-central-1.elasticbeanstalk.com/).

  

  

  

  

![](frontend.png)

  

  

  

  

# Running the middleware service

  

  

  

You can run the Vorto Normalizer service and frontend out of the box via **Docker**. Thus, getting started is as easy as downloading each of the container from the docker hub and running them as described in the following:

  

  

  

## **Running the Vorto Normalizer Service**

  

**Downloading the image:**

  

 - Pull the image from docker hub:

  

	`docker pull eclipsevorto/vorto-normalizer:nightly`

  
**Running the container:**

  

To run the middleware, you need to set the following environment variables:

  

*  **-e hono.tenantId=**  _Eclipse Hono tenant ID, for receiving device telemetry messages_

  

*  **-e hono.password=**  _Eclipse Hono messaging password, for receiving device telemetry messages_

  

*  **-e amqp.url=**  _AMQP 1.0 Broker url, for the middleware to publish normalized device payload to_

  

*  **-e amqp.username=**  _AMQP 1.0 Broker username for authentication_

  

*  **-e amqp.password=**  _AMQP 1.0 Broker password for authentication_

  

*  **-e cors=**  _Cross-Origin Resource Sharing (CORS) url, optional for connection to e.g the middleware ui_

  

  - Start the container via e.g.:

 
`docker run -it -p 8080:8080 -e hono.tenantId=your_tenantId -e hono.password=your_hono_password -e amqp.url=amqp_url -e amqp.username=amqp_username -e amqp.password=amqp_password -e cors=http://localhost:4200 eclipsevorto/vorto-normalizer:nightly`

  
  

**Adding your mapping specifications to the middleware**


In order to configure the normalizer middleware with your mapping specifications, you can simply mount a docker volume via `-v` and then point the middleware to this mounted directory, where it can find all mappings. 

The following variables need to be passed when running the middleware docker container:
 

*  **-v /absolute_local_directory_path:/directory_path_on_image**


**The docker volume parameter `-v /absolute_local_directory_path:/directory_path_on_image` defines two paths separated by a colon: On the one side an absolute path for the local directory (left side) and on the other side a non absolute folder name (right side) which has to be the same value as set in the environment variable `mapping_spec_dir` described in the following.**
  

*  **-e mapping_spec_dir=**  _Path in the docker container, including custom mapping specifications as defined on the right side of the volume parameter (`directory_path_on_image`) in the previous command. It can e.g be named to /customspecs_.

  

**Please note that `absolute_local_directory_path` has to be an absolute path in respect to the directory and operating system you are running the `docker run` command in.**


Here is an example how we can mount a volume with the directory mappings when running docker run:


    docker run -it -v //C/absolute_local_dir:/customspecs -p 8080:8080 -e mapping_spec_dir=/customspecs -e hono.tenantId=your_tenantId -e hono.password=your_hono_password -e amqp.url=amqp_url -e amqp.username=amqp_username -e amqp.password=amqp_password -e cors=http://localhost:4200 eclipsevorto/vorto-normalizer:nightly

  
  
  
  
  

## **Running the middleware frontend**

  

**Downloading the image:**

  - Pull the image from docker hub:
`docker pull eclipsevorto/vorto-normalizer-ui:nightly`

  

**Running the container:**

  - Start the container via:
	`docker run -p 4200:4200 eclipsevorto/vorto-normalizer-ui:nightly`

  

  

  

## What's next ?

  

  

  

  

  

*  [Read this tutorial](https://github.com/eclipse/vorto/blob/development/docs/tutorials/create_mapping_pipeline.md), which uses the Vorto Semantic Middleware for an IoT Geolocation use case.

* Read more about the [Vorto Mapping Engine](https://github.com/eclipse/vorto/blob/development/mapping-engine/Readme.md), the core of the Vorto Semantic Middleware