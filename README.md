# Ticketing_System
Ticketing system repo

# Steps to Create the Movie Booking Platform in Eclipse
Prerequisites
Eclipse IDE for Java Developers (latest version)

Java 17 JDK installed and configured in Eclipse

Maven plugin for Eclipse (m2e)

PostgreSQL database installed and running

# Step-by-Step Guide
1. Create the Main Maven Project
Open Eclipse and go to File → New → Other

Select Maven → Maven Project and click Next

Check "Create a simple project" and click Next

Fill in the details:

Group Id: com.xyz.moviebooking

Artifact Id: movie-booking-platform

Version: 1.0.0

Packaging: pom

Click Finish

2. Add the Parent POM Configuration
Right-click on the project and select Open With → Text Editor

Replace the content with the parent POM provided earlier

Save the file

3. Create the Discovery Server Module
Right-click on the main project → New → Other

Select Maven → Maven Module and click Next

Check "Create a simple project" and enter:

Module Name: discovery-server

Parent: com.xyz.moviebooking:movie-booking-platform

Click Finish

Update the pom.xml with the discovery server dependencies

Create the package structure: com.xyz.moviebooking.discovery

Create the DiscoveryServerApplication.java class

Create the application.yml file in src/main/resources

4. Create the API Gateway Module
Right-click on the main project → New → Other

Select Maven → Maven Module and click Next

Check "Create a simple project" and enter:

Module Name: api-gateway

Parent: com.xyz.moviebooking:movie-booking-platform

Click Finish

Update the pom.xml with the API gateway dependencies

Create the package structure: com.xyz.moviebooking.apigateway

Create the ApiGatewayApplication.java class

Create the application.yml file in src/main/resources

5. Create the Show Service Module
Right-click on the main project → New → Other

Select Maven → Maven Module and click Next

Check "Create a simple project" and enter:

Module Name: show-service

Parent: com.xyz.moviebooking:movie-booking-platform

Click Finish

Update the pom.xml with the show service dependencies

Create the package structure: com.xyz.moviebooking.showservice

Create sub-packages: entities, repositories, services, controllers

Create all the required Java classes:

ShowServiceApplication.java

Show.java, Screen.java, Theatre.java in entities

ShowRepository.java in repositories

ShowService.java in services

ShowController.java in controllers

Create the application.yml file in src/main/resources

6. Create the Booking Service Module
Right-click on the main project → New → Other

Select Maven → Maven Module and click Next

Check "Create a simple project" and enter:

Module Name: booking-service

Parent: com.xyz.moviebooking:movie-booking-platform

Click Finish

Update the pom.xml with the booking service dependencies

Create the package structure: com.xyz.moviebooking.bookingservice

Create sub-packages: entities, repositories, services, controllers

Create all the required Java classes:

BookingServiceApplication.java

Booking.java, BookingStatus.java in entities

BookingRepository.java in repositories

BookingService.java in services

BookingController.java in controllers

Create the application.yml file in src/main/resources

7. Create the Theatre Service Module
Right-click on the main project → New → Other

Select Maven → Maven Module and click Next

Check "Create a simple project" and enter:

Module Name: theatre-service

Parent: com.xyz.moviebooking:movie-booking-platform

Click Finish

Update the pom.xml with the theatre service dependencies

Create the package structure: com.xyz.moviebooking.theatreservice

Create sub-packages: entities, repositories, services, controllers

Create all the required Java classes:

TheatreServiceApplication.java

Theatre.java, Screen.java in entities

TheatreRepository.java in repositories

TheatreService.java in services

TheatreController.java in controllers

Create the application.yml file in src/main/resources

8. Create the Movie Service Module
Right-click on the main project → New → Other

Select Maven → Maven Module and click Next

Check "Create a simple project" and enter:

Module Name: movie-service

Parent: com.xyz.moviebooking:movie-booking-platform

Click Finish

Update the pom.xml with the movie service dependencies

Create the package structure: com.xyz.moviebooking.movieservice

Create sub-packages: entities, repositories, services, controllers

Create all the required Java classes:

MovieServiceApplication.java

Movie.java in entities

MovieRepository.java in repositories

MovieService.java in services

MovieController.java in controllers

Create the application.yml file in src/main/resources

9. Create the Payment Service Module
Right-click on the main project → New → Other

Select Maven → Maven Module and click Next

Check "Create a simple project" and enter:

Module Name: payment-service

Parent: com.xyz.moviebooking:movie-booking-platform

Click Finish

Update the pom.xml with the payment service dependencies

Create the package structure: com.xyz.moviebooking.paymentservice

Create sub-packages: entities, repositories, services, controllers

Create all the required Java classes:

PaymentServiceApplication.java

Payment.java in entities

PaymentRepository.java in repositories

PaymentService.java in services

PaymentController.java in controllers

Create the application.yml file in src/main/resources

10. Configure PostgreSQL Databases
Open PostgreSQL command line or pgAdmin

Run the following SQL commands to create databases:

sql
CREATE DATABASE show_db;
CREATE DATABASE booking_db;
CREATE DATABASE theatre_db;
CREATE DATABASE movie_db;
CREATE DATABASE payment_db;

# Testing API's
Follow the API_LIST.txt

# Design
Follow Architecture.txt
