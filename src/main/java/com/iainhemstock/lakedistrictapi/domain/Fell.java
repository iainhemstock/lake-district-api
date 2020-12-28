package com.iainhemstock.lakedistrictapi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fell {
    private OsMapRef osMapRef;
    private FellName name;
    private Meters heightMeters;
    private Feet heightFeet;
    private Meters prominenceMeters;
    private Feet prominenceFeet;
    private ClassificationNames classificationNames;
    private OsMapNames osMapNames;
    private Latitude latitude;
    private Longitude longitude;
    private RegionName regionName;
    private DMS convertedLatitude;
    private DMS convertedLongitude;
}
