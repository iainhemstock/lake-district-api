package com.iainhemstock.lakedistrictapi.application_logic;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.application_interfaces.LinkService;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.Link;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public Map<LinkRel, Link> buildNavLinksForPageAndCollectionType(final RepoPage<Fell> repoPage, final String collection) {
        if (repoPage.isEmpty())
            return Collections.EMPTY_MAP;

        Map<LinkRel, Link> linksMap = new LinkedHashMap<>();

        if (repoPage.hasPrevious())
            linksMap.put(LinkRel.PREV, buildLink(collection, LinkRel.PREV, repoPage.getPrevOffset(), repoPage.getLimit()));

        linksMap.put(LinkRel.SELF, buildLink(collection, LinkRel.SELF, repoPage.getOffset(), repoPage.getLimit()));

        if (repoPage.hasNext())
            linksMap.put(LinkRel.NEXT, buildLink(collection, LinkRel.NEXT, repoPage.getNextOffset(), repoPage.getLimit()));

        return linksMap;
    }

    private Link buildLink(final String collection, final LinkRel rel, final long offset, final int limit) {
        return new Link(rel, String.format("%s/%s?offset=%d&limit=%d",
            apiProperties.getBaseUrl(), collection, offset, limit));
    }
}
