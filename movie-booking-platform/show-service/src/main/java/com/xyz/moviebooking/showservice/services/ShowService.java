package com.xyz.moviebooking.showservice.services;

import com.xyz.moviebooking.common.entities.Theatre;
import com.xyz.moviebooking.showservice.clients.TheatreResponse;
import com.xyz.moviebooking.showservice.clients.TheatreServiceClient;
import com.xyz.moviebooking.showservice.entities.Show;
import com.xyz.moviebooking.showservice.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {
    
    @Autowired
    private ShowRepository showRepository;
    
    @Autowired
    private TheatreServiceClient theatreServiceClient;
    
    public List<Show> getShowsByMovieAndCityAndDate(Long movieId, String city, LocalDate date) {
        // Call theatre service via Feign client to get theatres in the city
        List<TheatreResponse> theatres = theatreServiceClient.getTheatresByCity(city);
        List<Long> theatreIds = theatres.stream()
                .map(TheatreResponse::getId)
                .collect(Collectors.toList());
        
        // Get all shows for the movie
        List<Show> allShows = showRepository.findByMovieId(movieId);
        
        // Filter shows by theatre IDs and date
        return allShows.stream()
                .filter(show -> theatreIds.contains(show.getTheatreId()))
                .filter(show -> show.getStartTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }
    
    public List<Show> getShowsByTheatreAndDate(Long theatreId, LocalDate date) {
        return showRepository.findByTheatreIdAndDate(theatreId, date);
    }
    
    public Show getShowById(Long id) {
        return showRepository.findById(id).orElse(null);
    }
    
    public List<Show> getShowsByTheatreId(Long theatreId) {
        return showRepository.findByTheatreId(theatreId);
    }
    
    public List<Show> getShowsByMovieId(Long movieId) {
        return showRepository.findByMovieId(movieId);
    }
    
    public Show createShow(Show show) {
        return showRepository.save(show);
    }
    
    public Show updateShow(Long id, Show showDetails) {
        return showRepository.findById(id).map(show -> {
            show.setMovieId(showDetails.getMovieId());
            show.setTheatreId(showDetails.getTheatreId());
            show.setScreenId(showDetails.getScreenId());
            show.setStartTime(showDetails.getStartTime());
            show.setEndTime(showDetails.getEndTime());
            show.setAvailableSeats(showDetails.getAvailableSeats());
            return showRepository.save(show);
        }).orElseThrow(() -> new RuntimeException("Show not found with id: " + id));
    }
    
    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }
    
    public boolean checkSeatAvailability(Long showId, List<String> seatNumbers) {
        Show show = showRepository.findById(showId).orElse(null);
        return show != null && show.getAvailableSeats() >= seatNumbers.size();
    }
    
    public boolean lockSeats(Long showId, List<String> seatNumbers) {
        Show show = showRepository.findById(showId).orElse(null);
        if (show == null || show.getAvailableSeats() < seatNumbers.size()) {
            return false;
        }
        
        show.setAvailableSeats(show.getAvailableSeats() - seatNumbers.size());
        showRepository.save(show);
        return true;
    }
    
    public boolean releaseSeats(Long showId, List<String> seatNumbers) {
        Show show = showRepository.findById(showId).orElse(null);
        if (show == null) {
            return false;
        }
        
        show.setAvailableSeats(show.getAvailableSeats() + seatNumbers.size());
        showRepository.save(show);
        return true;
    }
    
    public Double getBasePrice(Long showId) {
        // Fixed base price for demo - could vary by show
        return 10.0;
    }
    
    public Boolean isAfternoonShow(Long showId) {
        Show show = showRepository.findById(showId).orElse(null);
        if (show == null) {
            return false;
        }
        
        int hour = show.getStartTime().getHour();
        return hour >= 12 && hour < 17;
    }


}