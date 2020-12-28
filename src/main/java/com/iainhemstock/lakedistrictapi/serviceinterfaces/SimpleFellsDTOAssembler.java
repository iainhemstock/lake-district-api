package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.SimpleFellsDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;

import java.util.Set;

public interface SimpleFellsDTOAssembler {
    SimpleFellsDTO toDTO(final Set<Fell> fells);
}
