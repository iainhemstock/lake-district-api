package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.nonNull;

@Service
public class EndpointGenerator {

    private ApiProperties apiProperties;

    @Autowired
    public EndpointGenerator(final ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    public String generateForResourceWithId(final String resource, final int resourceId) {
        if (!nonNull(resource)) throw new NullPointerException("Argument 'resource' cannot be null");
        if (resourceId <= 0) throw new IllegalArgumentException("Argument 'resourceId' cannot be negative");

        return String.format("%s/%s/%d", apiProperties.getBaseUrl(), resource, resourceId);
    }
}
