package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.dtos.FellDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;

public interface FellDTOAssembler {
    FellDTO toDTO(final FellEntity fellEntity);
}
