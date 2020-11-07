package com.iainhemstock.lakedistrictapi.dtos;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public final class HeightDTO {
    private int meters;
    private int feet;

    public HeightDTO(int meters, int feet) {
        this.meters = meters;
        this.feet = feet;
    }

    public int getMeters() {
        return meters;
    }

    public int getFeet() {
        return feet;
    }

    @Override
    public String toString() {
        return "HeightDTO{" +
            "meters=" + meters +
            ", feet=" + feet +
            '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof HeightDTO)) return false;
        HeightDTO heightDTO = (HeightDTO) o;
        return meters == heightDTO.meters &&
                feet == heightDTO.feet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meters, feet);
    }
}
