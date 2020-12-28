package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.dtos.SimpleFellsDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;

import java.util.Set;

public interface SimpleFellsDTOAssembler {
    SimpleFellsDTO toDTO(final Set<Fell> fells);
}
