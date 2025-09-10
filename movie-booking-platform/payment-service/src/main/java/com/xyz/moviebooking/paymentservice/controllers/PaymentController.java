package com.xyz.moviebooking.paymentservice.controllers;

import com.xyz.moviebooking.paymentservice.entities.Payment;
import com.xyz.moviebooking.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(
            @RequestParam Long bookingId,
            @RequestParam Double amount,
            @RequestParam(defaultValue = "USD") String currency,
            @RequestParam(defaultValue = "CREDIT_CARD") String paymentMethod) {
        
        Payment payment = paymentService.processPayment(bookingId, amount, currency, paymentMethod);
        return ResponseEntity.ok(payment);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        return payment.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<Payment> getPaymentByBookingId(@PathVariable Long bookingId) {
        Optional<Payment> payment = paymentService.getPaymentByBookingId(bookingId);
        return payment.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<Payment> getPaymentByTransactionId(@PathVariable String transactionId) {
        Optional<Payment> payment = paymentService.getPaymentByTransactionId(transactionId);
        return payment.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/{id}/refund")
    public ResponseEntity<Payment> refundPayment(@PathVariable Long id) {
        try {
            Payment refundedPayment = paymentService.refundPayment(id);
            return ResponseEntity.ok(refundedPayment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}