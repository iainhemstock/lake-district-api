package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.application_interfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.application_logic.LatLongToDmsConversionServiceImpl;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;

import java.util.Set;

public class HelvellynFell extends Fell {
    public HelvellynFell() {
        FellEntity helvellynFellEntity = new HelvellynFellEntity();
        setOsMapRef(helvellynFellEntity.getOsMapRef());
        setName(helvellynFellEntity.getName());
        setHeightMeters(helvellynFellEntity.getHeightMeters());
        setHeightFeet(new Feet(3117));
        setProminenceMeters(helvellynFellEntity.getProminenceMeters());
        setProminenceFeet(new Feet(2336));
        setClassificationNames(new ClassificationNames(Set.of(new ClassificationName("Marilyn"))));
        setOsMapNames(new OsMapNames(Set.of(new OsMapName("OS Landranger 90"), new OsMapName("OS Explorer OL5"))));
        setLatitude(helvellynFellEntity.getLatitude());
        setLongitude(helvellynFellEntity.getLongitude());
        setRegionName(helvellynFellEntity.getRegionEntity().getRegionName());

        LatLongToDmsConversionService latLongToDmsConversionService = new LatLongToDmsConversionServiceImpl();
        latLongToDmsConversionService.convert(helvellynFellEntity.getLatitude());
        setConvertedLatitude(new DMS(
            latLongToDmsConversionService.getDegrees(),
            latLongToDmsConversionService.getMinutes(),
            latLongToDmsConversionService.getSeconds(),
            latLongToDmsConversionService.getHemisphere()));

        latLongToDmsConversionService.convert(helvellynFellEntity.getLongitude());
        setConvertedLongitude(new DMS(
            latLongToDmsConversionService.getDegrees(),
            latLongToDmsConversionService.getMinutes(),
            latLongToDmsConversionService.getSeconds(),
            latLongToDmsConversionService.getHemisphere()));
    }
}
