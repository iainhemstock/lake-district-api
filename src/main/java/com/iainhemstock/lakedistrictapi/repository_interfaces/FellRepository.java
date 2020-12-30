package com.iainhemstock.lakedistrictapi.repository_interfaces;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.domain.SimpleFell;

public interface FellRepository {
    Fell findById(OsMapRef osMapRef);
    RepoPage<SimpleFell> findAll(final int offset, final int  limit);
}
