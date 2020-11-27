package com.iainhemstock.lakedistrictapi.dtos;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.services.converters.LatLongToDmsConverter;
import com.iainhemstock.lakedistrictapi.services.converters.MeterToFootConverter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractTestFellDto extends FellDto {

    private LatLongToDmsConverter coordConverter;
    private MeterToFootConverter meterToFootConverter;

    public AbstractTestFellDto(final Fell entity) {
        coordConverter = new LatLongToDmsConverter();
        coordConverter.convert(entity.getLatitude(), LatLongToDmsConverter.CoordType.LATITUDE);
        Map<String, String> convertedLatitude = Map.of(
            "degrees", String.valueOf(coordConverter.getDegrees()),
            "minutes", String.valueOf(coordConverter.getMinutes()),
            "seconds", String.valueOf(coordConverter.getSeconds()),
            "hemisphere", String.valueOf(coordConverter.getHemisphere()));

        coordConverter.convert(entity.getLongitude(), LatLongToDmsConverter.CoordType.LONGITUDE);
        Map<String, String> convertedLongitude = Map.of(
            "degrees", String.valueOf(coordConverter.getDegrees()),
            "minutes", String.valueOf(coordConverter.getMinutes()),
            "seconds", String.valueOf(coordConverter.getSeconds()),
            "hemisphere", String.valueOf(coordConverter.getHemisphere()));

        if (entity.getParentPeak().isNull())
            setParentPeakUrl("");
        else
            setParentPeakUrl(TestApiProperties.API_BASE_URL + "/fells/" + entity.getParentPeak().getFellId());

        setName(entity.getName());
        setUrl(TestApiProperties.API_BASE_URL + "/fells/" + entity.getId());

        meterToFootConverter = new MeterToFootConverter();
        setHeight(Map.of(
            "meters", String.valueOf(entity.getHeightMeters()),
            "feet", String.valueOf(meterToFootConverter.convertRoundedToNearestInteger(entity.getHeightMeters()))));

        setProminence(Map.of(
            "meters", String.valueOf(entity.getProminenceMeters()),
            "feet", String.valueOf(meterToFootConverter.convertRoundedToNearestInteger(entity.getProminenceMeters()))));

        setLocation(Map.of(
            "latitude", String.valueOf(entity.getLatitude()),
            "longitude", String.valueOf(entity.getLongitude()),
            "dms", List.of(
                convertedLatitude,
                convertedLongitude
            ),
            "region", entity.getRegion().getName(),
            "os_map_ref", entity.getOsMapRef(),
            "os_maps", entity.getOsMaps().stream()
                    .map(osMap -> osMap.getName())
                    .collect(Collectors.toSet())
        ));

        setClassifications(entity.getClassifications().stream()
            .map(classification -> TestApiProperties.API_BASE_URL + "/classifications/" + classification.getId())
            .collect(Collectors.toSet()));
    }
}
