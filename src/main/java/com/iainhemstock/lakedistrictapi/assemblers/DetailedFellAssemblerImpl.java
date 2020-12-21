package com.iainhemstock.lakedistrictapi.assemblers;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.DetailedFellAssembler;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.MeterToFeetConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailedFellAssemblerImpl implements DetailedFellAssembler {

    private final MeterToFeetConversionService meterToFeetConversionService;
    private final LatLongToDmsConversionService latLongToDMSConversionService;

    @Autowired
    public DetailedFellAssemblerImpl(final MeterToFeetConversionService meterToFeetConversionService,
                                     final LatLongToDmsConversionService latLongToDMSConversionService) {
        this.meterToFeetConversionService = meterToFeetConversionService;
        this.latLongToDMSConversionService = latLongToDMSConversionService;
    }

    @Override
    public DetailedFell toDetailedFell(final FellEntity fellEntity) {
        DetailedFell detailedFell = new DetailedFell();
        detailedFell.setFellName(new FellName(fellEntity.getName().toString()));
        detailedFell.setOsMapRef(new OsMapRef(fellEntity.getOsMapRef()));
        detailedFell.setHeightMeters(new Meters(fellEntity.getHeightMeters().toInt()));
        detailedFell.setHeightFeet(meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(fellEntity.getHeightMeters().toInt())));
        detailedFell.setProminenceMeters(new Meters(fellEntity.getProminenceMeters().toInt()));
        detailedFell.setProminenceFeet(meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(fellEntity.getProminenceMeters().toInt())));
        detailedFell.setLatitude(new Latitude(fellEntity.getLatitude().toDouble()));
        detailedFell.setLongitude(new Longitude(fellEntity.getLongitude()));
        detailedFell.setRegionName(new RegionName(fellEntity.getRegion().getName()));
        detailedFell.setParentFell(new ParentFell(fellEntity.getParentPeak().getOsMapRef()));

        latLongToDMSConversionService.convert(new Latitude(fellEntity.getLatitude().toDouble()));
        detailedFell.setConvertedLatitude(new DMS(
            latLongToDMSConversionService.getDegrees(),
            latLongToDMSConversionService.getMinutes(),
            latLongToDMSConversionService.getSeconds(),
            latLongToDMSConversionService.getHemisphere()));

        latLongToDMSConversionService.convert(new Longitude(fellEntity.getLongitude()));
        detailedFell.setConvertedLongitude(new DMS(
            latLongToDMSConversionService.getDegrees(),
            latLongToDMSConversionService.getMinutes(),
            latLongToDMSConversionService.getSeconds(),
            latLongToDMSConversionService.getHemisphere()));

        ClassificationNames classificationNames = new ClassificationNames();
        fellEntity.getClassifications().forEach(classification -> classificationNames.add(new ClassificationName(classification.getName())));
        detailedFell.setClassificationNames(classificationNames);

        OsMapNames osMapNames = new OsMapNames();
        fellEntity.getOsMaps().forEach(osMap -> osMapNames.add(new OsMapName(osMap.getName())));
        detailedFell.setOsMapNames(osMapNames);

        return detailedFell;
    }
}
