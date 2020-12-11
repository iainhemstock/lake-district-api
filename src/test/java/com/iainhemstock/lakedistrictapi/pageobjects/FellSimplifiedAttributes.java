package com.iainhemstock.lakedistrictapi.pageobjects;

public enum FellSimplifiedAttributes {

    FELL_NAME("$.items[%d].name"),
    FELL_REGION("$.items[%d].region"),
    FELL_SELF_HREF("$.items[%d].links.self.href");

    private final String value;

    FellSimplifiedAttributes(final String value) {
        this.value = value;
    }

    public String valueAt(final int i) {
        return String.format(value, i);
    }
}
