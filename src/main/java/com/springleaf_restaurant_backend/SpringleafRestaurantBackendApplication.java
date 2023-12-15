package com.springleaf_restaurant_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringleafRestaurantBackendApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SpringleafRestaurantBackendApplication.class, args);
	}

}
