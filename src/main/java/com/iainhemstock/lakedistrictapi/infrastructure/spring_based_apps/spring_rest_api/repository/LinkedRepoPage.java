package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.Link;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPageMetaData;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Getter
public class LinkedRepoPage<T> {
    private final Map<LinkRel, Link> links;
    private final RepoPageMetaData metaData;
    private final int totalItems;
    private final Set<T> items;

    public LinkedRepoPage(final Set<T> items, final RepoPageMetaData metaData, final int totalItems, final String baseUrl) {
        this.links = new LinkedHashMap<>();
        this.metaData = metaData;
        this.totalItems = totalItems;
        this.items = items;

        if (metaData.getOffset() > 0)
            this.links.put(LinkRel.PREV,
                new Link(LinkRel.PREV, String.format("%s?offset=%d&limit=%d",
                    baseUrl, metaData.getOffset() - 1, metaData.getLimit())));

        this.links.put(LinkRel.SELF,
            new Link(LinkRel.SELF,
                String.format("%s?offset=%d&limit=%d", baseUrl, metaData.getOffset(), metaData.getLimit())));

        if (metaData.getOffset() + items.size() < totalItems)
            this.links.put(LinkRel.NEXT,
                new Link(LinkRel.NEXT, String.format("%s?offset=%d&limit=%d",
                    baseUrl, metaData.getOffset() + 1, metaData.getLimit())));
    }

    public int getOffset() {
        return this.metaData.getOffset();
    }

    public int getLimit() {
        return this.metaData.getLimit();
    }
}
