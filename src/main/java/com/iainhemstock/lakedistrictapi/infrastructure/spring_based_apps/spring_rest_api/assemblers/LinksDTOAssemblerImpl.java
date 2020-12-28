package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.assemblers;

import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.LinksDTO;
import com.iainhemstock.lakedistrictapi.application_interfaces.LinksDTOAssembler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LinksDTOAssemblerImpl implements LinksDTOAssembler {

    @Override
    public LinksDTO toDTO(final Links links) {
        LinksDTO linksDTO = new LinksDTO();
        Map<String, String> linksMap = new HashMap<>();
        links.forEach(link -> linksMap.put(link.getRel().toString(), link.getHref()));
        linksDTO.setLinks(linksMap);
        return linksDTO;
    }
}
