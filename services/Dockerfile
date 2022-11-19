FROM openjdk:8-jdk-alpine
MAINTAINER gabriel
COPY target/services-0.0.1-SNAPSHOT.jar services.jar
ENTRYPOINT ["java","-jar","/services.jar"]