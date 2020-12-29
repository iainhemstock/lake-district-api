package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization.ItemDTOSerializer;
import lombok.Getter;

@Getter
@JsonSerialize(using = ItemDTOSerializer.class)
public class ItemDTO {
    private Links links;
    private Fell fell;

    public ItemDTO(final Links links, final Fell fell) {
        this.links = links;
        this.fell = fell;
    }
}
