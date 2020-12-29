package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.assemblers;

import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.domain.SimpleFell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.RepoResult;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.PagedCollectionDTO;
import org.springframework.stereotype.Component;

@Component
public class PagedCollectionDTOAssembler<T> {

    public PagedCollectionDTO<SimpleFell> toDTO(final Links links, final RepoResult<SimpleFell> repoResult) {
        throw new UnsupportedOperationException();
    }

}
