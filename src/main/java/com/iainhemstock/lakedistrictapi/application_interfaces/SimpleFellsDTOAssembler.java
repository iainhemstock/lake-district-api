package com.iainhemstock.lakedistrictapi.application_interfaces;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.SimpleFellsDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.Fell;

import java.util.Set;

public interface SimpleFellsDTOAssembler {
    SimpleFellsDTO toDTO(final Set<Fell> fells);
}
