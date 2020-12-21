package com.iainhemstock.lakedistrictapi.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Longitude {

    @Column(name = "longitude")
    @NotNull
    private double value;

    public Longitude(final double value) {
        this.value = value;
    }

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
