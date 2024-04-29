package com.example.kursach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class JavaKursachApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaKursachApplication.class, args);
	}

}
