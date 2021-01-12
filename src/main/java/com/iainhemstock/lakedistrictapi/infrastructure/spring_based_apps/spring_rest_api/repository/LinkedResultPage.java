package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.Link;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPageMetaData;
import lombok.Getter;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Getter
public class LinkedResultPage<T> extends ResultPage<T> {
    private final EnumMap<LinkRel, Link> links;

    public LinkedResultPage(final Set<T> items, final ResultPageMetaData metaData, final int totalItems, final String baseUrl) {
        super(metaData, totalItems, items);
        this.links = new EnumMap<>(LinkRel.class);

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
}
