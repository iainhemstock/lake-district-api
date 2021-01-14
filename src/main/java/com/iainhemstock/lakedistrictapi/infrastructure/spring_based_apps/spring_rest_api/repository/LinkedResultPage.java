package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.Link;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPageMetaData;
import lombok.Getter;

import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
public class LinkedResultPage<T> extends ResultPage<T> {
    private final EnumMap<LinkRel, Link> links = new EnumMap<>(LinkRel.class);

    public LinkedResultPage(final Set<T> items,
                            final ResultPageMetaData metaData,
                            final int totalItems,
                            final String baseUrl,
                            final boolean hasPreviousPage,
                            final ResultPageMetaData prevPageMetaData,
                            final boolean hasNextPage,
                            final ResultPageMetaData nextPageMetaData) {
        super(metaData, totalItems, items, hasPreviousPage, prevPageMetaData, hasNextPage, nextPageMetaData);

        if (hasPreviousPage)
            this.links.put(LinkRel.PREV,
                new Link(LinkRel.PREV, String.format("%s?offset=%d&limit=%d",
                    baseUrl, prevPageMetaData.getOffset(), prevPageMetaData.getLimit())));

        this.links.put(LinkRel.SELF,
            new Link(LinkRel.SELF,
                String.format("%s?offset=%d&limit=%d", baseUrl, this.getMetaData().getOffset(), this.getMetaData().getLimit())));

        if (hasNextPage)
            this.links.put(LinkRel.NEXT,
                new Link(LinkRel.NEXT, String.format("%s?offset=%d&limit=%d",
                    baseUrl, nextPageMetaData.getOffset(), nextPageMetaData.getLimit())));
    }
}
