package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")
@Setter
@Getter
public class ApiProperties {
    private String baseUrl;
}
