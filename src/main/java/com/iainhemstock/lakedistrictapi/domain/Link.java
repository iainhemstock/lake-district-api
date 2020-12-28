package com.iainhemstock.lakedistrictapi.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Link {
    private final LinkRel rel;
    private final String href;

    public Link(final LinkRel rel, final String href) {
        this.rel = rel;
        this.href = href;
    }

    @Override
    public boolean equals(final Object other) {
        return href.equals(((Link) other).href);
    }
}
