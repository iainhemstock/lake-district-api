package com.iainhemstock.lakedistrictapi.domain;

public class ClassificationName {
    private final String value;

    public ClassificationName(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return value.equals(((ClassificationName) other).value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
