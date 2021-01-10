package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Meters {
    private final int value;

    public Meters(final int value) {
        this.value = value;
    }

    public int toInt() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
