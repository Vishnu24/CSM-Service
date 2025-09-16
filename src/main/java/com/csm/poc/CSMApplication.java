package com.csm.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CSMApplication {

	public static void main(String[] args) {
		SpringApplication.run(CSMApplication.class, args);
		System.out.println("Server is Running");
	}
}
