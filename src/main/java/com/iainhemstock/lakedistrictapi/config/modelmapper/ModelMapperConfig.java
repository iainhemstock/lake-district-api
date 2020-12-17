package com.iainhemstock.lakedistrictapi.config.modelmapper;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.config.modelmapper.converters.*;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.dtos.DmsDTO;
import com.iainhemstock.lakedistrictapi.dtos.FellDetailedDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;

@Configuration
public class ModelMapperConfig {

    private ApiProperties apiProperties;

    @Autowired
    public ModelMapperConfig(final ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();
        mm.createTypeMap(Fell.class, FellDetailedDTO.class)
            .addMappings(mapper -> {
                mapper.map(Fell::getLatitude, (dest, v) -> dest.getLocation().setLatitude((String) v));
                mapper.map(Fell::getLongitude, (dest, v) -> dest.getLocation().setLongitude((String) v));
                mapper.map(Fell::getOsMapRef, (dest, v) -> dest.getLocation().setOs_map_ref((String) v));
                mapper.map(fell -> fell.getRegion().getName(), (dest, v) -> dest.getLocation().setRegion((String) v));
                mapper.using(new OsMapConverter()).map(Fell::getOsMaps, (dest, v) -> dest.getLocation().setOs_maps((Set) v));
                mapper.using(new MeterToFeetConverter()).map(Fell::getHeightMeters, (dest, v) -> dest.getHeight().setFeet((String) v));
                mapper.using(new DmsLatitudeConverter()).map(Fell::getLatitude, (dest, v) -> dest.getLocation().setDms_latitude((DmsDTO) v));
                mapper.using(new ClassificationConverter()).map(Fell::getClassifications, (dest, v) -> dest.setClassifications((Set) v));
                mapper.using(new DmsLongitudeConverter()).map(Fell::getLongitude, (dest, v) -> dest.getLocation().setDms_longitude((DmsDTO) v));
                mapper.using(new MeterToFeetConverter()).map(Fell::getProminenceMeters, (dest, v) -> dest.getProminence().setFeet((String) v));
                mapper.using(new FellIdHrefConverter(apiProperties.getBaseUrl())).map(Fell::getOsMapRef, (dest, v) -> dest.getLinks().setSelf((Link) v));
                mapper.using(new ParentIdHrefConverter(apiProperties.getBaseUrl())).map(Fell::getParentPeak, (dest, v) -> dest.getLinks().setParent((Link) v));
            });

        return mm;
    }

}
