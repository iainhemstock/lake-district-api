package com.iainhemstock.lakedistrictapi.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
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
