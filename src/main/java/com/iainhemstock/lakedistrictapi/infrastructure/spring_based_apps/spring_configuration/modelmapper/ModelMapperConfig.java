package com.iainhemstock.lakedistrictapi.config.modelmapper;

import com.iainhemstock.lakedistrictapi.config.modelmapper.converters.ClassificationConverter;
import com.iainhemstock.lakedistrictapi.config.modelmapper.converters.DmsConverter;
import com.iainhemstock.lakedistrictapi.config.modelmapper.converters.OsMapConverter;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.FellDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.Fell;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();
        mm.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT);
        mm.createTypeMap(Fell.class, FellDTO.class)
            .addMappings(mapper -> {
                mapper.map(source -> source.getName().toString(), (dest, v) -> dest.setName((String) v));
                mapper.map(source -> source.getRegion().getRegionName().toString(), (dest, v) -> dest.setRegion((String) v));
                mapper.map(source -> source.getHeightFeet().toString(), (dest, v) -> dest.setHeightFeet((String) v));
                mapper.map(source -> source.getProminenceFeet().toString(), (dest, v) -> dest.setProminenceFeet((String) v));
                mapper.using(new OsMapConverter()).map(Fell::getOsMaps, (dest, v) -> dest.setOsMapNames((Set) v));
                mapper.using(new ClassificationConverter()).map(Fell::getClassifications, (dest, v) -> dest.setClassificationNames((Set) v));
                mapper.using(new DmsConverter()).map(Fell::getConvertedLatitude, (dest, v) -> dest.setLatitudeAsDms((Map) v));
                mapper.using(new DmsConverter()).map(Fell::getConvertedLongitude, (dest, v) -> dest.setLongitudeAsDms((Map) v));
            });
        return mm;
    }

}
