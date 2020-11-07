package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public final class DmsCoordsDTO {
    private final DmsDTO yDms;
    private final DmsDTO xDms;

    public DmsCoordsDTO(DmsDTO yDms, DmsDTO xDms) {
        this.yDms = yDms;
        this.xDms = xDms;
    }

    @JsonGetter("y")
    public DmsDTO getyDms() {
        return yDms;
    }

    @JsonGetter("x")
    public DmsDTO getxDms() {
        return xDms;
    }

    @Override
    public String toString() {
        return "DmsCoordsDTO{" +
            "yDms=" + yDms +
            ", xDms=" + xDms +
            '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof DmsCoordsDTO)) return false;
        DmsCoordsDTO that = (DmsCoordsDTO) o;
        return Objects.equals(yDms, that.yDms) &&
                Objects.equals(xDms, that.xDms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yDms, xDms);
    }
}
