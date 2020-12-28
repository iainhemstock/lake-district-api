package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.assemblers;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.LinksDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.SimpleFellDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.SimpleFellsDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.application_interfaces.SimpleFellsDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SimpleFellsDTOAssemblerImpl implements SimpleFellsDTOAssembler {
    private ApiProperties apiProperties;

    @Autowired
    public SimpleFellsDTOAssemblerImpl(final ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    @Override
    public SimpleFellsDTO toDTO(final Set<Fell> fells) {
        if (fells == null)
            throw new NullPointerException("Set of [Fell] cannot be null");

        SimpleFellsDTO simpleFellsDTO = new SimpleFellsDTO();
        simpleFellsDTO.setFells(new LinkedHashSet<>());

        for (final Fell fell : fells) {
            if (fell == null)
                throw new NullPointerException("Cannot map null Fell");
            LinksDTO linksDTO = new LinksDTO();
            linksDTO.setLinks(Map.of("self", String.format("%s/fells/%s", apiProperties.getBaseUrl(), fell.getOsMapRef())));
            simpleFellsDTO.getFells().add(new SimpleFellDTO(fell.getName().toString(), fell.getRegion().toString(), linksDTO));
        }

        return simpleFellsDTO;
    }
}
