package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.assembler;

import com.iainhemstock.lakedistrictapi.application_interfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.application_interfaces.MeterToFeetConversionService;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;

import java.util.LinkedHashSet;
import java.util.Set;

public class DomainToEntityAssembler {
    private final LatLongToDmsConversionService latLongToDmsConversionService;
    private final MeterToFeetConversionService meterToFeetConversionService;

    public DomainToEntityAssembler(final LatLongToDmsConversionService latLongToDmsConversionService,
                                   final MeterToFeetConversionService meterToFeetConversionService) {
        this.latLongToDmsConversionService = latLongToDmsConversionService;
        this.meterToFeetConversionService = meterToFeetConversionService;
    }

    public Fell toDomain(final FellEntity entity) {
        this.latLongToDmsConversionService.convert(new Latitude(entity.getLatitude()));
        DMS convertedLatitude = new DMS(
            this.latLongToDmsConversionService.getDegrees(),
            this.latLongToDmsConversionService.getMinutes(),
            this.latLongToDmsConversionService.getSeconds(),
            this.latLongToDmsConversionService.getHemisphere());

        this.latLongToDmsConversionService.convert(new Longitude(entity.getLongitude()));
        DMS convertedLongitude = new DMS(
            this.latLongToDmsConversionService.getDegrees(),
            this.latLongToDmsConversionService.getMinutes(),
            this.latLongToDmsConversionService.getSeconds(),
            this.latLongToDmsConversionService.getHemisphere());

        Feet heightFeet = this.meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(entity.getHeightMeters()));
        Feet prominenceFeet = this.meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(entity.getProminenceMeters()));

        Set<ClassificationName> classificationNames = new LinkedHashSet<>();
        entity.getClassifications().forEach(classificationEntity ->
            classificationNames.add(new ClassificationName(classificationEntity.getName())));

        Set<OsMapName> osMapNames = new LinkedHashSet<>();
        entity.getOsMaps().forEach(osMapEntity ->
            osMapNames.add(new OsMapName(osMapEntity.getName())));

        return new Fell(
            new OsMapRef(entity.getOsMapRef()),
            (entity.getParentOsMapRef() != null)
                ? new OsMapRef(entity.getParentOsMapRef())
                : null,
            new FellName(entity.getName()),
            new Meters(entity.getHeightMeters()),
            heightFeet,
            new Meters(entity.getProminenceMeters()),
            prominenceFeet,
            classificationNames,
            osMapNames,
            new Latitude(entity.getLatitude()),
            new Longitude(entity.getLongitude()),
            new RegionName(entity.getRegionEntity().getName()),
            convertedLatitude,
            convertedLongitude);
    }
}
