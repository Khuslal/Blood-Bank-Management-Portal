package com.management.bloodbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // used for spring's native auditing, uses eg : @CreatedDate
@SpringBootApplication
public class BloodbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodbankApplication.class, args);
	}

}
