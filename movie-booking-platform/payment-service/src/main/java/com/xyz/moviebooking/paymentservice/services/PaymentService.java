package com.xyz.moviebooking.paymentservice.services;

import com.xyz.moviebooking.paymentservice.entities.Payment;
import com.xyz.moviebooking.paymentservice.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Value("${payment.gateway.mock.success-rate:0.95}")
    private double successRate;
    
    private final Random random = new Random();
    
    public Payment processPayment(Long bookingId, Double amount, String currency, String paymentMethod) {
        // Simulate payment processing with a mock gateway
        boolean isSuccess = random.nextDouble() < successRate;
        String transactionId = UUID.randomUUID().toString();
        String status = isSuccess ? "SUCCESS" : "FAILED";
        
        Payment payment = new Payment(bookingId, amount, currency, paymentMethod, status, transactionId);
        return paymentRepository.save(payment);
    }
    
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }
    
    public Optional<Payment> getPaymentByBookingId(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }
    
    public Optional<Payment> getPaymentByTransactionId(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId);
    }
    
    public Payment refundPayment(Long paymentId) {
        return paymentRepository.findById(paymentId).map(payment -> {
            payment.setPaymentStatus("REFUNDED");
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));
    }
}