package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class LinksDTO {
    private Map<String, String> links;
}

