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
}
