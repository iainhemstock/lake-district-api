package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.Link;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkRel;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
@JsonPropertyOrder({"links", "offset", "limit", "total_items", "items"})
public class PagedCollectionDTO<T> {

    private final Map<LinkRel, Link> links;
    private final Set<T> items;
    private final String offset;
    private final String limit;
    private final String total_items;

    public PagedCollectionDTO(final Map<LinkRel, Link> links,
                              final Set<T> items,
                              final int offset,
                              final int limit,
                              final int totalItems) {
        this.links = links;
        this.items = items;
        this.offset = String.valueOf(offset);
        this.limit = String.valueOf(limit);
        this.total_items = String.valueOf(totalItems);
    }
}
