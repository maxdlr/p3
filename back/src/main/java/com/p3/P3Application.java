package com.p3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.p3.persistence")
@EntityScan("com.p3.model")
@SpringBootApplication()
public class P3Application {

	public static void main(String[] args) {
		SpringApplication.run(P3Application.class, args);
	}
}
