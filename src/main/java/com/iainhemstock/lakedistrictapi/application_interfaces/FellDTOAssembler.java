package com.iainhemstock.lakedistrictapi.application_interfaces;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.FellDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;

public interface FellDTOAssembler {
    FellDTO toDTO(final Fell fell);
}
