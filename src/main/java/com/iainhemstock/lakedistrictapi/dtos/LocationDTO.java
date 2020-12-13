package com.iainhemstock.lakedistrictapi.dtos;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class LocationDTO {
    private String latitude;
    private String longitude;
    private List<Map<String, String>> dms;
    private Map<String, String> dmsLatitude;
    private Map<String, String> dmsLongitude;
    private String region;
    private String os_map_ref;
    private Set<String> os_maps;
}
