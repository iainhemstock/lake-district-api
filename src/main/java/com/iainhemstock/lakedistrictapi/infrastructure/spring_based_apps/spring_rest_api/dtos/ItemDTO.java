package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
public class ItemDTO<T> {
    private final Map<LinkRel, Link> links;
    private final T item;

    public ItemDTO(final Map<LinkRel, Link> links, final T Item) {
        this.links = links;
        item = Item;
    }
}
