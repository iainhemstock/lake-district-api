package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository.FellNameAndOsMapRefOnly;
import lombok.Getter;

import java.util.Map;

@Getter
public class SimpleLinkedFell {
    private final String name;
    private final Map<LinkRel, Link> links;

    public SimpleLinkedFell(final FellNameAndOsMapRefOnly projection, final String baseUrl) {
        this.name = projection.getName();
        this.links = Map.of(LinkRel.SELF,
            new Link(LinkRel.SELF, String.format("%s/fells/%s", baseUrl, projection.getOsMapRef())));
    }

}
