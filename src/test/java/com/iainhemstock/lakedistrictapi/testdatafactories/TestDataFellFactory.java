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
        Fell fell = new Fell();
        fell.setOsMapRef(new OsMapRef(entity.getOsMapRef()));
        fell.setName(new FellName(entity.getName()));
        fell.setHeightMeters(new Meters(entity.getHeightMeters()));
        fell.setHeightFeet(new Feet(3117));
        fell.setProminenceMeters(new Meters(entity.getProminenceMeters()));
        fell.setProminenceFeet(new Feet(2336));
        fell.setLatitude(new Latitude(entity.getLatitude()));
        fell.setLongitude(new Longitude(entity.getLongitude()));
        fell.setRegionName(new RegionName(entity.getRegionEntity().getName()));
        fell.setClassificationNames(entity.getClassifications().stream()
            .map(classificationEntity -> new ClassificationName(classificationEntity.getName()))
            .collect(Collectors.toSet()));
        fell.setOsMapNames(entity.getOsMaps().stream()
            .map(osMap -> new OsMapName(osMap.getName()))
            .collect(Collectors.toSet()));

        LatLongToDmsConversionService latLongToDmsConversionService = new LatLongToDmsConversionServiceImpl();
        latLongToDmsConversionService.convert(new Latitude(entity.getLatitude()));
        fell.setConvertedLatitude(new DMS(
            latLongToDmsConversionService.getDegrees(),
            latLongToDmsConversionService.getMinutes(),
            latLongToDmsConversionService.getSeconds(),
            latLongToDmsConversionService.getHemisphere()));

        latLongToDmsConversionService.convert(new Longitude(entity.getLongitude()));
        fell.setConvertedLongitude(new DMS(
            latLongToDmsConversionService.getDegrees(),
            latLongToDmsConversionService.getMinutes(),
            latLongToDmsConversionService.getSeconds(),
            latLongToDmsConversionService.getHemisphere()));

        return fell;
    }

}
