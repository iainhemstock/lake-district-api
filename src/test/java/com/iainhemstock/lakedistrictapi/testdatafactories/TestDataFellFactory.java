package com.iainhemstock.lakedistrictapi.testdatafactories;

import com.iainhemstock.lakedistrictapi.application_interfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.application_logic.LatLongToDmsConversionServiceImpl;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class TestDataFellFactory {

    public static Fell helvellynFell() {
        FellEntity entity = TestDataFellEntityFactory.helvellynFellEntity();

        LatLongToDmsConversionService latLongToDmsConversionService = new LatLongToDmsConversionServiceImpl();
        latLongToDmsConversionService.convert(new Latitude(entity.getLatitude()));
        DMS convertedLatitude = new DMS(
            latLongToDmsConversionService.getDegrees(),
            latLongToDmsConversionService.getMinutes(),
            latLongToDmsConversionService.getSeconds(),
            latLongToDmsConversionService.getHemisphere());

        latLongToDmsConversionService.convert(new Longitude(entity.getLongitude()));
        DMS convertedLongitude = new DMS(
            latLongToDmsConversionService.getDegrees(),
            latLongToDmsConversionService.getMinutes(),
            latLongToDmsConversionService.getSeconds(),
            latLongToDmsConversionService.getHemisphere());

        return new Fell(
            new OsMapRef(entity.getOsMapRef()),
            new OsMapRef(entity.getParentOsMapRef()),
            new FellName(entity.getName()),
            new Meters(entity.getHeightMeters()),
            new Feet(3117),
            new Meters(entity.getProminenceMeters()),
            new Feet(2336),
            entity.getClassifications().stream()
                .map(classificationEntity -> new ClassificationName(classificationEntity.getName()))
                .collect(Collectors.toSet()),
            entity.getOsMaps().stream()
                .map(osMap -> new OsMapName(osMap.getName()))
                .collect(Collectors.toSet()),
            new Latitude(entity.getLatitude()),
            new Longitude(entity.getLongitude()),
            new RegionName(entity.getRegionEntity().getName()),
            convertedLatitude,
            convertedLongitude);
    }

}
