package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.entities.Classification;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.OsMap;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LinkService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.MeterToFeetConversionService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DetailedFell {
    private FellName fellName;
    private Meters heightMeters;
    private Feet heightFeet;
    private Meters prominenceMeters;
    private Feet prominenceFeet;
    private Latitude latitude;
    private Longitude longitude;
    private DMS convertedLatitude;
    private DMS convertedLongitude;
    private RegionName regionName;
    private OsMapRef osMapRef;
    private ParentFell parentFell;
    private OsMapNames osMapNames;
    private ClassificationNames classificationNames;

    public DetailedFell() {
    }

    public DetailedFell(final FellEntity fell,
                        final MeterToFeetConversionService m2fConverter,
                        final LatLongToDmsConversionService latLong2DMSConverter,
                        final LinkService linkService) {
        this.fellName = new FellName(fell.getName().toString());
        this.heightMeters = new Meters(fell.getHeightMeters().toInt());
        this.heightFeet = m2fConverter.convertRoundedToNearestInteger(new Meters(fell.getHeightMeters().toInt()));
        this.prominenceMeters = new Meters(fell.getProminenceMeters().toInt());
        this.prominenceFeet = m2fConverter.convertRoundedToNearestInteger(new Meters(fell.getProminenceMeters().toInt()));
        this.latitude = new Latitude(fell.getLatitude().toDouble());
        this.longitude = new Longitude(fell.getLongitude().toDouble());
        this.regionName = new RegionName(fell.getRegion().getName());
        this.osMapRef = fell.getOsMapRef();
        this.parentFell = fell.getParentPeak();

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
        fell.getOsMaps().toSet().stream()
            .map(OsMap::getName)
            .forEach(name -> this.osMapNames.add(new OsMapName(name)));

        this.classificationNames = new ClassificationNames();
        fell.getClassifications().toSet().stream()
            .map(Classification::getName)
            .forEach(name -> this.classificationNames.add(new ClassificationName(name)));
    }
}
