package com.iainhemstock.lakedistrictapi.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class RegionName {
    private String value;

    public RegionName(final String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
