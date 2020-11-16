package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public final class DmsCoordsDTO {
    private final DmsDTO convertedLatitude;
    private final DmsDTO convertedLongitude;

    public DmsCoordsDTO(DmsDTO convertedLatitude, DmsDTO convertedLongitude) {
        this.convertedLatitude = convertedLatitude;
        this.convertedLongitude = convertedLongitude;
    }

    @JsonGetter("converted_longitude")
    public DmsDTO getConvertedLongitude() {
        return convertedLongitude;
    }

    @JsonGetter("converted_latitude")
    public DmsDTO getConvertedLatitude() {
        return convertedLatitude;
    }

    @Override
    public String toString() {
        return "DmsCoordsDTO{" +
            "yDms=" + convertedLongitude +
            ", xDms=" + convertedLatitude +
            '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DmsCoordsDTO)) return false;
        DmsCoordsDTO that = (DmsCoordsDTO) o;
        return Objects.equals(convertedLongitude, that.convertedLongitude) &&
                Objects.equals(convertedLatitude, that.convertedLatitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertedLongitude, convertedLatitude);
    }
}
