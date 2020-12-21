package com.iainhemstock.lakedistrictapi.serviceinterfaces;

import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;

public interface FellEntityService {
    FellEntity getById(final OsMapRef osMapRef);
}
