package com.iainhemstock.lakedistrictapi.domain;

import lombok.Getter;

@Getter
public class Height {
    private Meters meters;
    private Feet feet;

    public Height(final Meters meters, final Feet feet) {
        this.meters = meters;
        this.feet = feet;
    }

    public String toString() {
        return String.format("Height: [Meters: %s, Feet: %s]", meters, feet);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Height height = (Height) o;
        if (meters != null ? !meters.equals(height.meters) : height.meters != null) return false;
        return feet != null ? feet.equals(height.feet) : height.feet == null;
    }

    @Override
    public int hashCode() {
        int result = meters != null ? meters.hashCode() : 0;
        result = 31 * result + (feet != null ? feet.hashCode() : 0);
        return result;
    }
}
