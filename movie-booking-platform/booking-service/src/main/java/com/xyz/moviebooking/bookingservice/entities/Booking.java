package com.xyz.moviebooking.bookingservice.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    private Long showId;
    
    @ElementCollection
    @CollectionTable(name = "booking_seats", joinColumns = @JoinColumn(name = "booking_id"))
    @Column(name = "seat_number")
    private List<String> seatNumbers;
    
    private Double totalPrice;
    
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    
    private LocalDateTime bookingTime;
    private LocalDateTime updatedTime;
    
    // Constructors
    public Booking() {
        this.bookingTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }
    
    public Booking(Long userId, Long showId, List<String> seatNumbers) {
        this();
        this.userId = userId;
        this.showId = showId;
        this.seatNumbers = seatNumbers;
        this.status = BookingStatus.PENDING;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Long getShowId() { return showId; }
    public void setShowId(Long showId) { this.showId = showId; }
    
    public List<String> getSeatNumbers() { return seatNumbers; }
    public void setSeatNumbers(List<String> seatNumbers) { this.seatNumbers = seatNumbers; }
    
    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
    
    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { 
        this.status = status; 
        this.updatedTime = LocalDateTime.now();
    }
    
    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
    
    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
}