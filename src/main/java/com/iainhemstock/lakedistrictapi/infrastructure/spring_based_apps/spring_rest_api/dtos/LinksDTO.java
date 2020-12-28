package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization.LinksDTOSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonSerialize(using = LinksDTOSerializer.class)
public class LinksDTO {
    private Map<String, String> links;
}
