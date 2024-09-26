package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"Api", "Persistence", "Model"})
public class P3Application {

	public static void main(String[] args) {
		SpringApplication.run(P3Application.class, args);
	}

}
