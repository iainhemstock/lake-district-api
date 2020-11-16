package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.dtos.*;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FellDTOMapper {

    // todo: move this to external properties
    private static final double METERS_TO_FEET_CONVERSION = 3.2808;

    private final EndpointGenerator endpointGenerator;
    private final LatLongToDmsCoordConverter coordConverter;

    @Autowired
    public FellDTOMapper(final EndpointGenerator endpointGenerator, final LatLongToDmsCoordConverter coordConverter) {
        this.endpointGenerator = endpointGenerator;
        this.coordConverter = coordConverter;
    }

    public FellDTO createDTO(FellEntity fellEntity) {
        HeightDTO heightDTO = makeHeightDTO(fellEntity);
        ProminenceDTO prominenceDTO = makeProminenceDTO(fellEntity);
        LocationDTO locationDTO = makeLocationDTO(fellEntity, coordConverter);

        return makeFellDTO(fellEntity, heightDTO, prominenceDTO, locationDTO);
    }

    private FellDTO makeFellDTO(final FellEntity fellEntity, final HeightDTO heightDTO, final ProminenceDTO prominenceDTO, final LocationDTO locationDTO)  {
        FellDTO fellDTO = new FellDTO();

        if (fellEntity.getParentPeak().isNull()) fellDTO.setParentPeakUrl("");
        else fellDTO.setParentPeakUrl(endpointGenerator.generateForResourceWithId("fells", fellEntity.getParentPeak().getFellId()));

        fellDTO.setName(fellEntity.getName());
        fellDTO.setUrl(endpointGenerator.generateForResourceWithId("fells", fellEntity.getId()));
        fellDTO.setClassifications(makeClassificationUrls(fellEntity));
        fellDTO.setHeight(heightDTO);
        fellDTO.setProminence(prominenceDTO);
        fellDTO.setLocation(locationDTO);

        return fellDTO;
    }

    private LocationDTO makeLocationDTO(final FellEntity fellEntity, final LatLongToDmsCoordConverter coordConverter) {
        coordConverter.convert(fellEntity.getLatitude(), LatLongToDmsCoordConverter.CoordType.LATITUDE);
        DmsDTO convertedLatitude = new DmsDTO(
            String.valueOf(coordConverter.getDegrees()),
            String.valueOf(coordConverter.getMinutes()),
            String.valueOf(coordConverter.getSeconds()),
            coordConverter.getHemisphere());

        coordConverter.convert(fellEntity.getLongitude(), LatLongToDmsCoordConverter.CoordType.LONGITUDE);
        DmsDTO convertedLongitude = new DmsDTO(
            String.valueOf(coordConverter.getDegrees()),
            String.valueOf(coordConverter.getMinutes()),
            String.valueOf(coordConverter.getSeconds()),
            coordConverter.getHemisphere());

        String latitude = String.valueOf(fellEntity.getLatitude());
        String longitude = String.valueOf(fellEntity.getLongitude());

        DecimalCoordsDTO decimalCoords = new DecimalCoordsDTO(latitude, longitude);
        DmsCoordsDTO dmsCoords = new DmsCoordsDTO(convertedLatitude, convertedLongitude);
        CoordsDTO coordsDTO = new CoordsDTO(decimalCoords, dmsCoords);

        return new LocationDTO(
            coordsDTO,
            endpointGenerator.generateForResourceWithId("regions", fellEntity.getRegion().getId()),
            fellEntity.getOsMapRef(),
            makeOsMapUrls(fellEntity));
    }

    private ProminenceDTO makeProminenceDTO(final FellEntity fellEntity) {
        return new ProminenceDTO(
            String.valueOf(fellEntity.getProminenceMeters()),
            String.valueOf((int) Math.round(fellEntity.getProminenceMeters() * METERS_TO_FEET_CONVERSION)));
    }

    private HeightDTO makeHeightDTO(final FellEntity fellEntity) {
        return new HeightDTO(
            String.valueOf(fellEntity.getHeightMeters()),
            String.valueOf((int) Math.round(fellEntity.getHeightMeters() * METERS_TO_FEET_CONVERSION)));
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
