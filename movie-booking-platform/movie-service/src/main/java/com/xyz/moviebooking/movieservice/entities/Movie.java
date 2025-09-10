package com.xyz.moviebooking.movieservice.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    private String language;
    private String genre;
    private int duration;
    private LocalDate releaseDate;
    private String director;
    
    @Column(name = "movie_cast")  // Rename the column to avoid reserved keyword
    private String cast;
    
    // Constructors
    public Movie() {}
    
    public Movie(String title, String description, String language, String genre, 
                 int duration, LocalDate releaseDate, String director, String cast) {
        this.title = title;
        this.description = description;
        this.language = language;
        this.genre = genre;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.director = director;
        this.cast = cast;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    
    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    
    public String getCast() { return cast; }
    public void setCast(String cast) { this.cast = cast; }
}