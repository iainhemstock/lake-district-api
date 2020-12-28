package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.application_interfaces.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Override
    public Links buildNavLinksForPageAndCollectionType(final Page<FellEntity> page, final String collection) {
        if (page.getPageable().isUnpaged())
            return Links.empty();

        Links links = new Links();

        if (page.hasPrevious())
            links.add(buildLink(collection, LinkRel.PREV, page.previousPageable().getOffset(), page.previousPageable().getPageSize()));

        links.add(buildLink(collection, LinkRel.SELF, page.getPageable().getOffset(), page.getPageable().getPageSize()));

        if (page.hasNext())
            links.add(buildLink(collection, LinkRel.NEXT, page.nextPageable().getOffset(), page.nextPageable().getPageSize()));

        return links;
    }

    private Link buildLink(final String collection, final LinkRel rel, final long offset, final int limit) {
        return new Link(rel, String.format("%s/%s?offset=%d&limit=%d",
            apiProperties.getBaseUrl(), collection, offset, limit));
    }
}
