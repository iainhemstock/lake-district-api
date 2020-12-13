package com.iainhemstock.lakedistrictapi.dtos;

//todo: delete this dummy class, only used to figure out how modelmapper works

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegionDTO {
    private int uuid;
    private String location;
    private String abbr;
    private int category;
    private HeightDTO height = new HeightDTO();
}
