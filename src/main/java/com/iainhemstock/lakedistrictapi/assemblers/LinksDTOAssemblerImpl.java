package com.iainhemstock.lakedistrictapi.assemblers;

import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.dtos.LinksDTO;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LinksDTOAssembler;
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
