package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.entities.Classification;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.OsMap;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.EndpointGeneratorService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.MeterToFeetConversionService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class DetailedFell {
    private final FellName fellName;
    private final Link selfLink;
    private final Meters heightMeters;
    private final Feet heightFeet;
    private final Meters prominenceMeters;
    private final Feet prominenceFeet;
    private final Latitude latitude;
    private final Longitude longitude;
    private final DMS convertedLatitude;
    private final DMS convertedLongitude;
    private final RegionName regionName;
    private final OsMapRef osMapRef;
    private final ParentFell parentFell;
    private final Link parentLink;
    private final OsMapNames osMapNames;
    private final ClassificationNames classificationNames;

    public DetailedFell(final FellEntity fell,
                        final MeterToFeetConversionService m2fConverter,
                        final LatLongToDmsConversionService latLong2DMSConverter,
                        final EndpointGeneratorService endpointGeneratorService) {
        this.fellName = new FellName(fell.getName());
        this.selfLink = endpointGeneratorService.generateForResourceWithId("fells", fell.getOsMapRef());
        this.heightMeters = new Meters(fell.getHeightMeters());
        this.heightFeet = m2fConverter.convertRoundedToNearestInteger(new Meters(fell.getHeightMeters()));
        this.prominenceMeters = new Meters(fell.getProminenceMeters());
        this.prominenceFeet = m2fConverter.convertRoundedToNearestInteger(new Meters(fell.getProminenceMeters()));
        this.latitude = new Latitude(fell.getLatitude());
        this.longitude = new Longitude(fell.getLongitude());
        this.regionName = new RegionName(fell.getRegion().getName());
        this.osMapRef = new OsMapRef(fell.getOsMapRef());
        this.parentFell = fell.getParentPeak();
        this.parentLink = endpointGeneratorService.generateForResourceWithId("fells", fell.getParentPeak().getOsMapRef());

        latLong2DMSConverter.convert(latitude);
        this.convertedLatitude = new DMS(
            latLong2DMSConverter.getDegrees(),
            latLong2DMSConverter.getMinutes(),
            latLong2DMSConverter.getSeconds(),
            latLong2DMSConverter.getHemisphere());

        latLong2DMSConverter.convert(longitude);
        this.convertedLongitude = new DMS(
            latLong2DMSConverter.getDegrees(),
            latLong2DMSConverter.getMinutes(),
            latLong2DMSConverter.getSeconds(),
            latLong2DMSConverter.getHemisphere());

        this.osMapNames = new OsMapNames();
        fell.getOsMaps().stream()
            .map(OsMap::getName)
            .forEach(name -> this.osMapNames.add(new OsMapName(name)));

        this.classificationNames = new ClassificationNames();
        fell.getClassifications().stream()
            .map(Classification::getName)
            .forEach(name -> this.classificationNames.add(new ClassificationName(name)));
    }
}
