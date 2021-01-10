package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
public class ClassificationName {
    private final String value;

    public ClassificationName(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
