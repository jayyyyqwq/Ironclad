# Stage 1: Build stage
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the parent pom and the module pom
COPY pom.xml .
COPY gateway-service/pom.xml gateway-service/
COPY sink-service/pom.xml sink-service/

# Build only the gateway-service
COPY gateway-service/src gateway-service/src
RUN mvn -f gateway-service/pom.xml clean package -DskipTests

# Stage 2: Runtime stage
FROM ubuntu:22.04

# Install OpenJDK 17 Runtime
RUN apt-get update && \
    apt-get install -y openjdk-17-jre-headless && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy the built artifact from the build stage
COPY --from=build /app/gateway-service/target/gateway-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Environment variables for Kafka configuration (can be overridden in docker-compose)
ENV KAFKA_BOOTSTRAP_SERVERS=kafka:29092

ENTRYPOINT ["java", "-jar", "app.jar"]
