package com.iainhemstock.lakedistrictapi.dtos;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.services.LatLongToDmsCoordConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public abstract class AbstractTestFellDTO extends FellDTO {

    private static final double METERS_TO_FEET_CONVERSION = 3.2808;
    @Autowired private ApiProperties apiProperties;

    private LatLongToDmsCoordConverter coordConverter;

    public AbstractTestFellDTO(final FellEntity entity) {
        coordConverter = new LatLongToDmsCoordConverter();
        coordConverter.convert(entity.getLatitude(), LatLongToDmsCoordConverter.CoordType.LATITUDE);
        DmsDTO convertedLatitude = new DmsDTO(
            String.valueOf(coordConverter.getDegrees()),
            String.valueOf(coordConverter.getMinutes()),
            String.valueOf(coordConverter.getSeconds()),
            coordConverter.getHemisphere());

        coordConverter.convert(entity.getLongitude(), LatLongToDmsCoordConverter.CoordType.LONGITUDE);
        DmsDTO convertedLongitude = new DmsDTO(
            String.valueOf(coordConverter.getDegrees()),
            String.valueOf(coordConverter.getMinutes()),
            String.valueOf(coordConverter.getSeconds()),
            coordConverter.getHemisphere());

        if (entity.getParentPeak().isNull())
            setParentPeakUrl("");
        else
            setParentPeakUrl(apiProperties.getBaseUrl() + "/fells/" + entity.getParentPeak().getFellId());

        setName(entity.getName());
        setUrl(apiProperties.getBaseUrl() + "/fells/" + entity.getId());

        setHeight(new HeightDTO(
            String.valueOf(entity.getHeightMeters()),
            String.valueOf((int) Math.round(entity.getHeightMeters() * METERS_TO_FEET_CONVERSION))));

        setProminence(new ProminenceDTO(
            String.valueOf(entity.getProminenceMeters()),
            String.valueOf((int) Math.round(entity.getProminenceMeters() * METERS_TO_FEET_CONVERSION))));

        setLocation(new LocationDTO(
            new CoordsDTO(
                new DecimalCoordsDTO(
                    String.valueOf(entity.getLatitude()),
                    String.valueOf(entity.getLongitude())),
                new DmsCoordsDTO(
                    convertedLatitude,
                    convertedLongitude
                )
            ),
            apiProperties.getBaseUrl() + "/regions/" + entity.getRegion().getId(),
            entity.getOsMapRef(),
            entity.getOsMaps().stream()
                .map(osMap -> apiProperties.getBaseUrl() + "/maps/" + osMap.getId())
                .collect(Collectors.toSet())));

        setClassifications(entity.getClassifications().stream()
            .map(classification -> apiProperties.getBaseUrl() + "/classifications/" + classification.getId())
            .collect(Collectors.toSet()));
    }
}
