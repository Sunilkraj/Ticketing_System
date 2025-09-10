# Ticketing_System 🎟️
A microservices-based **Movie Booking Platform** built with **Spring Boot, Spring Cloud, PostgreSQL, and Java 17**.  
This project demonstrates service discovery, API gateway, authentication, booking, show management, theatre management, movie catalog, and payment services.

---

## 📌 Prerequisites
- **Eclipse IDE for Java Developers** (latest version)  
- **Java 17 JDK** installed and configured in Eclipse  
- **Maven plugin** for Eclipse (m2e)  
- **PostgreSQL** database installed and running  

---

## ⚙️ Steps to Create the Platform in Eclipse

### 1. Create the Main Maven Project
1. Open Eclipse → `File → New → Other`  
2. Select **Maven → Maven Project** → Next  
3. Check **"Create a simple project"** → Next  
4. Fill in:
   - Group Id: `com.xyz.moviebooking`  
   - Artifact Id: `movie-booking-platform`  
   - Version: `1.0.0`  
   - Packaging: `pom`  
5. Finish.  
6. Replace `pom.xml` content with the **parent POM** (manages versions & dependencies).

---

### 2. Create Service Modules

#### 🧭 Discovery Server
- Module Name: `discovery-server`  
- Package: `com.xyz.moviebooking.discovery`  
- Add `DiscoveryServerApplication.java`  
- Add `application.yml`  

#### 🔑 API Gateway
- Module Name: `api-gateway`  
- Package: `com.xyz.moviebooking.apigateway`  
- Add `ApiGatewayApplication.java`  
- Add `application.yml`  

#### 🎬 Show Service
- Module Name: `show-service`  
- Package: `com.xyz.moviebooking.showservice`  
- Sub-packages: `entities`, `repositories`, `services`, `controllers`  
- Classes:  
  - `ShowServiceApplication.java`  
  - `Show.java`, `Screen.java`, `Theatre.java` (entities)  
  - `ShowRepository.java`  
  - `ShowService.java`  
  - `ShowController.java`  
- Add `application.yml`

#### 🎟️ Booking Service
- Module Name: `booking-service`  
- Package: `com.xyz.moviebooking.bookingservice`  
- Sub-packages: `entities`, `repositories`, `services`, `controllers`  
- Classes:  
  - `BookingServiceApplication.java`  
  - `Booking.java`, `BookingStatus.java`  
  - `BookingRepository.java`  
  - `BookingService.java`  
  - `BookingController.java`  
- Add `application.yml`

#### 🏛 Theatre Service
- Module Name: `theatre-service`  
- Package: `com.xyz.moviebooking.theatreservice`  
- Sub-packages: `entities`, `repositories`, `services`, `controllers`  
- Classes:  
  - `TheatreServiceApplication.java`  
  - `Theatre.java`, `Screen.java`  
  - `TheatreRepository.java`  
  - `TheatreService.java`  
  - `TheatreController.java`  
- Add `application.yml`

#### 🎥 Movie Service
- Module Name: `movie-service`  
- Package: `com.xyz.moviebooking.movieservice`  
- Sub-packages: `entities`, `repositories`, `services`, `controllers`  
- Classes:  
  - `MovieServiceApplication.java`  
  - `Movie.java`  
  - `MovieRepository.java`  
  - `MovieService.java`  
  - `MovieController.java`  
- Add `application.yml`

#### 💳 Payment Service
- Module Name: `payment-service`  
- Package: `com.xyz.moviebooking.paymentservice`  
- Sub-packages: `entities`, `repositories`, `services`, `controllers`  
- Classes:  
  - `PaymentServiceApplication.java`  
  - `Payment.java`  
  - `PaymentRepository.java`  
  - `PaymentService.java`  
  - `PaymentController.java`  
- Add `application.yml`

---

## 🗄️ PostgreSQL Databases

Create databases for each microservice:

```sql
CREATE DATABASE show_db;
CREATE DATABASE booking_db;
CREATE DATABASE theatre_db;
CREATE DATABASE movie_db;
CREATE DATABASE payment_db;
