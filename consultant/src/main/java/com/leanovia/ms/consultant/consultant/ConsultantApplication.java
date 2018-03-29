package com.leanovia.ms.consultant.consultant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.leanovia.ms.consultant.consultant.dao")

public class ConsultantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultantApplication.class, args);
	}
}
