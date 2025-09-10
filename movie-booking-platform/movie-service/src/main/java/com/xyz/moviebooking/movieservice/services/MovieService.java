package com.xyz.moviebooking.movieservice.services;

import com.xyz.moviebooking.movieservice.entities.Movie;
import com.xyz.moviebooking.movieservice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;
    
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }
    
    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public List<Movie> getMoviesByLanguage(String language) {
        return movieRepository.findByLanguage(language);
    }
    
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }
    
    public List<Movie> getMoviesByLanguageAndGenre(String language, String genre) {
        return movieRepository.findByLanguageAndGenre(language, genre);
    }
    
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }
    
    public Movie updateMovie(Long id, Movie movieDetails) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(movieDetails.getTitle());
            movie.setDescription(movieDetails.getDescription());
            movie.setLanguage(movieDetails.getLanguage());
            movie.setGenre(movieDetails.getGenre());
            movie.setDuration(movieDetails.getDuration());
            movie.setReleaseDate(movieDetails.getReleaseDate());
            movie.setDirector(movieDetails.getDirector());
            movie.setCast(movieDetails.getCast());
            return movieRepository.save(movie);
        }).orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }
    
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}