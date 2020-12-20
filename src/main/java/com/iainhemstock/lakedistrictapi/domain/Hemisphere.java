package com.iainhemstock.lakedistrictapi.domain;

public class Hemisphere {
    private final String value;

    public Hemisphere(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(final Object other) {
        return value.equals(((Hemisphere) other).value);
    }
}
