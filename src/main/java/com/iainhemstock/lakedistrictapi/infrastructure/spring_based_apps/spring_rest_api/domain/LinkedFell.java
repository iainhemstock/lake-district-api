package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class LinkedFell extends Fell implements LinkedBasicFell {
    private final Map<LinkRel, Link> links = new LinkedHashMap<>();

    public LinkedFell(final Fell fell, final String baseUrl) {
        super(fell);
        addSelfLink(baseUrl);
        addParentLinkIfExists(baseUrl);
    }

    private void addSelfLink(final String baseUrl) {
        this.links.put(LinkRel.SELF, new Link(LinkRel.SELF, baseUrl + "/fells/" + this.getOsMapRef()));
    }

    private void addParentLinkIfExists(final String baseUrl) {
        if (this.getParentOsMapRef() == null) return;
        this.links.put(LinkRel.PARENT, new Link(LinkRel.PARENT, baseUrl + "/fells/" + this.getParentOsMapRef()));
    }
}
