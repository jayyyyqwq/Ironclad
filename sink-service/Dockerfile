# Stage 1: Build stage
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the parent pom and the module poms
COPY pom.xml .
COPY gateway-service/pom.xml gateway-service/
COPY sink-service/pom.xml sink-service/

# Build only the sink-service
COPY sink-service/src sink-service/src
RUN mvn -f sink-service/pom.xml clean package -DskipTests

# Stage 2: Runtime stage
FROM ubuntu:22.04

# Install OpenJDK 17 Runtime
RUN apt-get update && \
    apt-get install -y openjdk-17-jre-headless && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy the built artifact from the build stage
COPY --from=build /app/sink-service/target/sink-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

# Environment variables for Kafka and Postgres configuration
ENV KAFKA_BOOTSTRAP_SERVERS=kafka:29092
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/ironclad_telemetry
ENV SPRING_DATASOURCE_USERNAME=ironclad_user
ENV SPRING_DATASOURCE_PASSWORD=ironclad_password

ENTRYPOINT ["java", "-jar", "app.jar"]
