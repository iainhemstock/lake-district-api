package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@EqualsAndHashCode()
@ToString
public class SimpleFell {
    private final FellName name;
    private final RegionName regionName;
    private final Set<Link> links;

    public SimpleFell(final FellName name, final RegionName regionName, final Set<Link> links) {
        this.name = name;
        this.regionName = regionName;
        this.links = links;
    }
}
