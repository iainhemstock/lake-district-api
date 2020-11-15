package com.iainhemstock.lakedistrictapi.dtos;

import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.services.DmsService;

import java.util.stream.Collectors;

public abstract class AbstractTestFellDTO extends FellDTO {

    private static final double METERS_TO_FEET_CONVERSION = 3.2808;
    private static String API_BASE_URL = "http://localhost:8080/api";

    private DmsService dmsService;

    public AbstractTestFellDTO(final FellEntity entity) {
        dmsService = new DmsService((new double[]{entity.getLatitude(), entity.getLongitude()}));

        if (entity.getParentPeak().isNull())
            setParentPeakUrl("");
        else
            setParentPeakUrl(API_BASE_URL + "/fells/" + entity.getParentPeak().getFellId());

        setName(entity.getName());
        setUrl(API_BASE_URL + "/fells/" + entity.getId());

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
                    dmsService.getFirstDms(),
                    dmsService.getSecondDms()
                )
            ),
            API_BASE_URL + "/regions/" + entity.getRegion().getId(),
            entity.getOsMapRef(),
            entity.getOsMaps().stream()
                .map(osMap -> API_BASE_URL + "/maps/" + osMap.getId())
                .collect(Collectors.toSet())));

        setClassifications(entity.getClassifications().stream()
            .map(classification -> API_BASE_URL + "/classifications/" + classification.getId())
            .collect(Collectors.toSet()));
    }
}
