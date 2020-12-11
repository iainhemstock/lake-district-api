package com.iainhemstock.lakedistrictapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FellSimplifiedDTO {
    private String name;
    private String region;
    private LinksDTO links = new LinksDTO();
}
