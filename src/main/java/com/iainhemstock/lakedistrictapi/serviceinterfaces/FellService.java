package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.dtos.PagedCollectionDTO;
import com.iainhemstock.lakedistrictapi.dtos.SummarisedFellDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;

public interface FellService {
    Fell getById(final OsMapRef osMapRef);
    PagedCollectionDTO<SummarisedFellDTO> getSummarisedFells(final Integer offset, final Integer limit);
}
