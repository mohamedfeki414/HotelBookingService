package com.hotel.booking.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing 
@SpringBootApplication
public class HotelBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingServiceApplication.class, args);
	}

}
