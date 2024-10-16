FROM openjdk:11-jdk

ARG JAR_FILE=target/TestTaskFlex-1.0.0.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar", "--server.port=8080"]
