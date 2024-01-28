package com.kdu.caching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GecodingAssignment {
    public static void main(String[] args) {
        SpringApplication.run(GecodingAssignment.class, args);

    }
}
