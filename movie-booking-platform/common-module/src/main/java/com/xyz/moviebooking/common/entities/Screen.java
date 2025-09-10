package com.xyz.moviebooking.common.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "screens")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private int totalSeats;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;
    
    // Constructors
    public Screen() {}
    
    public Screen(String name, int totalSeats, Theatre theatre) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.theatre = theatre;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
    
    public Theatre getTheatre() { return theatre; }
    public void setTheatre(Theatre theatre) { this.theatre = theatre; }
    
    @Override
    public String toString() {
        return "Screen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalSeats=" + totalSeats +
                ", theatre=" + (theatre != null ? theatre.getName() : "null") +
                '}';
    }
}