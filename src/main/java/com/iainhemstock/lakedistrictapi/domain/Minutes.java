package com.iainhemstock.lakedistrictapi.domain;

public class Minutes {
    private final int value;

    public Minutes(final int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(final Object other) {
        return value == ((Minutes) other).value;
    }
}
