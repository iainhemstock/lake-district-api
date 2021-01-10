package com.iainhemstock.lakedistrictapi.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Latitude {

    private final double value;

    public double toDouble() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(final Object other) {
        return value == ((Latitude)other).value;
    }
}
