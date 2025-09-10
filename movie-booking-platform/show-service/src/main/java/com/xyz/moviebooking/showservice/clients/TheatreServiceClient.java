package com.xyz.moviebooking.showservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "theatre-service", url = "http://localhost:8083")
public interface TheatreServiceClient {
    
    @GetMapping("/api/theatres/city/{city}")
    List<TheatreResponse> getTheatresByCity(@PathVariable String city);
    
    @GetMapping("/api/theatres/{id}")
    TheatreResponse getTheatreById(@PathVariable Long id);
}

