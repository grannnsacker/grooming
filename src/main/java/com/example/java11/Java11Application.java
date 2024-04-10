package com.example.java11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Java11Application {

	public static void main(String[] args) {
		SpringApplication.run(Java11Application.class, args);
	}

}
