package com.iainhemstock.lakedistrictapi.dtos;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class LocationDTO {
    private String latitude;
    private String longitude;
    private DmsDTO dms_latitude;
    private DmsDTO dms_longitude;
    private String region;
    private String os_map_ref;
    private Set<String> os_maps;
}
