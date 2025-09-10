package com.xyz.moviebooking.theatreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = {
    "com.xyz.moviebooking.theatreservice.entities",
    "com.xyz.moviebooking.common.entities"  // Scan common entities
})
@EnableJpaRepositories(basePackages = "com.xyz.moviebooking.theatreservice.repositories")
public class TheatreServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TheatreServiceApplication.class, args);
    }
}