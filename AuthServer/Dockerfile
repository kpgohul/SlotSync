FROM eclipse-temurin:latest
LABEL maintainer="kpgohul@gmail.com"
WORKDIR /app
COPY target/spring-auth-server-v1.jar /app/spring-auth-server.jar
ENTRYPOINT["java", "-jar", "spring-auth-server.jar"]