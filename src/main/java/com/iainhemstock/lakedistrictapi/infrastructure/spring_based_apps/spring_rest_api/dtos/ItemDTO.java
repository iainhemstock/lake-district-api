package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import com.iainhemstock.lakedistrictapi.domain.Link;
import lombok.Getter;

import java.util.Set;

@Getter
public class ItemDTO<T> {
    private final Set<Link> links;
    private final T item;

    public ItemDTO(final Set<Link> links, final T Item) {
        this.links = links;
        item = Item;
    }
}
