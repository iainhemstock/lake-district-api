package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.List;
import java.util.Set;

@Embeddable
@Getter
@JsonPropertyOrder({"decimal_coords", "dms_coords", "region", "os_map_ref", "os_maps"})
public final class LocationDto {
    private DecimalCoordsDto decimalCoords;
    private List<DmsDto> dmsCoords;
    private Set<String> osMaps;
    private String osMapRef;
    private String region;

    public LocationDto(DecimalCoordsDto decimalCoords,
                       List<DmsDto> dmsCoords,
                       String region,
                       String osMapRef,
                       Set<String> osMaps) {
        this.decimalCoords = decimalCoords;
        this.dmsCoords = dmsCoords;
        this.region = region;
        this.osMapRef = osMapRef;
        this.osMaps = osMaps;
    }

    @JsonGetter("decimal_coords")
    public DecimalCoordsDto getDecimalCoords() {
        return decimalCoords;
    }

    @JsonGetter("dms_coords")
    public List<DmsDto> getDmsCoords() {
        return dmsCoords;
    }

    @JsonGetter("os_map_ref")
    public String getOsMapRef() {
        return osMapRef;
    }

    @JsonGetter("os_maps")
    public Set<String> getOsMaps() {
        return osMaps;
    }

    @Override
    public String toString() {
        return "LocationDto{" +
            "decimalCoords=" + decimalCoords +
            ", dmsCoords=" + dmsCoords +
            ", region='" + region + '\'' +
            ", osMapRef='" + osMapRef + '\'' +
            ", osMaps=" + osMaps +
            '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationDto that = (LocationDto) o;
        if (decimalCoords != null ? !decimalCoords.equals(that.decimalCoords) : that.decimalCoords != null)
            return false;
        if (dmsCoords != null ? !dmsCoords.equals(that.dmsCoords) : that.dmsCoords != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (osMapRef != null ? !osMapRef.equals(that.osMapRef) : that.osMapRef != null) return false;
        return osMaps != null ? osMaps.equals(that.osMaps) : that.osMaps == null;
    }

    @Override
    public int hashCode() {
        int result = decimalCoords != null ? decimalCoords.hashCode() : 0;
        result = 31 * result + (dmsCoords != null ? dmsCoords.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (osMapRef != null ? osMapRef.hashCode() : 0);
        result = 31 * result + (osMaps != null ? osMaps.hashCode() : 0);
        return result;
    }
}
