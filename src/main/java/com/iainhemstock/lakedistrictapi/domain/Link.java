package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Link {
    private final LinkRel rel;
    private final String href;

    public Link(final LinkRel rel, final String href) {
        this.rel = rel;
        this.href = href;
    }
}
