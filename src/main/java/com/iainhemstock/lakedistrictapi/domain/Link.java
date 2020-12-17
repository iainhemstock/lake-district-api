package com.iainhemstock.lakedistrictapi.domain;

public class Link {
    private final String href;

    public Link(final String href) {
        this.href = href;
    }

    public String toString() {
        return this.href;
    }
}
