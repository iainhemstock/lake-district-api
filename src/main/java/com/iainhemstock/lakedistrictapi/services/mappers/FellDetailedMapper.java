package com.iainhemstock.lakedistrictapi.services.mappers;

import com.iainhemstock.lakedistrictapi.dtos.FellDetailedDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PROTECTED)
public abstract class FellDetailedMapper {
    private Fell fell;
    private FellDetailedDTO dto;

    public final FellDetailedDTO map(Fell fell) {
        this.fell = fell;
        this.dto = new FellDetailedDTO();

        mapFellName();
        mapHeight();
        mapProminence();
        mapParentPeak();
        mapClassifications();
        mapLatitude();
        mapLongitude();
        mapDms();
        mapRegion();
        mapOsMapRef();
        mapOsMaps();
        mapFellUrl();

        return this.dto;
    }

    abstract protected void mapFellUrl();
    abstract protected void mapLatitude();
    abstract protected void mapLongitude();
    abstract protected void mapDms();
    abstract protected void mapRegion();
    abstract protected void mapOsMapRef();
    abstract protected void mapOsMaps();
    abstract protected void mapClassifications();
    abstract protected void mapParentPeak();
    abstract protected void mapProminence();
    abstract protected void mapHeight();
    abstract protected void mapFellName();
}
