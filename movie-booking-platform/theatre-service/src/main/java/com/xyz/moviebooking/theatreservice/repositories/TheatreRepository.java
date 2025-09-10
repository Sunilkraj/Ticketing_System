package com.xyz.moviebooking.theatreservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xyz.moviebooking.common.entities.Theatre;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    List<Theatre> findByCity(String city);
    List<Theatre> findByNameContainingIgnoreCase(String name);
}