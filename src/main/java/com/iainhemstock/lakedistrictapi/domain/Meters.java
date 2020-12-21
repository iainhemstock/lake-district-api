package com.iainhemstock.lakedistrictapi.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Meters {
    private int value;

    public Meters(final int value) {
        this.value = value;
    }

    public int toInt() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meters meters = (Meters) o;
        return value == meters.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
