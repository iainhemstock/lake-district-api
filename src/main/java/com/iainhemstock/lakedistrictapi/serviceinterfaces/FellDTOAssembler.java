package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.dtos.FellDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;

public interface FellDTOAssembler {
    FellDTO toDTO(final Fell fell);
}
