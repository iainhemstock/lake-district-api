package com.iainhemstock.lakedistrictapi.dtos;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.services.FellDtoMapper;
import com.iainhemstock.lakedistrictapi.services.LatLongToDmsCoordConverter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractTestFellDto extends FellDto {

    private LatLongToDmsCoordConverter coordConverter;

    public AbstractTestFellDto(final FellEntity entity) {
        coordConverter = new LatLongToDmsCoordConverter();
        coordConverter.convert(entity.getLatitude(), LatLongToDmsCoordConverter.CoordType.LATITUDE);
        DmsDto convertedLatitude = new DmsDto(
            String.valueOf(coordConverter.getDegrees()),
            String.valueOf(coordConverter.getMinutes()),
            String.valueOf(coordConverter.getSeconds()),
            coordConverter.getHemisphere());

        coordConverter.convert(entity.getLongitude(), LatLongToDmsCoordConverter.CoordType.LONGITUDE);
        DmsDto convertedLongitude = new DmsDto(
            String.valueOf(coordConverter.getDegrees()),
            String.valueOf(coordConverter.getMinutes()),
            String.valueOf(coordConverter.getSeconds()),
            coordConverter.getHemisphere());

        if (entity.getParentPeak().isNull())
            setParentPeakUrl("");
        else
            setParentPeakUrl(TestApiProperties.API_BASE_URL + "/fells/" + entity.getParentPeak().getFellId());

        setName(entity.getName());
        setUrl(TestApiProperties.API_BASE_URL + "/fells/" + entity.getId());

        setHeight(new HeightDto(
            String.valueOf(entity.getHeightMeters()),
            String.valueOf((int) Math.round(entity.getHeightMeters() * FellDtoMapper.METERS_TO_FEET_CONVERSION))));

        setProminence(new ProminenceDto(
            String.valueOf(entity.getProminenceMeters()),
            String.valueOf((int) Math.round(entity.getProminenceMeters() * FellDtoMapper.METERS_TO_FEET_CONVERSION))));

        setLocation(new LocationDto(
            new DecimalCoordsDto(
                    String.valueOf(entity.getLatitude()),
                    String.valueOf(entity.getLongitude())),
            List.of(convertedLatitude, convertedLongitude),
            TestApiProperties.API_BASE_URL + "/regions/" + entity.getRegion().getId(),
            entity.getOsMapRef(),
            entity.getOsMaps().stream()
                .map(osMap -> TestApiProperties.API_BASE_URL + "/maps/" + osMap.getId())
                .collect(Collectors.toSet())));

        setClassifications(entity.getClassifications().stream()
            .map(classification -> TestApiProperties.API_BASE_URL + "/classifications/" + classification.getId())
            .collect(Collectors.toSet()));
    }
}
