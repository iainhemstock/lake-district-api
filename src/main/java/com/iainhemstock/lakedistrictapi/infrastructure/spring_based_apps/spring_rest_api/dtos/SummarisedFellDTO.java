package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SummarisedFellDTO {
    private String name;
    private String region;
    private LinksDTO links = new LinksDTO();
}
