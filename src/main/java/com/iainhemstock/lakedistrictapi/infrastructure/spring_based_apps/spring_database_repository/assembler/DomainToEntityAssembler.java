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
        Fell fell = new Fell();
        fell.setOsMapRef(new OsMapRef(entity.getOsMapRef()));
        if (entity.getParentOsMapRef() != null)
            fell.setParentOsMapRef(new OsMapRef(entity.getParentOsMapRef()));
        fell.setName(new FellName(entity.getName()));
        fell.setHeightMeters(new Meters(entity.getHeightMeters()));
        fell.setProminenceMeters(new Meters(entity.getProminenceMeters()));
        fell.setLatitude(new Latitude(entity.getLatitude()));
        fell.setLongitude(new Longitude(entity.getLongitude()));
        fell.setRegionName(new RegionName(entity.getRegionEntity().getName()));

        this.latLongToDmsConversionService.convert(new Latitude(entity.getLatitude()));
        fell.setConvertedLatitude(new DMS(
            this.latLongToDmsConversionService.getDegrees(),
            this.latLongToDmsConversionService.getMinutes(),
            this.latLongToDmsConversionService.getSeconds(),
            this.latLongToDmsConversionService.getHemisphere()));

        this.latLongToDmsConversionService.convert(new Longitude(entity.getLongitude()));
        fell.setConvertedLongitude(new DMS(
            this.latLongToDmsConversionService.getDegrees(),
            this.latLongToDmsConversionService.getMinutes(),
            this.latLongToDmsConversionService.getSeconds(),
            this.latLongToDmsConversionService.getHemisphere()));

        fell.setHeightFeet(this.meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(entity.getHeightMeters())));
        fell.setProminenceFeet(this.meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(entity.getProminenceMeters())));

        Set<ClassificationName> classificationNames = new LinkedHashSet<>();
        entity.getClassifications().forEach(classificationEntity -> classificationNames.add(new ClassificationName(classificationEntity.getName())));
        fell.setClassificationNames(classificationNames);

        Set<OsMapName> osMapNames = new LinkedHashSet<>();
        entity.getOsMaps().forEach(osMapEntity -> osMapNames.add(new OsMapName(osMapEntity.getName())));
        fell.setOsMapNames(osMapNames);

        return fell;
    }
}
