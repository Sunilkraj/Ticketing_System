package com.xyz.moviebooking.showservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xyz.moviebooking.showservice.clients")
public class ShowServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShowServiceApplication.class, args);
    }
}