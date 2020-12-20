package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.EndpointGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class EndpointGeneratorServiceImpl implements EndpointGeneratorService {

    private final ApiProperties apiProperties;

    @Autowired
    public EndpointGeneratorServiceImpl(final ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    public Link generateForResourceWithId(final String resource, final String resourceId) {
        if (!nonNull(resource)) throw new NullPointerException("Argument 'resource' cannot be null");
        if (resourceId.isBlank()) throw new IllegalArgumentException("Argument 'resourceId' cannot be blank");

        return new Link(String.format("%s/%s/%s", apiProperties.getBaseUrl(), resource, resourceId));
    }
}
