package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class LinkedFell {
    private final Map<LinkRel, Link> links;
    private final Fell fell;

    public LinkedFell(final Fell fell, final String baseUrl) {
        this.fell = fell;
        this.links = new LinkedHashMap<>();
        this.links.put(LinkRel.SELF, new Link(LinkRel.SELF, baseUrl + "/fells/" + this.fell.getOsMapRef()));
        if (fell.getParentOsMapRef() != null)
            this.links.put(LinkRel.PARENT, new Link(LinkRel.PARENT, baseUrl + "/fells/" + this.fell.getParentOsMapRef()));
    }
}
