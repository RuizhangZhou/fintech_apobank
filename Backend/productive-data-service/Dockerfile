FROM openjdk:14-jdk-alpine
WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY target/*.jar ./data-service.jar
ENTRYPOINT [ "java", "-jar", "./data-service.jar" ]
