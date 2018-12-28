package com.sti.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sti.bootcamp.config.DaoSpringConfig;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories({"com.sti.bootcamp.dao."})
@EntityScan({"com.sti.bootcamp.model"})
@Import({DaoSpringConfig.class})
@ComponentScan
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
