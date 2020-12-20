package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.domain.Link;

public interface EndpointGeneratorService {
    Link generateForResourceWithId(final String resource, final String resourceId);
}
