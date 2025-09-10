# 🎬 Movie Booking Platform — Setup Guide

This document describes how to **import, configure, and understand** the Movie Booking Platform built with Spring Boot Microservices.

---

## 📦 Import Instructions

1. **Unzip** `Spring Boot Application.zip`.  
2. In Eclipse:  
   - `File → Import → Existing Maven Projects`  
   - Select the unzipped folder `movie-booking-platform`.  
   - Eclipse will detect all modules.

---

## 📂 Project Structure
movie-booking-platform (pom)
├── discovery-server
│   ├── src/main/java
│   │   └── com/xyz/moviebooking/discovery
│   │       └── DiscoveryServerApplication.java
│   └── src/main/resources
│       └── application.yml
├── api-gateway
│   ├── src/main/java
│   │   └── com/xyz/moviebooking/apigateway
│   │       └── ApiGatewayApplication.java
│   └── src/main/resources
│       └── application.yml
├── show-service
│   ├── src/main/java
│   │   └── com/xyz/moviebooking/showservice
│   │       ├── entities
│   │       ├── repositories
│   │       ├── services
│   │       ├── controllers
│   │       └── ShowServiceApplication.java
│   └── src/main/resources
│       └── application.yml
├── booking-service
│   ├── src/main/java
│   │   └── com/xyz/moviebooking/bookingservice
│   │       ├── entities
│   │       ├── repositories
│   │       ├── services
│   │       ├── controllers
│   │       └── BookingServiceApplication.java
│   └── src/main/resources
│       └── application.yml
├── theatre-service
│   ├── src/main/java
│   │   └── com/xyz/moviebooking/theatreservice
│   │       ├── entities
│   │       ├── repositories
│   │       ├── services
│   │       ├── controllers
│   │       └── TheatreServiceApplication.java
│   └── src/main/resources
│       └── application.yml
├── movie-service
│   ├── src/main/java
│   │   └── com/xyz/moviebooking/movieservice
│   │       ├── entities
│   │       ├── repositories
│   │       ├── services
│   │       ├── controllers
│   │       └── MovieServiceApplication.java
│   └── src/main/resources
│       └── application.yml
└── payment-service
    ├── src/main/java
    │   └── com/xyz/moviebooking/paymentservice
    │       ├── entities
    │       ├── repositories
    │       ├── services
    │       ├── controllers
    │       └── PaymentServiceApplication.java
    └── src/main/resources
        └── application.yml
		
---

## 📝 Selected Write Scenario: Book Movie Tickets

### 🎯 Design Patterns Used
- **Facade Pattern** → `BookingFacadeService` orchestrates booking workflow:  
  - Check availability  
  - Calculate price  
  - Lock seats  
  - Process payment  
  - Confirm booking  

- **Strategy Pattern** → Different discount strategies:  
  - `ThirdTicketDiscountStrategy`  
  - `AfternoonShowDiscountStrategy`  

---

## 🏗️ Architectural Diagram


Architectural Diagram
========================
  +-------------------+      +-----------------+      +---------------------+
  |   Web / Mobile    |      |   API Gateway   |      |   Service Registry  |
  |     Clients       +----->+  (Spring Cloud  +----->+    (Eureka/Consul)  |
  |                   |      |     Gateway)    |      |                     |
  +-------------------+      +--------+--------+      +---------------------+
                                      |
              +-----------------------+-----------------------+
              |                       |                       |
      +-------v-------+       +-------v-------+       +-------v-------+
      |  Booking      |       |   Show &      |       |   Theatre     |
      |  Service      |       |   Movie Service|      |  Management   |
      |  (Write)      |       |   (Read)      |       |  Service      |
      +-------+-------+       +-------+-------+       +-------+-------+
              |                       |                       |
      +-------v-------+       +-------v-------+       +-------v-------+
      |   PostgreSQL  |       |   PostgreSQL  |       |   PostgreSQL  |
      |   (Booking)   |       |   (Read)      |       |   (Theatre)   |
      +-------+-------+       +-------+-------+       +-------+-------+
              |                       |                       |
      +-------v-----------------------+-----------------------+-------+
      |                 Centralized Logging (ELK Stack)               |
      |                 Monitoring (Prometheus/Grafana)               |
      +---------------------------------------------------------------+
                                      |
                              +-------v-------+
                              |   Cloud       |
                              |   (AWS/Azure) |
                              +---------------+
							  

---

## 🔑 Key Design Decisions

- **Microservices Architecture** → Independent scaling for booking (write-heavy) vs browsing (read-heavy).  
- **API Gateway** → Centralized entry point for authentication, routing, and rate-limiting.  
- **Database per Service** → Loose coupling. Supports **CQRS** for read-heavy queries.  

### 🔄 Transactional Integrity
- **Saga Pattern** → Handles distributed transactions (booking + payment).  
- **Pessimistic Locking** → Prevents seat overbooking.  

### 📡 Integration & Localization
- REST APIs + Async Messaging (Kafka/RabbitMQ).  
- Localization supported via `Accept-Language` header.  

### 📈 Scalability & Availability
- Multi-region deployment (AWS/Azure).  
- Auto-scaling via Kubernetes / AWS ASG.  
- Redis cache for frequently accessed data.  

### 🔒 Security
- HTTPS enforced at API Gateway.  
- OAuth2.0 / JWT authentication.  
- OWASP Top 10 protections (input validation, WAF, secrets management).  

---

## ⚙️ Platform Provisioning & Tech Choices

- **Language** → Java 17  
- **Framework** → Spring Boot + Spring Cloud  
- **Databases** → PostgreSQL (per service)  
- **Async Messaging** → Kafka / RabbitMQ  
- **Cloud** → AWS (EKS, RDS, ElastiCache, S3)  
- **Monitoring & Logging** → Prometheus, Grafana, ELK Stack  
- **AI Extension (Optional)** → Recommendation engine using ML  

---

## 🗄️ PostgreSQL Setup

Run in `psql`:

```sql
CREATE DATABASE show_db;
CREATE DATABASE booking_db;
CREATE DATABASE theatre_db;
CREATE DATABASE movie_db;
CREATE DATABASE payment_db;
