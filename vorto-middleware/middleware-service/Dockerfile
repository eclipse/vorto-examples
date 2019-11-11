
# middleware service
FROM node:10 
FROM openjdk:8-jre-alpine AS middleware-service
COPY target/middleware-service-1.0.0.jar middleware-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/middleware-service.jar"]
EXPOSE 8080