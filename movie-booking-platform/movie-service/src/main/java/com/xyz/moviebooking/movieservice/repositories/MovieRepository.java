package com.xyz.moviebooking.movieservice.repositories;

import com.xyz.moviebooking.movieservice.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByLanguage(String language);
    List<Movie> findByGenre(String genre);
    List<Movie> findByLanguageAndGenre(String language, String genre);
}