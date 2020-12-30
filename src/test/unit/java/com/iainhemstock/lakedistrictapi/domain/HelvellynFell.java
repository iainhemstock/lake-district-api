package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.application_interfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.application_logic.LatLongToDmsConversionServiceImpl;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;

import java.util.Set;

public class HelvellynFell extends Fell {
    public HelvellynFell() {
        FellEntity helvellynFellEntity = new HelvellynFellEntity();
        setOsMapRef(new OsMapRef(helvellynFellEntity.getOsMapRef()));
        setName(new FellName(helvellynFellEntity.getName()));
        setHeightMeters(new Meters(helvellynFellEntity.getHeightMeters()));
        setHeightFeet(new Feet(3117));
        setProminenceMeters(new Meters(helvellynFellEntity.getProminenceMeters()));
        setProminenceFeet(new Feet(2336));
        setClassificationNames(new ClassificationNames(Set.of(new ClassificationName("Marilyn"))));
        setOsMapNames(new OsMapNames(Set.of(new OsMapName("OS Landranger 90"), new OsMapName("OS Explorer OL5"))));
        setLatitude(new Latitude(helvellynFellEntity.getLatitude()));
        setLongitude(new Longitude(helvellynFellEntity.getLongitude()));
        setRegionName(new RegionName(helvellynFellEntity.getRegionEntity().getName()));

        LatLongToDmsConversionService latLongToDmsConversionService = new LatLongToDmsConversionServiceImpl();
        latLongToDmsConversionService.convert(new Latitude(helvellynFellEntity.getLatitude()));
        setConvertedLatitude(new DMS(
            latLongToDmsConversionService.getDegrees(),
            latLongToDmsConversionService.getMinutes(),
            latLongToDmsConversionService.getSeconds(),
            latLongToDmsConversionService.getHemisphere()));

        latLongToDmsConversionService.convert(new Longitude(helvellynFellEntity.getLongitude()));
        setConvertedLongitude(new DMS(
            latLongToDmsConversionService.getDegrees(),
            latLongToDmsConversionService.getMinutes(),
            latLongToDmsConversionService.getSeconds(),
            latLongToDmsConversionService.getHemisphere()));
    }
}
