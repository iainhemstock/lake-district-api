package com.iainhemstock.lakedistrictapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SimpleFellDTO {
    private final String name;
    private final String region;
    private final LinksDTO links;
}
