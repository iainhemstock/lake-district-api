package com.iainhemstock.lakedistrictapi.domain;

public enum LinkRel {
    FIRST("first"),
    PREV("prev"),
    SELF("self"),
    NEXT("next"),
    LAST("last"),
    PARENT("parent");

    private String value;

    LinkRel(final String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
