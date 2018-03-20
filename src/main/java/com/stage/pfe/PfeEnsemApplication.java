package com.stage.pfe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import com.stage.pfe.storage.StorageProperties;


@ComponentScan(basePackages="com.stage.pfe")
@EnableConfigurationProperties(StorageProperties.class)
@EnableJpaRepositories(basePackages="com.stage.pfe.dao")
@SpringBootApplication
@EnableOAuth2Sso
public class PfeEnsemApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PfeEnsemApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PfeEnsemApplication.class, args);
    }

}