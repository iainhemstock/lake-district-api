package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.assemblers;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.FellDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.FellDTOAssembler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperFellDTOAssembler implements FellDTOAssembler {

    private ModelMapper mapper;

    @Autowired
    public ModelMapperFellDTOAssembler(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public FellDTO toDTO(final Fell fell) {
        return mapper.map(fell, FellDTO.class);
    }
}
