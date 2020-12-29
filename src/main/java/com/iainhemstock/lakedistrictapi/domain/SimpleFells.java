package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@EqualsAndHashCode
public class SimpleFells {
    private Set<SimpleFell> simpleFells;

    public SimpleFells() {
        this.simpleFells = new LinkedHashSet<>();
    }

    public void add(final SimpleFell simpleFell) {
        this.simpleFells.add(simpleFell);
    }

    @Override
    public String toString() {
        return String.format("SimpleFells: %s", this.simpleFells);
    }
}
