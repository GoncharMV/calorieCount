package com.grettastic.calorieCount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.grettastic.calorieCount.*")
public class CalorieCountApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalorieCountApplication.class, args);
	}

}
