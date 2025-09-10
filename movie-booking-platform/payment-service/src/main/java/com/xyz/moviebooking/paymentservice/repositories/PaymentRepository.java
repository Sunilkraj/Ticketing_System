package com.xyz.moviebooking.paymentservice.repositories;

import com.xyz.moviebooking.paymentservice.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByBookingId(Long bookingId);
    List<Payment> findByPaymentStatus(String paymentStatus);
    Optional<Payment> findByTransactionId(String transactionId);
}