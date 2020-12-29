package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedCollectionDTO<T> {

    private final Links links;
    private final RepoPage<T> items;
    private final String offset;
    private final String limit;
    private final String total_items;

    public PagedCollectionDTO(final Links links, final RepoPage<T> items) {
        this.links = links;
        this.items = items;
        this.offset = String.valueOf(items.getOffset());
        this.limit = String.valueOf(items.getLimit());
        this.total_items = String.valueOf(items.getTotalItemsAvailable());
    }
}
