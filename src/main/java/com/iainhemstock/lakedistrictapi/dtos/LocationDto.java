package com.iainhemstock.lakedistrictapi.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;
import java.util.Set;

@Embeddable
public final class LocationDto {
    @Embedded private CoordsDto coords;
    private String regionUri;
    private String osMapRef;
    private Set<String> osMaps;

    public LocationDto(CoordsDto coords, String regionUri, String osMapRef, Set<String> osMaps) {
        this.coords = coords;
        this.regionUri = regionUri;
        this.osMapRef = osMapRef;
        this.osMaps = osMaps;
    }

    public CoordsDto getCoords() {
        return coords;
    }

    @JsonGetter("region")
    public String getRegionUri() {
        return regionUri;
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
        return "LocationDTO{" +
            "coords=" + coords +
            ", region='" + regionUri + '\'' +
            ", osMapRef='" + osMapRef + '\'' +
            ", osMaps=" + osMaps +
            '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationDto)) return false;
        LocationDto that = (LocationDto) o;
        return Objects.equals(coords, that.coords) &&
                Objects.equals(regionUri, that.regionUri) &&
                Objects.equals(osMapRef, that.osMapRef) &&
                osMaps.equals(that.osMaps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coords, regionUri, osMapRef, osMaps);
    }
}
