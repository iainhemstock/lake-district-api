package com.iainhemstock.lakedistrictapi.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegionName {
    private String value;

    public RegionName(final String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
