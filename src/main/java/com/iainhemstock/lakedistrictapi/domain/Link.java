package com.iainhemstock.lakedistrictapi.domain;

public class Link {
    private final String href;

    public Link(final String href) {
        this.href = href;
    }

    public String toString() {
        return this.href;
    }

    @Override
    public boolean equals(final Object other) {
        return href.equals(((Link) other).href);
    }
}
