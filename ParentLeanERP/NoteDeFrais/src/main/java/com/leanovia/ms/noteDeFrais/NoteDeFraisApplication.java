package com.leanovia.ms.noteDeFrais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.leanovia.ms.noteDeFrais.storage.StorageProperties;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class NoteDeFraisApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(NoteDeFraisApplication.class, args);
	}
}
