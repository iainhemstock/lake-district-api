package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Embeddable
public final class CoordsDTO {
    @Embedded private DecimalCoordsDTO decimalCoords;
    private DmsCoordsDTO dmsCoords;

    public CoordsDTO(DecimalCoordsDTO decimalCoords, DmsCoordsDTO dmsCoords) {
        this.decimalCoords = decimalCoords;
        this.dmsCoords = dmsCoords;
    }

    @JsonGetter("decimal")
    public DecimalCoordsDTO getDecimalCoords() {
        return decimalCoords;
    }

    @JsonGetter("dms")
    public DmsCoordsDTO getDmsCoords() {
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
        if (!(o instanceof CoordsDTO)) return false;
        CoordsDTO coordsDTO = (CoordsDTO) o;
        return Objects.equals(decimalCoords, coordsDTO.decimalCoords) &&
                Objects.equals(dmsCoords, coordsDTO.dmsCoords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(decimalCoords, dmsCoords);
    }
}
