package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class LinkServiceImpl implements LinkService {

    private final ApiProperties apiProperties;

    @Autowired
    public LinkServiceImpl(final ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    @Override
    public Link buildForResourceWithIdAndRel(final String resource, final String resourceId, final LinkRel rel) {
        if (!nonNull(resource)) throw new NullPointerException("Argument 'resource' cannot be null");
        if (resourceId.isBlank()) throw new IllegalArgumentException("Argument 'resourceId' cannot be blank");
        return new Link(rel, String.format("%s/%s/%s", apiProperties.getBaseUrl(), resource, resourceId));
    }
}
