package com.iainhemstock.lakedistrictapi.domain;

public class Seconds {
    private final int value;

    public Seconds(final int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(final Object other) {
        return value == ((Seconds) other).value;
    }
}
