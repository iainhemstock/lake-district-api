package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@JsonPropertyOrder({"feet", "meters"})
public final class ProminenceDto {
    private String feet;
    private String meters;

    public ProminenceDto(String meters, String feet) {
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
        if (!(o instanceof ProminenceDto)) return false;
        ProminenceDto that = (ProminenceDto) o;
        return meters == that.meters &&
                feet == that.feet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meters, feet);
    }
}
