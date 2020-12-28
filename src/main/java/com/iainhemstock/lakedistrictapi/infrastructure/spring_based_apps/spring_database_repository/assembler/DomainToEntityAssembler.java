package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.assembler;

import com.iainhemstock.lakedistrictapi.application_interfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.application_interfaces.MeterToFeetConversionService;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;

public class DomainToEntityAssembler {
    private final LatLongToDmsConversionService latLongToDmsConversionService;
    private final MeterToFeetConversionService meterToFeetConversionService;

    public DomainToEntityAssembler(final LatLongToDmsConversionService latLongToDmsConversionService, final MeterToFeetConversionService meterToFeetConversionService) {
        this.latLongToDmsConversionService = latLongToDmsConversionService;
        this.meterToFeetConversionService = meterToFeetConversionService;
    }

    public Fell toDomain(final FellEntity entity) {
        Fell fell = new Fell();
        fell.setOsMapRef(entity.getOsMapRef());
        fell.setParentOsMapRef(entity.getParentOsMapRef());
        fell.setName(entity.getName());
        fell.setHeightMeters(entity.getHeightMeters());
        fell.setProminenceMeters(entity.getProminenceMeters());
        fell.setLatitude(entity.getLatitude());
        fell.setLongitude(entity.getLongitude());
        fell.setRegionName(entity.getRegionEntity().getRegionName());

        this.latLongToDmsConversionService.convert(entity.getLatitude());
        fell.setConvertedLatitude(new DMS(
            this.latLongToDmsConversionService.getDegrees(),
            this.latLongToDmsConversionService.getMinutes(),
            this.latLongToDmsConversionService.getSeconds(),
            this.latLongToDmsConversionService.getHemisphere()));

        this.latLongToDmsConversionService.convert(entity.getLongitude());
        fell.setConvertedLongitude(new DMS(
            this.latLongToDmsConversionService.getDegrees(),
            this.latLongToDmsConversionService.getMinutes(),
            this.latLongToDmsConversionService.getSeconds(),
            this.latLongToDmsConversionService.getHemisphere()));

        fell.setHeightFeet(this.meterToFeetConversionService.convertRoundedToNearestInteger(entity.getHeightMeters()));
        fell.setProminenceFeet(this.meterToFeetConversionService.convertRoundedToNearestInteger(entity.getProminenceMeters()));

        ClassificationNames classificationNames = new ClassificationNames();
        entity.getClassifications().forEach(classificationEntity -> classificationNames.add(classificationEntity.getClassificationName()));
        fell.setClassificationNames(classificationNames);

        OsMapNames osMapNames = new OsMapNames();
        entity.getOsMaps().forEach(osMapEntity -> osMapNames.add(osMapEntity.getOsMapName()));
        fell.setOsMapNames(osMapNames);

        return fell;
    }
}
