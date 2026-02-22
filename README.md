# Ironclad: Drone Telemetry Ingestion System

Ironclad is a high-performance microservices-based system designed to ingest, process, and store telemetry data from a fleet of drones in real-time. This project handles critical data like GPS coordinates, battery levels, and sensor metrics, ensuring reliable delivery and persistence for further analysis.

## 🚀 The Journey: A Personal Milestone

This project represents a significant step in my journey as a developer. It marks several "firsts" for me:
- **First Microservices Project:** Transitioning from monolithic architectures to a distributed system using Spring Boot.
- **First Drone-Related System:** Designing a data model and ingestion pipeline tailored for autonomous aerial vehicle telemetry.
- **First Time Using Docker:** Containerizing the entire ecosystem—from the application services to the infrastructure like Kafka and PostgreSQL.

Navigating the complexities of service orchestration, message brokering with Kafka, and container management was a challenging but immensely rewarding experience.

## 🏗️ Architecture Overview

Ironclad is built using a decoupled, event-driven architecture:

1.  **Gateway Service:** A reactive Spring Boot application (using WebFlux) that provides a REST API for drones to POST telemetry data. It acts as a producer, sending messages to a Kafka topic.
2.  **Apache Kafka:** Serves as the high-throughput message backbone, ensuring that the ingestion pipeline remains resilient even under heavy load.
3.  **Sink Service:** A consumer service that listens to the Kafka topic. It processes the incoming telemetry and persists it into a PostgreSQL database.
4.  **PostgreSQL:** The primary relational database, storing telemetry records with JSONB support for flexible sensor metrics.

## 🛠️ Tech Stack

- **Framework:** Spring Boot 3.1+
- **Languages:** Java 17
- **Messaging:** Apache Kafka
- **Database:** PostgreSQL
- **Containerization:** Docker & Docker Compose
- **Build Tool:** Maven

## 🚦 Getting Started

### Prerequisites

- Docker and Docker Compose installed on your system.
- Java 17 (if running services locally outside of Docker).
- Maven (if building the project manually).

### Deployment with Docker

The easiest way to get the entire system up and running is using Docker Compose:

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    cd ironclad
    ```

2.  **Build and Start the Infrastructure:**
    ```bash
    docker-compose up --build
    ```
    This command will build the `gateway-service` and `sink-service` images and start all containers, including Zookeeper, Kafka, and Postgres.

### API Usage

Once the system is running, the Gateway Service is available at `http://localhost:8080`.

**Ingest Telemetry:**
- **Endpoint:** `POST /api/v1/telemetry`
- **Body:**
  ```json
  {
    "droneId": "DRONE-001",
    "gps": {
      "latitude": 34.0522,
      "longitude": -118.2437
    },
    "battery": 85,
    "sensorMetrics": {
      "altitude": 120,
      "speed": 15.5
    }
  }
  ```

## 📈 Future Enhancements

- [ ] Implementation of a Real-time Dashboard for visualizing drone paths.
- [ ] Integration of Prometheus and Grafana for system monitoring.
- [ ] Adding OAuth2 security for the Gateway API.
- [ ] Support for multiple drone protocols (Protobuf/gRPC).

---
*Created with passion by a developer exploring the horizon of microservices and containerization.*
