FROM openjdk:11
ARG JAR_FILE=build/libs/customer.cmd-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=staging","-jar","app.jar"]