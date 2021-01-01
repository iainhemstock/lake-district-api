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

//    public Fell(final Fell fell) {
//        this.osMapRef = fell.getOsMapRef();
//        this.parentOsMapRef = fell.getParentOsMapRef();
//        this.name = fell.getName();
//        this.heightMeters = fell.getHeightMeters();
//        this.heightFeet = fell.getHeightFeet();
//        this.prominenceMeters = fell.getProminenceMeters();
//        this.prominenceFeet = fell.getProminenceFeet();
//        this.classificationNames = Set.copyOf(fell.getClassificationNames());
//        this.osMapNames = Set.copyOf(fell.getOsMapNames());
//        this.latitude = fell.getLatitude();
//        this.longitude = fell.getLongitude();
//        this.regionName = fell.getRegionName();
//        this.convertedLatitude = fell.getConvertedLatitude();
//        this.convertedLongitude = fell.getConvertedLongitude();
//    }
}
