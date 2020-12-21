package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.dtos.DetailedFellDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;

public interface DetailedFellDTOAssembler {
    DetailedFellDTO toDTO(final FellEntity fellEntity);
}
