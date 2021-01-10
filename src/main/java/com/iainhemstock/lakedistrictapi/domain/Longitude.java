package com.iainhemstock.lakedistrictapi.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
public class Longitude {

    private final double value;

    public double toDouble() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return value == ((Longitude) other).value;
    }
}
