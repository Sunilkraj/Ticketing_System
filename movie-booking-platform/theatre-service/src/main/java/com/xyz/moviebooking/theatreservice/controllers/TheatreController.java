package com.xyz.moviebooking.theatreservice.controllers;

import com.xyz.moviebooking.common.entities.Theatre;
import com.xyz.moviebooking.theatreservice.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {
    
    @Autowired
    private TheatreService theatreService;
    
    @GetMapping
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        List<Theatre> theatres = theatreService.getAllTheatres();
        return ResponseEntity.ok(theatres);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable Long id) {
        Optional<Theatre> theatre = theatreService.getTheatreById(id);
        return theatre.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Theatre>> getTheatresByCity(@PathVariable String city) {
        List<Theatre> theatres = theatreService.getTheatresByCity(city);
        return ResponseEntity.ok(theatres);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Theatre>> searchTheatres(@RequestParam String name) {
        List<Theatre> theatres = theatreService.searchTheatres(name);
        return ResponseEntity.ok(theatres);
    }
    
    @PostMapping
    public ResponseEntity<Theatre> createTheatre(@RequestBody Theatre theatre) {
        Theatre createdTheatre = theatreService.createTheatre(theatre);
        return ResponseEntity.ok(createdTheatre);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable Long id, @RequestBody Theatre theatreDetails) {
        try {
            Theatre updatedTheatre = theatreService.updateTheatre(id, theatreDetails);
            return ResponseEntity.ok(updatedTheatre);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheatre(@PathVariable Long id) {
        theatreService.deleteTheatre(id);
        return ResponseEntity.noContent().build();
    }
}