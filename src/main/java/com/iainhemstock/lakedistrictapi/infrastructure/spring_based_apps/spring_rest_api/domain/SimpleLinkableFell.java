package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
public class SimpleLinkableFell {
    private final String name;
    private final String region;
    private final Map<LinkRel, Link> links;

    public SimpleLinkableFell(final Fell fell, final Map<LinkRel, Link> links) {
        this.name = fell.getName().toString();
        this.region = fell.getRegionName().toString();
        this.links = links;
    }
}
