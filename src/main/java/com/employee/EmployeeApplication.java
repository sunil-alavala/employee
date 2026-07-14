package com.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
		BCryptPasswordEncoder encoder =
				new BCryptPasswordEncoder();

		String encryptedPassword =
				encoder.encode("admin123");

		System.out.println("Password......" + encryptedPassword);
	}

}
