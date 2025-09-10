package com.xyz.moviebooking.theatreservice.services;

import com.xyz.moviebooking.common.entities.Theatre;
import com.xyz.moviebooking.theatreservice.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {
    
    @Autowired
    private TheatreRepository theatreRepository;
    
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }
    
    public Optional<Theatre> getTheatreById(Long id) {
        return theatreRepository.findById(id);
    }
    
    public List<Theatre> getTheatresByCity(String city) {
        return theatreRepository.findByCity(city);
    }
    
    public List<Theatre> searchTheatres(String name) {
        return theatreRepository.findByNameContainingIgnoreCase(name);
    }
    
    public Theatre createTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }
    
    public Theatre updateTheatre(Long id, Theatre theatreDetails) {
        return theatreRepository.findById(id).map(theatre -> {
            theatre.setName(theatreDetails.getName());
            theatre.setCity(theatreDetails.getCity());
            theatre.setAddress(theatreDetails.getAddress());
            return theatreRepository.save(theatre);
        }).orElseThrow(() -> new RuntimeException("Theatre not found with id: " + id));
    }
    
    public void deleteTheatre(Long id) {
        theatreRepository.deleteById(id);
    }
}