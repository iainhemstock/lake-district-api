package com.iainhemstock.lakedistrictapi.domain;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Fell {
    private final OsMapRef osMapRef;
    private final OsMapRef parentOsMapRef;
    private final FellName name;
    private final Meters heightMeters;
    private final Feet heightFeet;
    private final Meters prominenceMeters;
    private final Feet prominenceFeet;
    private final Set<ClassificationName> immutableClassificationNames;
    private final Set<OsMapName> immutableOsMapNames;
    private final Latitude latitude;
    private final Longitude longitude;
    private final RegionName regionName;
    private final DMS convertedLatitude;
    private final DMS convertedLongitude;

    public Fell(final Fell fell) {
        this.osMapRef = fell.getOsMapRef();
        this.parentOsMapRef = fell.getParentOsMapRef();
        this.name = fell.getName();
        this.heightMeters = fell.getHeightMeters();
        this.heightFeet = fell.getHeightFeet();
        this.prominenceMeters = fell.getProminenceMeters();
        this.prominenceFeet = fell.getProminenceFeet();
        this.immutableClassificationNames = Set.copyOf(fell.getImmutableClassificationNames());
        this.immutableOsMapNames = Set.copyOf(fell.getImmutableOsMapNames());
        this.latitude = fell.getLatitude();
        this.longitude = fell.getLongitude();
        this.regionName = fell.getRegionName();
        this.convertedLatitude = fell.getConvertedLatitude();
        this.convertedLongitude = fell.getConvertedLongitude();
    }
}
