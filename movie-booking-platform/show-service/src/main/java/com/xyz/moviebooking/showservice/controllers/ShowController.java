package com.xyz.moviebooking.showservice.controllers;

import com.xyz.moviebooking.common.entities.Theatre;
import com.xyz.moviebooking.showservice.entities.Show;
import com.xyz.moviebooking.showservice.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {
    
    @Autowired
    private ShowService showService;
    
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Show>> getShowsByMovieAndCityAndDate(
            @PathVariable Long movieId,
            @RequestParam String city,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        List<Show> shows = showService.getShowsByMovieAndCityAndDate(movieId, city, date);
        return ResponseEntity.ok(shows);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable Long id) {
        Show show = showService.getShowById(id);
        if (show != null) {
            return ResponseEntity.ok(show);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<Show>> getShowsByTheatreAndDate(
            @PathVariable Long theatreId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        List<Show> shows = showService.getShowsByTheatreAndDate(theatreId, date);
        return ResponseEntity.ok(shows);
    }
    
    @PostMapping
    public ResponseEntity<Show> createShow(@RequestBody Show show) {
        Show createdShow = showService.createShow(show);
        return ResponseEntity.ok(createdShow);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody Show showDetails) {
        try {
            Show updatedShow = showService.updateShow(id, showDetails);
            return ResponseEntity.ok(updatedShow);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }
    
    // Endpoints for seat management (used by booking service)
    @GetMapping("/{showId}/seats/availability")
    public ResponseEntity<Boolean> checkSeatAvailability(
            @PathVariable Long showId,
            @RequestParam List<String> seatNumbers) {
        
        boolean available = showService.checkSeatAvailability(showId, seatNumbers);
        return ResponseEntity.ok(available);
    }
    
    @PostMapping("/{showId}/seats/lock")
    public ResponseEntity<Boolean> lockSeats(
            @PathVariable Long showId,
            @RequestParam List<String> seatNumbers) {
        
        boolean success = showService.lockSeats(showId, seatNumbers);
        return ResponseEntity.ok(success);
    }
    
    @PostMapping("/{showId}/seats/release")
    public ResponseEntity<Boolean> releaseSeats(
            @PathVariable Long showId,
            @RequestParam List<String> seatNumbers) {
        
        boolean success = showService.releaseSeats(showId, seatNumbers);
        return ResponseEntity.ok(success);
    }
    
    @GetMapping("/{showId}/base-price")
    public ResponseEntity<Double> getBasePrice(@PathVariable Long showId) {
        Double basePrice = showService.getBasePrice(showId);
        return ResponseEntity.ok(basePrice);
    }
    
    @GetMapping("/{showId}/is-afternoon")
    public ResponseEntity<Boolean> isAfternoonShow(@PathVariable Long showId) {
        Boolean isAfternoon = showService.isAfternoonShow(showId);
        return ResponseEntity.ok(isAfternoon);
    }
}