package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class FellName {
    private final String value;

    public FellName(final String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
