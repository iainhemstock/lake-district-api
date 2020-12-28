package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedCollectionDTO<T> {
    private LinksDTO links = new LinksDTO();
    private String offset;
    private String limit;
    private String total_items;
    private T items;

    public PagedCollectionDTO() {
    }

    public PagedCollectionDTO(final LinksDTO linksDTO,
                              final T items,
                              final String offset,
                              final String limit,
                              final String totalItems) {
        this.links = linksDTO;
        this.items = items;
        this.offset = offset;
        this.limit = limit;
        this.total_items = totalItems;
    }
}
