package com.iainhemstock.lakedistrictapi.assemblers;

import com.iainhemstock.lakedistrictapi.dtos.DetailedFellDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.DetailedFellDTOAssembler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperDetailedFellDTOAssembler implements DetailedFellDTOAssembler {

    private ModelMapper mapper;

    @Autowired
    public ModelMapperDetailedFellDTOAssembler(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public DetailedFellDTO toDTO(final FellEntity fellEntity) {
        return mapper.map(fellEntity, DetailedFellDTO.class);
    }
}
