package com.iainhemstock.lakedistrictapi.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@TestConfiguration
public class TestApiConfiguration {

    public static final String NOW = "2018-12-10T13:45:00.00Z";

    @Bean
    public Clock clock() {
        return Clock.fixed(Instant.parse(NOW), ZoneId.of("UTC"));
    }

}
