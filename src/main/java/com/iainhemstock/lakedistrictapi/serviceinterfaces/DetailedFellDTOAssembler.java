package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.domain.DetailedFell;
import com.iainhemstock.lakedistrictapi.dtos.DetailedFellDTO;

public interface DetailedFellDTOAssembler {
    DetailedFellDTO toDTO(final DetailedFell detailedFell);
}
