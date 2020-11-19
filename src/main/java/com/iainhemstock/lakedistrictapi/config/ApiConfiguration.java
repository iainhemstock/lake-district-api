package com.iainhemstock.lakedistrictapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ApiConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

}
