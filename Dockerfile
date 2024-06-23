FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/rh-0.0.1-SNAPSHOT.jar rh_app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "rh_app.jar"]