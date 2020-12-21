package com.iainhemstock.lakedistrictapi.config.modelmapper;

import com.iainhemstock.lakedistrictapi.config.modelmapper.converters.ClassificationConverter;
import com.iainhemstock.lakedistrictapi.config.modelmapper.converters.DmsConverter;
import com.iainhemstock.lakedistrictapi.config.modelmapper.converters.OsMapConverter;
import com.iainhemstock.lakedistrictapi.domain.DetailedFell;
import com.iainhemstock.lakedistrictapi.dtos.DetailedFellDTO;
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
        mm.createTypeMap(DetailedFell.class, DetailedFellDTO.class)
            .addMappings(mapper -> {
               mapper.map(source -> source.getFellName().toString(), (dest, v) -> dest.setName((String) v));
               mapper.map(source -> source.getRegionName().toString(), (dest, v) -> dest.setRegion((String) v));
               mapper.using(new OsMapConverter()).map(DetailedFell::getOsMapNames, (dest, v) -> dest.setOsMapNames((Set) v));
               mapper.using(new ClassificationConverter()).map(DetailedFell::getClassificationNames, (dest, v) -> dest.setClassificationNames((Set) v));
               mapper.using(new DmsConverter()).map(DetailedFell::getConvertedLatitude, (dest, v) -> dest.setLatitudeAsDms((Map) v));
               mapper.using(new DmsConverter()).map(DetailedFell::getConvertedLongitude, (dest, v) -> dest.setLongitudeAsDms((Map) v));
            });
        return mm;
    }

}
