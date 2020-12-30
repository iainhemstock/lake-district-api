package com.iainhemstock.lakedistrictapi.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
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
}
