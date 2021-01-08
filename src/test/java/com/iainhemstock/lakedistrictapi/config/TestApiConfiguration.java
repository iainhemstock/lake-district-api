package com.iainhemstock.lakedistrictapi.config;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.ApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@TestConfiguration
public class TestApiConfiguration {

    public static final String NOW = "2018-12-10T13:45:00.00Z";
    private static final int DEFAULT_PAGE_OFFSET = 0;
    private static final int DEFAULT_PAGE_LIMIT = 1;
    private static final String BASE_URL = "http://localhost:8080/api/v1";

    @Bean
    public Clock clock() {
        return Clock.fixed(Instant.parse(NOW), ZoneId.of("UTC"));
    }

    @Bean
    public ApiProperties apiProperties() {
        ApiProperties properties = new ApiProperties();
        properties.setPageOffset(DEFAULT_PAGE_OFFSET);
        properties.setPageOffset(DEFAULT_PAGE_LIMIT);
        properties.setBaseUrl(BASE_URL);
        return properties;
    }

}
