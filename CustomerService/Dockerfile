FROM eclipse-temurin:latest
LABEL maintainer="kpgohul@gmail.com"
WORKDIR /app
COPY target/customer-service-v1.jar /app/customer-service.jar
ENTRYPOINT["java", "-jar", "customer-service.jar"]