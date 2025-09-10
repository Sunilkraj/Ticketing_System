# 🎬 Movie Booking Platform — API Guide

This document lists all **REST APIs** for the microservices in the Movie Booking Platform.  
Base URLs assume local deployment with default ports.

---

## 1️⃣ Movie Service APIs (`localhost:8084`)

### Get All Movies
```
GET http://localhost:8084/api/movies
```
**Response**
```json
[
  {
    "id": 1,
    "title": "Avengers: Endgame",
    "description": "The epic conclusion to the Infinity Saga",
    "language": "English",
    "genre": "Action",
    "duration": 181,
    "releaseDate": "2019-04-26",
    "director": "Anthony Russo, Joe Russo",
    "cast": "Robert Downey Jr., Chris Evans, Scarlett Johansson"
  }
]
```

### Create Movie
```
POST http://localhost:8084/api/movies
```
**Request**
```json
{
  "title": "Inception",
  "description": "A thief who steals corporate secrets",
  "language": "English",
  "genre": "Sci-Fi",
  "duration": 148,
  "releaseDate": "2010-07-16",
  "director": "Christopher Nolan",
  "cast": "Leonardo DiCaprio, Joseph Gordon-Levitt"
}
```

### Search Movies
```
GET http://localhost:8084/api/movies/search?title=avengers
```

---

## 2️⃣ Theatre Service APIs (`localhost:8083`)

### Create Theatre
```
POST http://localhost:8083/api/theatres
```
**Request**
```json
{
  "name": "IMAX Theatre",
  "city": "Bangalore",
  "address": "Malleshwaram"
}
```

### Get All Theatres
```
GET http://localhost:8083/api/theatres
```

### Get Theatres by City
```
GET http://localhost:8083/api/theatres/city/Bangalore
```

---

## 3️⃣ Show Service APIs (`localhost:8082`)

### Create Show
```
POST http://localhost:8082/api/shows
```
**Request**
```json
{
  "movieId": 1,
  "theatreId": 1,
  "screenId": 1,
  "startTime": "2023-12-25T14:30:00",
  "endTime": "2023-12-25T17:30:00",
  "availableSeats": 200
}
```

### Get Shows by Movie, City and Date
```
GET http://localhost:8082/api/shows/movie/1?city=Bangalore&date=2023-12-25
```

### Lock Seats
```
POST http://localhost:8082/api/shows/1/seats/lock?seatNumbers=A1,A2,A3
```

### Check Seat Availability
```
GET http://localhost:8082/api/shows/1/seats/availability?seatNumbers=A1,A2,A3
```

### Search Theatres
```
GET http://localhost:8082/api/shows/theatres/search?name=PVR
```

---

## 4️⃣ Booking Service APIs (`localhost:8081`)

### Create Booking
```
POST http://localhost:8081/api/bookings
```
**Request**
```json
{
  "userId": 1,
  "showId": 1,
  "seatNumbers": ["A1", "A2", "A3"]
}
```

### Get Booking by ID
```
GET http://localhost:8081/api/bookings/1
```

⚠️ **Note:** `"status": "FAILED"` since `processPayment` and `refundPayment` are not yet implemented.

### Get User’s Bookings
```
GET http://localhost:8081/api/bookings/user/1
```

### Cancel Booking
```
POST http://localhost:8081/api/bookings/1/cancel
```

---

## 5️⃣ Payment Service APIs (`localhost:8085`)

### Process Payment
```
POST http://localhost:8085/api/payments/process?bookingId=1&amount=27.0&currency=USD&paymentMethod=CREDIT_CARD
```
**Response**
```json
{
  "id": 2,
  "bookingId": 1,
  "amount": 27.0,
  "currency": "USD",
  "paymentMethod": "CREDIT_CARD",
  "paymentStatus": "SUCCESS",
  "transactionId": "04e2404c-3128-4bb8-b69e-cca97f25bce3",
  "paymentDate": "2025-09-08T11:19:57.5025924"
}
```

### Get Payment by ID
```
GET http://localhost:8085/api/payments/1
```

### Refund Payment
```
POST http://localhost:8085/api/payments/5/refund
```

---
