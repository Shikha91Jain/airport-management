package com.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring boot main class for airport management application
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.assessment", "com.assessment.rest"})
public class AirportManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportManagementApplication.class, args);
	}

}
