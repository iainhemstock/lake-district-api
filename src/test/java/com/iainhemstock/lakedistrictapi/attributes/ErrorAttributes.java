package com.iainhemstock.lakedistrictapi.attributes;

public enum ErrorAttributes {
    STATUS("$.status"),
    MESSAGE("$.message"),
    PATH("$.path"),
    TIMESTAMP("$.timestamp");

    private String value;

    ErrorAttributes(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
