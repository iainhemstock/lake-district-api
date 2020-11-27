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
    private static final String KEY_DMS = "dms";
    private static final String KEY_FEET = "feet";
    private static final String KEY_METERS = "meters";
    private static final String KEY_REGION = "region";
    private static final String KEY_DEGREES = "degrees";
    private static final String KEY_MINUTES = "minutes";
    private static final String KEY_SECONDS = "seconds";
    private static final String KEY_OS_MAPS = "os_maps";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_OS_MAP_REF = "os_map_ref";
    private static final String KEY_HEMISPHERE = "hemisphere";

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
    protected void initialize() {
        getDto().setHeight(new HashMap<>());
        getDto().setProminence(new HashMap<>());
        getDto().setLocation(new HashMap<>());
        getDto().getLocation().put(KEY_OS_MAPS, new HashSet<>());
        getDto().getLocation().put(KEY_DMS, new ArrayList<>());
    }

    @Override
    protected void mapFellName() {
        getDto().setName(getFell().getName());
    }

    @Override
    protected void mapHeight() {
        getDto().getHeight().put(KEY_FEET, String.valueOf(meterToFootConverter.convertRoundedToNearestInteger(getFell().getHeightMeters())));
        getDto().getHeight().put(KEY_METERS, String.valueOf(getFell().getHeightMeters()));
    }

    @Override
    protected void mapProminence() {
        getDto().getProminence().put(KEY_FEET, String.valueOf(meterToFootConverter.convertRoundedToNearestInteger(getFell().getProminenceMeters())));
        getDto().getProminence().put(KEY_METERS, String.valueOf(getFell().getProminenceMeters()));
    }

    @Override
    protected void mapParentPeak() {
        getDto().setParentPeakUrl(constructParentPeakUrl());
    }

    private String constructParentPeakUrl() {
        String parentPeakUrl = endpointGenerator.generateForResourceWithId("fells", getFell().getParentPeak().getFellId());
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
        getDto().getLocation().put(KEY_LATITUDE, String.valueOf(getFell().getLatitude()));
    }

    @Override
    protected void mapLongitude() {
        getDto().getLocation().put(KEY_LONGITUDE, String.valueOf(getFell().getLongitude()));
    }

    @Override
    protected void mapDms() {
        ((List<Map<String, String>>)getDto().getLocation().get(KEY_DMS))
            .addAll(List.of(getConvertedLatitude(), getConvertedLongitude()));
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
            KEY_DEGREES, String.valueOf(latLongToDmsConverter.getDegrees()),
            KEY_MINUTES, String.valueOf(latLongToDmsConverter.getMinutes()),
            KEY_SECONDS, String.valueOf(latLongToDmsConverter.getSeconds()),
            KEY_HEMISPHERE, String.valueOf(latLongToDmsConverter.getHemisphere()));
    }

    @Override
    protected void mapRegion() {
        getDto().getLocation().put(KEY_REGION, getFell().getRegion().getName());
    }

    @Override
    protected void mapOsMapRef() {
        getDto().getLocation().put(KEY_OS_MAP_REF, getFell().getOsMapRef());
    }

    @Override
    protected void mapOsMaps() {
        ((Set<String>)getDto().getLocation().get(KEY_OS_MAPS))
            .addAll(getFell().getOsMaps().stream()
                .map(OsMap::getName)
                .collect(Collectors.toSet()));
    }

    @Override
    protected void mapFellUrl() {
        getDto().setUrl(endpointGenerator.generateForResourceWithId("fells", getFell().getId()));
    }

}
