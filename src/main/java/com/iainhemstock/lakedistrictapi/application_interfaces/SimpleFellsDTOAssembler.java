package com.iainhemstock.lakedistrictapi.application_interfaces;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.SimpleFellsDTO;

import java.util.Set;

public interface SimpleFellsDTOAssembler {
    SimpleFellsDTO toDTO(final Set<FellEntity> fellEntities);
}
