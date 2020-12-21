package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.dtos.FellDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;

public interface DetailedFellDTOAssembler {
    FellDTO toDTO(final FellEntity fellEntity);
}
