package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode()
@ToString
public class SimpleFell {
    private final FellName name;
    private final RegionName regionName;
    private final Links links;

    public SimpleFell(final FellName name, final RegionName regionName, final Links links) {
        this.name = name;
        this.regionName = regionName;
        this.links = links;
    }
}
