package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.Link;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPageRequest;
import lombok.Getter;

import java.util.EnumMap;
import java.util.Set;

@Getter
public class LinkedResultPage<T> extends ResultPage<T> {
    private final EnumMap<LinkRel, Link> links = new EnumMap<>(LinkRel.class);

    public LinkedResultPage(final Set<T> items,
                            final ResultPageRequest pageRequest,
                            final int totalItems,
                            final String baseUrl,
                            final ResultPageRequest prevResultPageRequest,
                            final ResultPageRequest nextResultPageRequest) {
        super(pageRequest, totalItems, items, prevResultPageRequest, nextResultPageRequest);

        if (this.hasPrevPage()) {
            this.links.put(LinkRel.PREV,
                new Link(LinkRel.PREV, String.format("%s?offset=%d&limit=%d",
                    baseUrl, prevResultPageRequest.getOffset(), prevResultPageRequest.getLimit())));
        }

        this.links.put(LinkRel.SELF,
            new Link(LinkRel.SELF,
                String.format("%s?offset=%d&limit=%d", baseUrl, this.getPageRequest().getOffset(), this.getPageRequest().getLimit())));

        if (this.hasNextPage()) {
            this.links.put(LinkRel.NEXT,
                new Link(LinkRel.NEXT, String.format("%s?offset=%d&limit=%d",
                    baseUrl, nextResultPageRequest.getOffset(), nextResultPageRequest.getLimit())));
        }
    }
}
