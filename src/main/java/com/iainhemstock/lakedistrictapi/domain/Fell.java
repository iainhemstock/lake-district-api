package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Fell {
    private OsMapRef osMapRef;
    private OsMapRef parentOsMapRef;
    private FellName name;
    private Meters heightMeters;
    private Feet heightFeet;
    private Meters prominenceMeters;
    private Feet prominenceFeet;
    private Set<ClassificationName> classificationNames;
    private Set<OsMapName> osMapNames;
    private Latitude latitude;
    private Longitude longitude;
    private RegionName regionName;
    private DMS convertedLatitude;
    private DMS convertedLongitude;
}
