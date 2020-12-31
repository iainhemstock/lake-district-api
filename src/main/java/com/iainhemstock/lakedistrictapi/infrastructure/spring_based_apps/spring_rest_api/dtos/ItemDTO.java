package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.Link;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.LinkRel;
import lombok.Getter;

import java.util.Map;

@Getter
public class ItemDTO<T> {
    private final Map<LinkRel, Link> links;
    private final T item;

    public ItemDTO(final Map<LinkRel, Link> links, final T Item) {
        this.links = links;
        item = Item;
    }
}
