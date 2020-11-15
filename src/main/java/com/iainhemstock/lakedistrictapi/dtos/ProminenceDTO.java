package com.iainhemstock.lakedistrictapi.dtos;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public final class ProminenceDTO {
    private String meters;
    private String feet;

    public ProminenceDTO(String meters, String feet) {
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
        return "ProminenceDTO{" +
            "meters=" + meters +
            ", feet=" + feet +
            '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ProminenceDTO)) return false;
        ProminenceDTO that = (ProminenceDTO) o;
        return meters == that.meters &&
                feet == that.feet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meters, feet);
    }
}
