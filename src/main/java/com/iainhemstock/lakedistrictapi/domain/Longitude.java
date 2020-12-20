package com.iainhemstock.lakedistrictapi.domain;

public class Longitude {
    private final double value;

    public Longitude(final double value) {
        this.value = value;
    }

    public double toDouble() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return value == ((Longitude) other).value;
    }
}
