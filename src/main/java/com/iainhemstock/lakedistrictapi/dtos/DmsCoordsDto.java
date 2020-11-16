package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public final class DmsCoordsDto {
    private final DmsDto convertedLatitude;
    private final DmsDto convertedLongitude;

    public DmsCoordsDto(DmsDto convertedLatitude, DmsDto convertedLongitude) {
        this.convertedLatitude = convertedLatitude;
        this.convertedLongitude = convertedLongitude;
    }

    @JsonGetter("converted_longitude")
    public DmsDto getConvertedLongitude() {
        return convertedLongitude;
    }

    @JsonGetter("converted_latitude")
    public DmsDto getConvertedLatitude() {
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
        if (!(o instanceof DmsCoordsDto)) return false;
        DmsCoordsDto that = (DmsCoordsDto) o;
        return Objects.equals(convertedLongitude, that.convertedLongitude) &&
                Objects.equals(convertedLatitude, that.convertedLatitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertedLongitude, convertedLatitude);
    }
}
