package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain;

public enum LinkRel {
    PREV("prev"),
    SELF("self"),
    NEXT("next"),
    PARENT("parent");

    private String value;

    LinkRel(final String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
