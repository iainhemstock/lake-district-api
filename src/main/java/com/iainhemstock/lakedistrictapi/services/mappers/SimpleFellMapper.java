package com.iainhemstock.lakedistrictapi.services.mappers;

import com.iainhemstock.lakedistrictapi.entities.Classification;
import com.iainhemstock.lakedistrictapi.entities.OsMap;
import com.iainhemstock.lakedistrictapi.services.EndpointGenerator;
import com.iainhemstock.lakedistrictapi.services.converters.LatLongToDmsConverter;
import com.iainhemstock.lakedistrictapi.services.converters.MeterToFootConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SimpleFellMapper extends FellMapper {

    private final EndpointGenerator endpointGenerator;
    private final LatLongToDmsConverter latLongToDmsConverter;
    private final MeterToFootConverter meterToFootConverter;

    @Autowired
    public SimpleFellMapper(final EndpointGenerator endpointGenerator,
                            final LatLongToDmsConverter latLongToDmsConverter,
                            final MeterToFootConverter meterToFootConverter) {
        this.endpointGenerator = endpointGenerator;
        this.latLongToDmsConverter = latLongToDmsConverter;
        this.meterToFootConverter = meterToFootConverter;
    }

    @Override
    protected void mapFellName() {
        getDto().setName(getFell().getName());
    }

    @Override
    protected void mapHeight() {
        getDto().setHeightInFeet(String.valueOf(meterToFootConverter.convertRoundedToNearestInteger(getFell().getHeightMeters())));
        getDto().setHeightInMeters(String.valueOf(getFell().getHeightMeters()));
    }

    @Override
    protected void mapProminence() {
        getDto().setProminenceInFeet(String.valueOf(meterToFootConverter.convertRoundedToNearestInteger(getFell().getProminenceMeters())));
        getDto().setProminenceInMeters(String.valueOf(getFell().getProminenceMeters()));
    }

    @Override
    protected void mapParentPeak() {
        getDto().setParentPeak(constructParentPeakUrl());
    }

    private String constructParentPeakUrl() {
        String parentPeakUrl = endpointGenerator.generateForResourceWithId("fells", getFell().getParentPeak().getOsMapRef());
        if (getFell().getParentPeak().isNull())
            parentPeakUrl = "";
        return parentPeakUrl;
    }

    @Override
    protected void mapClassifications() {
        getDto().setClassifications(getFell().getClassifications().stream()
            .map(Classification::getName)
            .collect(Collectors.toSet()));
    }

    @Override
    protected void mapLatitude() {
        getDto().setLatitude(String.valueOf(getFell().getLatitude()));
    }

    @Override
    protected void mapLongitude() {
        getDto().setLongitude(String.valueOf(getFell().getLongitude()));
    }

    @Override
    protected void mapDms() {
        getDto().setDms(List.of(getConvertedLatitude(), getConvertedLongitude()));
    }

    private Map<String, String> getConvertedLatitude() {
        latLongToDmsConverter.convert(getFell().getLatitude(), LatLongToDmsConverter.CoordType.LATITUDE);
        return assembleConvertedDmsMap();
    }

    private Map<String, String> getConvertedLongitude() {
        latLongToDmsConverter.convert(getFell().getLongitude(), LatLongToDmsConverter.CoordType.LONGITUDE);
        return assembleConvertedDmsMap();
    }

    private Map<String, String> assembleConvertedDmsMap() {
        return Map.of(
            "degrees", String.valueOf(latLongToDmsConverter.getDegrees()),
            "minutes", String.valueOf(latLongToDmsConverter.getMinutes()),
            "seconds", String.valueOf(latLongToDmsConverter.getSeconds()),
            "hemisphere", String.valueOf(latLongToDmsConverter.getHemisphere())
        );
    }

    @Override
    protected void mapRegion() {
        getDto().setRegion(getFell().getRegion().getName());
    }

    @Override
    protected void mapOsMapRef() {
        getDto().setOsMapRef(getFell().getOsMapRef());
    }

    @Override
    protected void mapOsMaps() {
        getDto().setOsMaps(getFell().getOsMaps().stream()
                                .map(OsMap::getName)
                                .collect(Collectors.toSet()));
    }

    @Override
    protected void mapFellUrl() {
        getDto().setUrl(endpointGenerator.generateForResourceWithId("fells", getFell().getOsMapRef()));
    }

}
