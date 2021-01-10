package com.iainhemstock.lakedistrictapi.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class OsMapName {
    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
