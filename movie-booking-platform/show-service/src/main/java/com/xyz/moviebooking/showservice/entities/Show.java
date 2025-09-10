package com.xyz.moviebooking.showservice.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long movieId;
    private Long theatreId;
    private Long screenId;
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    private int availableSeats;
    
    // Constructors
    public Show() {}
    
    public Show(Long movieId, Long theatreId, Long screenId, 
                LocalDateTime startTime, LocalDateTime endTime, int availableSeats) {
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.screenId = screenId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSeats = availableSeats;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
    
    public Long getTheatreId() { return theatreId; }
    public void setTheatreId(Long theatreId) { this.theatreId = theatreId; }
    
    public Long getScreenId() { return screenId; }
    public void setScreenId(Long screenId) { this.screenId = screenId; }
    
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
}