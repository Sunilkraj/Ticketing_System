package com.xyz.moviebooking.bookingservice.repositories;

import com.xyz.moviebooking.bookingservice.entities.Booking;
import com.xyz.moviebooking.bookingservice.entities.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByStatus(BookingStatus status);
    List<Booking> findByShowId(Long showId);
    Optional<Booking> findByIdAndUserId(Long id, Long userId);
    List<Booking> findByUserIdAndStatus(Long userId, BookingStatus status);
}