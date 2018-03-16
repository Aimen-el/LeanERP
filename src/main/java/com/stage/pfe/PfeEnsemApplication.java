package com.stage.pfe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.stage.pfe.storage.StorageService;
import com.stage.pfe.storage.StorageProperties;

@EnableConfigurationProperties(StorageProperties.class)
@EnableJpaRepositories(basePackages="com.stage.pfe.dao")
@SpringBootApplication
public class PfeEnsemApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PfeEnsemApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PfeEnsemApplication.class, args);
    }

}