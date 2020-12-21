package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iainhemstock.lakedistrictapi.serializers.ItemDTOSerializer;
import lombok.Getter;

@Getter
@JsonSerialize(using = ItemDTOSerializer.class)
public class ItemDTO {
    private final LinksDTO linksDTO;
    private final DetailedFellDTO detailedFellDTO;

    public ItemDTO(final LinksDTO linksDTO, final DetailedFellDTO detailedFellDTO) {
        this.linksDTO = linksDTO;
        this.detailedFellDTO = detailedFellDTO;
    }
}