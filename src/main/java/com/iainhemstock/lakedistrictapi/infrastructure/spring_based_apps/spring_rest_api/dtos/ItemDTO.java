package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import com.iainhemstock.lakedistrictapi.domain.Links;
import lombok.Getter;

@Getter
public class ItemDTO<T> {
    private final Links links;
    private final T item;

    public ItemDTO(final Links links, final T Item) {
        this.links = links;
        item = Item;
    }
}
