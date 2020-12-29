package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.assemblers;

import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.domain.SimpleFell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.PagedCollectionDTO;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PagedCollectionDTOAssembler<T> {

    public PagedCollectionDTO<Set<SimpleFell>> toDTO(final Links links, final RepoPage repoPage) {
        throw new UnsupportedOperationException();
    }

}
