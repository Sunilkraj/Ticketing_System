package com.xyz.moviebooking.bookingservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "payment-service", url = "http://localhost:8085")
public interface PaymentServiceClient {
    
    @PostMapping("/api/payments/process")
    Boolean processPayment(@RequestParam Long bookingId, 
                          @RequestParam Double amount);
    
    @PostMapping("/api/payments/{paymentId}/refund")
    Boolean refundPayment(@PathVariable Long paymentId);
}