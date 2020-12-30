package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class ClassificationName {
    private String value;

    public ClassificationName(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
