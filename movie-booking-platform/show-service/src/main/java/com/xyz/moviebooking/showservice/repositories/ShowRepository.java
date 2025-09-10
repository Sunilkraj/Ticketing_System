package com.xyz.moviebooking.showservice.repositories;

import com.xyz.moviebooking.showservice.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    
    // Use native SQL query instead of JPQL
    @Query(value = "SELECT s.* FROM shows s " +
                   "WHERE s.movie_id = :movieId " +
                   "AND s.theatre_id IN (SELECT t.id FROM theatres t WHERE t.city = :city) " +
                   "AND DATE(s.start_time) = :date " +
                   "ORDER BY s.start_time", 
           nativeQuery = true)
    List<Show> findShowsByMovieAndCityAndDate(@Param("movieId") Long movieId,
                                             @Param("city") String city,
                                             @Param("date") LocalDate date);
    
    @Query("SELECT s FROM Show s WHERE s.theatreId = :theatreId AND DATE(s.startTime) = :date")
    List<Show> findByTheatreIdAndDate(@Param("theatreId") Long theatreId, 
                                     @Param("date") LocalDate date);
    
    List<Show> findByMovieId(Long movieId);
    List<Show> findByTheatreId(Long theatreId);
    List<Show> findByTheatreIdAndMovieId(Long theatreId, Long movieId);
}