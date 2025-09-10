package com.xyz.moviebooking.movieservice.controllers;

import com.xyz.moviebooking.movieservice.entities.Movie;
import com.xyz.moviebooking.movieservice.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    
    @Autowired
    private MovieService movieService;
    
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        return movie.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String title) {
        List<Movie> movies = movieService.searchMoviesByTitle(title);
        return ResponseEntity.ok(movies);
    }
    
    @GetMapping("/language/{language}")
    public ResponseEntity<List<Movie>> getMoviesByLanguage(@PathVariable String language) {
        List<Movie> movies = movieService.getMoviesByLanguage(language);
        return ResponseEntity.ok(movies);
    }
    
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable String genre) {
        List<Movie> movies = movieService.getMoviesByGenre(genre);
        return ResponseEntity.ok(movies);
    }
    
    @GetMapping("/filter")
    public ResponseEntity<List<Movie>> getMoviesByLanguageAndGenre(
            @RequestParam String language, @RequestParam String genre) {
        List<Movie> movies = movieService.getMoviesByLanguageAndGenre(language, genre);
        return ResponseEntity.ok(movies);
    }
    
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        return ResponseEntity.ok(createdMovie);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        try {
            Movie updatedMovie = movieService.updateMovie(id, movieDetails);
            return ResponseEntity.ok(updatedMovie);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}