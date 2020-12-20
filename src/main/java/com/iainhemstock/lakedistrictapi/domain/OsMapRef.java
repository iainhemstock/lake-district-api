package com.iainhemstock.lakedistrictapi.domain;

public class OsMapRef  {
    private final String value;

    public OsMapRef(final String value) {
        if (value == null) throw new NullPointerException("OsMapRef cannot be null");
        if (value.equals("")) throw new IllegalArgumentException("OsMapRef cannot be blank");
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return value.equals(((OsMapRef) other).value);
    }
}
