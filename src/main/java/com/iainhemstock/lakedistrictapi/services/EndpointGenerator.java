package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class EndpointGenerator {

    private final ApiProperties apiProperties;

    @Autowired
    public EndpointGenerator(final ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    public String generateForResourceWithId(final String resource, final String resourceId) {
        if (!nonNull(resource)) throw new NullPointerException("Argument 'resource' cannot be null");
        if (resourceId.isBlank()) throw new IllegalArgumentException("Argument 'resourceId' cannot be blank");

        return String.format("%s/%s/%s", apiProperties.getBaseUrl(), resource, resourceId);
    }
}
