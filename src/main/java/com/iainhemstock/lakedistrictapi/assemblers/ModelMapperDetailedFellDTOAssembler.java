package com.iainhemstock.lakedistrictapi.assemblers;

import com.iainhemstock.lakedistrictapi.dtos.FellDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.FellDTOAssembler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperDetailedFellDTOAssembler implements FellDTOAssembler {

    private ModelMapper mapper;

    @Autowired
    public ModelMapperDetailedFellDTOAssembler(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public FellDTO toDTO(final FellEntity fellEntity) {
        return mapper.map(fellEntity, FellDTO.class);
    }
}
