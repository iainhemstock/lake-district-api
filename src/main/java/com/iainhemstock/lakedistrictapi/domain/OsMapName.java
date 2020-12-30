package com.iainhemstock.lakedistrictapi.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode
public class OsMapName {
    private String value;

    @Override
    public String toString() {
        return value;
    }
}
