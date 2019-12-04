
# middleware service
FROM openjdk:8-jre-alpine AS middleware-service
COPY target/middleware-service-1.0.0.jar middleware-service.jar
RUN apk update && apk add bash
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/middleware-service.jar"]
CMD ["/bin/sh"]
EXPOSE 8080