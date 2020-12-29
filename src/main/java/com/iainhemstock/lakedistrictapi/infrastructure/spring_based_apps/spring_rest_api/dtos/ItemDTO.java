package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization.ItemDTOSerializer;
import lombok.Getter;

@Getter
@JsonSerialize(using = ItemDTOSerializer.class)
public class ItemDTO {
    private LinksDTO linksDTO;
    private FellDTO fellDTO;

    public ItemDTO(final LinksDTO linksDTO, final FellDTO fellDTO) {
        this.linksDTO = linksDTO;
        this.fellDTO = fellDTO;
    }
}
