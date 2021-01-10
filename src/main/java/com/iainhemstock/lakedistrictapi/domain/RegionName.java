package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class RegionName {
    private final String value;

    public RegionName(final String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
