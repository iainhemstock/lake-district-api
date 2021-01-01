package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import lombok.Getter;

import java.util.Map;

@Getter
public class SimpleLinkedFell {
    private final String name;
    private final Map<LinkRel, Link> links;

    public SimpleLinkedFell(final Fell fell) {
        this.name = fell.getName().toString();
        this.links = Map.of(LinkRel.SELF,
            new Link(LinkRel.SELF, "http://localhost:8080/api/v1/fells/" + fell.getOsMapRef().toString()));
    }

}
