package com.iainhemstock.lakedistrictapi.dtos;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public final class HeightDTO {
    private String meters;
    private String feet;

    public HeightDTO(String meters, String feet) {
        this.meters = meters;
        this.feet = feet;
    }

    public String getMeters() {
        return meters;
    }

    public String getFeet() {
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
