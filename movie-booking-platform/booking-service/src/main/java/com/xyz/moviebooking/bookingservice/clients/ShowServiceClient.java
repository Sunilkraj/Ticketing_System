package com.xyz.moviebooking.bookingservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "show-service", url = "http://localhost:8082")
public interface ShowServiceClient {
    
    @GetMapping("/api/shows/{showId}/seats/availability")
    Boolean checkSeatAvailability(@PathVariable Long showId, 
                                 @RequestParam List<String> seatNumbers);
    
    @PostMapping("/api/shows/{showId}/seats/lock")
    Boolean lockSeats(@PathVariable Long showId, 
                     @RequestParam List<String> seatNumbers);
    
    @PostMapping("/api/shows/{showId}/seats/release")
    Boolean releaseSeats(@PathVariable Long showId, 
                        @RequestParam List<String> seatNumbers);
    
    @GetMapping("/api/shows/{showId}/base-price")
    Double getBasePrice(@PathVariable Long showId);
    
    @GetMapping("/api/shows/{showId}/is-afternoon")
    Boolean isAfternoonShow(@PathVariable Long showId);
}