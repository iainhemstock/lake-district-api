package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.dtos.*;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FellDTOMapper {

    private static final double METERS_TO_FEET_CONVERSION = 3.2808;

    private static String API_BASE_URL = "http://localhost:8080/api";

    public FellDTO createDTO(FellEntity fellEntity) {
        DmsService dmsService = new DmsService(
            new double[]{
                fellEntity.getLatitude(),
                fellEntity.getLongitude()});

        HeightDTO heightDTO = makeHeightDTO(fellEntity);
        ProminenceDTO prominenceDTO = makeProminenceDTO(fellEntity);
        LocationDTO locationDTO = makeLocationDTO(fellEntity, dmsService);

        return makeFellDTO(fellEntity, heightDTO, prominenceDTO, locationDTO);
    }

    private FellDTO makeFellDTO(final FellEntity fellEntity, final HeightDTO heightDTO, final ProminenceDTO prominenceDTO, final LocationDTO locationDTO)  {
        FellDTO fellDTO = new FellDTO();

        if (fellEntity.getParentPeak().isNull()) fellDTO.setParentPeakUrl("");
        else fellDTO.setParentPeakUrl(String.format("%s/fells/%s", API_BASE_URL, fellEntity.getParentPeak().getFellId()));

        fellDTO.setName(fellEntity.getName());
        fellDTO.setUrl(String.format("%s/fells/%s", API_BASE_URL, fellEntity.getId()));
        fellDTO.setClassifications(getClassificationUris(fellEntity));
        fellDTO.setHeight(heightDTO);
        fellDTO.setProminence(prominenceDTO);
        fellDTO.setLocation(locationDTO);

        return fellDTO;
    }

    private LocationDTO makeLocationDTO(final FellEntity fellEntity, final DmsService dmsService) {
        String latitude = String.valueOf(fellEntity.getLatitude());
        String longitude = String.valueOf(fellEntity.getLongitude());

        DecimalCoordsDTO decimalCoords = new DecimalCoordsDTO(latitude, longitude);
        DmsCoordsDTO dmsCoords = new DmsCoordsDTO(dmsService.getFirstDms(), dmsService.getSecondDms());
        CoordsDTO coordsDTO = new CoordsDTO(decimalCoords, dmsCoords);

        return new LocationDTO(
            coordsDTO,
            makeRegionUrlString(fellEntity),
            fellEntity.getOsMapRef(),
            getOsMapUris(fellEntity)
        );
    }

    private String makeRegionUrlString(final FellEntity fellEntity) {
        String regionUrl = null;
        try {
            regionUrl = fellEntity.getRegion().getUrl().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return regionUrl;
    }

    private ProminenceDTO makeProminenceDTO(final FellEntity fellEntity) {
        return new ProminenceDTO(
            fellEntity.getProminenceMeters(),
            (int) Math.round(fellEntity.getProminenceMeters() * METERS_TO_FEET_CONVERSION));
    }

    private HeightDTO makeHeightDTO(final FellEntity fellEntity) {
        return new HeightDTO(
            fellEntity.getHeightMeters(),
            (int) Math.round(fellEntity.getHeightMeters() * METERS_TO_FEET_CONVERSION));
    }

    private Set<String> getClassificationUris(FellEntity fellEntity) {
        return fellEntity.getClassifications().stream()
            .map(classification -> API_BASE_URL + "/classifications/" + classification.getId())
            .collect(Collectors.toSet());
    }

    private Set<String> getOsMapUris(FellEntity fellEntity) {
        return fellEntity.getOsMaps().stream()
            .map(osMap -> API_BASE_URL + "/maps/" + osMap.getId())
            .collect(Collectors.toSet());
    }
}
