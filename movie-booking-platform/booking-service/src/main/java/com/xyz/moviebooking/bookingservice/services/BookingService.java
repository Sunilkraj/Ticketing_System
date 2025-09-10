package com.xyz.moviebooking.bookingservice.services;

import com.xyz.moviebooking.bookingservice.clients.PaymentServiceClient;
import com.xyz.moviebooking.bookingservice.clients.ShowServiceClient;
import com.xyz.moviebooking.bookingservice.entities.Booking;
import com.xyz.moviebooking.bookingservice.entities.BookingStatus;
import com.xyz.moviebooking.bookingservice.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private ShowServiceClient showServiceClient;
    
    @Autowired
    private PaymentServiceClient paymentServiceClient;
    
    @Transactional
    public Booking createBooking(Booking booking) {
        // Check seat availability
        Boolean seatsAvailable = showServiceClient.checkSeatAvailability(
            booking.getShowId(), booking.getSeatNumbers());
        
        if (seatsAvailable == null || !seatsAvailable) {
            throw new RuntimeException("Seats not available");
        }
        
        // Calculate price with discounts
        double price = calculatePrice(booking);
        booking.setTotalPrice(price);
        booking.setStatus(BookingStatus.PENDING);
        
        // Save booking
        Booking savedBooking = bookingRepository.save(booking);
        
        try {
            // Initiate payment
            Boolean paymentSuccess = paymentServiceClient.processPayment(
                savedBooking.getId(), price);
            
            if (paymentSuccess != null && paymentSuccess) {
                savedBooking.setStatus(BookingStatus.CONFIRMED);
                // Lock the seats
                showServiceClient.lockSeats(booking.getShowId(), booking.getSeatNumbers());
            } else {
                savedBooking.setStatus(BookingStatus.FAILED);
            }
            
            return bookingRepository.save(savedBooking);
        } catch (Exception e) {
            // If payment fails, mark booking as failed
            savedBooking.setStatus(BookingStatus.FAILED);
            return bookingRepository.save(savedBooking);
        }
    }
    
    private double calculatePrice(Booking booking) {
        // Get base price from show service
        Double basePrice = showServiceClient.getBasePrice(booking.getShowId());
        if (basePrice == null) {
            basePrice = 10.0; // Default base price
        }
        
        int ticketCount = booking.getSeatNumbers().size();
        double total = basePrice * ticketCount;
        
        // Third ticket discount (50% off on third ticket)
        if (ticketCount >= 3) {
            total -= basePrice * 0.5;
        }
        
        // Afternoon show discount (20% off)
        Boolean isAfternoon = showServiceClient.isAfternoonShow(booking.getShowId());
        if (isAfternoon != null && isAfternoon) {
            total *= 0.8;
        }
        
        return total;
    }
    
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }
    
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
    
    public List<Booking> getBookingsByStatus(BookingStatus status) {
        return bookingRepository.findByStatus(status);
    }
    
    public List<Booking> getBookingsByShowId(Long showId) {
        return bookingRepository.findByShowId(showId);
    }
    
    @Transactional
    public Optional<Booking> cancelBooking(Long id) {
        return bookingRepository.findById(id).map(booking -> {
            if (booking.getStatus() == BookingStatus.CONFIRMED) {
                booking.setStatus(BookingStatus.CANCELLED);
                // Release seats
                showServiceClient.releaseSeats(booking.getShowId(), booking.getSeatNumbers());
                
                // Initiate refund if payment was successful
                // Note: In a real implementation, you'd need to store payment ID
                // paymentServiceClient.refundPayment(paymentId);
            }
            return bookingRepository.save(booking);
        });
    }
    
    @Transactional
    public Optional<Booking> updateBookingStatus(Long id, BookingStatus status) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setStatus(status);
            return bookingRepository.save(booking);
        });
    }
}