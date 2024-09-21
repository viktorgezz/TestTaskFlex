FROM openjdk:11-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ./target/TestTaskFlex-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
