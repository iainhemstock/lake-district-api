package com.iainhemstock.lakedistrictapi.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OsMapName {
    private String value;

    public OsMapName(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
