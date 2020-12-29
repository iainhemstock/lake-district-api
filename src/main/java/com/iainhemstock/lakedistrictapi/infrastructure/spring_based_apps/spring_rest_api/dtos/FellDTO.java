package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class FellDTO {
    private String heightMeters;
    private String heightFeet;
    private String prominenceMeters;
    private String prominenceFeet;
    private String osMapRef;
    private String latitude;
    private String longitude;
    private Map<String, String> latitudeAsDms;
    private Map<String, String> longitudeAsDms;
    private String region;
    private Set<String> osMapNames;
    private Set<String> classificationNames;
    private String name;
}


