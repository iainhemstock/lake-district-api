package com.iainhemstock.lakedistrictapi.domain;

public class RegionName {
    private final String value;

    public RegionName(final String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return value.equals(((RegionName) other).value);
    }
}
