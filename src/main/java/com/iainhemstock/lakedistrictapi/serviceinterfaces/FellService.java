package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import org.springframework.data.domain.Page;

public interface FellService {
    Fell getById(final OsMapRef osMapRef);
    Page<Fell> getFells(int offset, int limit);
}
