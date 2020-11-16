package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.dtos.*;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.services.EndpointGenerator;
import com.iainhemstock.lakedistrictapi.services.LatLongToDmsCoordConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FellDtoMapper {

    public static final double METERS_TO_FEET_CONVERSION = 3.2808;

    private final EndpointGenerator endpointGenerator;
    private final LatLongToDmsCoordConverter coordConverter;

    @Autowired
    public FellDtoMapper(final EndpointGenerator endpointGenerator, final LatLongToDmsCoordConverter coordConverter) {
        this.endpointGenerator = endpointGenerator;
        this.coordConverter = coordConverter;
    }

    public FellDto createDto(FellEntity fellEntity) {
        FellDto fellDto = new FellDto();
        fellDto.setName(fellEntity.getName());
        fellDto.setHeight(makeHeightDto(fellEntity));
        fellDto.setProminence(makeProminenceDto(fellEntity));
        fellDto.setParentPeakUrl(constructParentPeakUrl(fellEntity));
        fellDto.setClassifications(makeClassificationUrls(fellEntity));
        fellDto.setLocation(makeLocationDto(fellEntity));
        fellDto.setUrl(endpointGenerator.generateForResourceWithId("fells", fellEntity.getId()));

        return fellDto;
    }

    private String constructParentPeakUrl(final FellEntity fellEntity) {
        String parentPeakUrl = endpointGenerator.generateForResourceWithId("fells", fellEntity.getParentPeak().getFellId());
        if (fellEntity.getParentPeak().isNull())
            parentPeakUrl = "";
        return parentPeakUrl;
    }

    private LocationDto makeLocationDto(final FellEntity fellEntity) {
        return new LocationDto(
            makeCoordsDto(fellEntity),
            endpointGenerator.generateForResourceWithId("regions", fellEntity.getRegion().getId()),
            fellEntity.getOsMapRef(),
            makeOsMapUrls(fellEntity));
    }

    private CoordsDto makeCoordsDto(final FellEntity fellEntity) {
        DmsCoordsDto dmsCoords = makeDmsCoordsDto(fellEntity);
        DecimalCoordsDto decimalCoords = makeDecimalCoordsDto(fellEntity);

        return new CoordsDto(decimalCoords, dmsCoords);
    }

    private DecimalCoordsDto makeDecimalCoordsDto(final FellEntity fellEntity) {
        DecimalCoordsDto decimalCoords = new DecimalCoordsDto(
            String.valueOf(fellEntity.getLatitude()),
            String.valueOf(fellEntity.getLongitude()));

        return decimalCoords;
    }

    private DmsCoordsDto makeDmsCoordsDto(final FellEntity fellEntity) {
        coordConverter.convert(fellEntity.getLatitude(), LatLongToDmsCoordConverter.CoordType.LATITUDE);
        DmsDto convertedLatitude = new DmsDto(
            String.valueOf(coordConverter.getDegrees()),
            String.valueOf(coordConverter.getMinutes()),
            String.valueOf(coordConverter.getSeconds()),
            coordConverter.getHemisphere());

        coordConverter.convert(fellEntity.getLongitude(), LatLongToDmsCoordConverter.CoordType.LONGITUDE);
        DmsDto convertedLongitude = new DmsDto(
            String.valueOf(coordConverter.getDegrees()),
            String.valueOf(coordConverter.getMinutes()),
            String.valueOf(coordConverter.getSeconds()),
            coordConverter.getHemisphere());

        return new DmsCoordsDto(convertedLatitude, convertedLongitude);
    }

    private HeightDto makeHeightDto(FellEntity fellEntity) {
        return new HeightDto(
            String.valueOf(fellEntity.getHeightMeters()),
            String.valueOf((int) Math.round(fellEntity.getHeightMeters() * METERS_TO_FEET_CONVERSION)));
    }

    private ProminenceDto makeProminenceDto(FellEntity fellEntity) {
        return new ProminenceDto(
            String.valueOf(fellEntity.getProminenceMeters()),
            String.valueOf((int) Math.round(fellEntity.getProminenceMeters() * METERS_TO_FEET_CONVERSION)));
    }

    private Set<String> makeClassificationUrls(FellEntity fellEntity) {
        return fellEntity.getClassifications().stream()
            .map(classification -> endpointGenerator.generateForResourceWithId("classifications", classification.getId()))
            .collect(Collectors.toSet());
    }

    private Set<String> makeOsMapUrls(FellEntity fellEntity) {
        return fellEntity.getOsMaps().stream()
            .map(osMap -> endpointGenerator.generateForResourceWithId("maps", osMap.getId()))
            .collect(Collectors.toSet());
    }
}
