package com.iainhemstock.lakedistrictapi.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class Link {
    private String href;

    public Link(final String href) {
        this.href = href;
    }

    public String toString() {
        return this.href;
    }
}
