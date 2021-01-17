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
    private static final String URL_PATTERN = "%s?offset=%d&limit=%d&sort=%s";

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
                new Link(LinkRel.PREV, String.format(URL_PATTERN,
                    baseUrl, prevResultPageRequest.getOffset(), prevResultPageRequest.getLimit(), prevResultPageRequest.getSort())));
        }

        this.links.put(LinkRel.SELF,
            new Link(LinkRel.SELF, String.format(URL_PATTERN,
                baseUrl, this.getPageRequest().getOffset(), this.getPageRequest().getLimit(), this.getPageRequest().getSort())));

        if (this.hasNextPage()) {
            this.links.put(LinkRel.NEXT,
                new Link(LinkRel.NEXT, String.format(URL_PATTERN,
                    baseUrl, nextResultPageRequest.getOffset(), nextResultPageRequest.getLimit(), nextResultPageRequest.getSort())));
        }
    }
}
