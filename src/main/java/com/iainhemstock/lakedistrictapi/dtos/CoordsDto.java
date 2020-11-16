package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Embeddable
public final class CoordsDto {
    @Embedded private DecimalCoordsDto decimalCoords;
    private DmsCoordsDto dmsCoords;

    public CoordsDto(DecimalCoordsDto decimalCoords, DmsCoordsDto dmsCoords) {
        this.decimalCoords = decimalCoords;
        this.dmsCoords = dmsCoords;
    }

    @JsonGetter("decimal")
    public DecimalCoordsDto getDecimalCoords() {
        return decimalCoords;
    }

    @JsonGetter("dms")
    public DmsCoordsDto getDmsCoords() {
        return dmsCoords;
    }

    @Override
    public String toString() {
        return "CoordsDTO{" +
            "decimalCoords=" + decimalCoords +
            ", dmsCoords=" + dmsCoords +
            '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof CoordsDto)) return false;
        CoordsDto coordsDTO = (CoordsDto) o;
        return Objects.equals(decimalCoords, coordsDTO.decimalCoords) &&
                Objects.equals(dmsCoords, coordsDTO.dmsCoords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(decimalCoords, dmsCoords);
    }
}
