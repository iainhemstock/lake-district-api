package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.dtos.LinksDTO;

public interface LinksDTOAssembler {
    LinksDTO toDTO(final Links links);
}
