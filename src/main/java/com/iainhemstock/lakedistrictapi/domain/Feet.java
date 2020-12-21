package com.iainhemstock.lakedistrictapi.domain;

public class Feet {
    private final int value;

    public Feet(final int value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feet feet = (Feet) o;
        return value == feet.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
