package com.xyz.moviebooking.bookingservice.controllers;

import com.xyz.moviebooking.bookingservice.entities.Booking;
import com.xyz.moviebooking.bookingservice.entities.BookingStatus;
import com.xyz.moviebooking.bookingservice.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        try {
            Booking createdBooking = bookingService.createBooking(booking);
            return ResponseEntity.ok(createdBooking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Booking>> getBookingsByStatus(@PathVariable BookingStatus status) {
        List<Booking> bookings = bookingService.getBookingsByStatus(status);
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/show/{showId}")
    public ResponseEntity<List<Booking>> getBookingsByShowId(@PathVariable Long showId) {
        List<Booking> bookings = bookingService.getBookingsByShowId(showId);
        return ResponseEntity.ok(bookings);
    }
    
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
        Optional<Booking> cancelledBooking = bookingService.cancelBooking(id);
        return cancelledBooking.map(ResponseEntity::ok)
                              .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Booking> updateBookingStatus(
            @PathVariable Long id, 
            @RequestParam BookingStatus status) {
        Optional<Booking> updatedBooking = bookingService.updateBookingStatus(id, status);
        return updatedBooking.map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }
}