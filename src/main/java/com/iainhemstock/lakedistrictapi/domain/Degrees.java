package com.iainhemstock.lakedistrictapi.domain;

public class Degrees {
    private final int value;

    public Degrees(final int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(final Object other) {
        return value == ((Degrees) other).value;
    }
}
