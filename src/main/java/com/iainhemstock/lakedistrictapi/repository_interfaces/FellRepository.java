package com.iainhemstock.lakedistrictapi.repository_interfaces;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;

public interface FellRepository {
    Fell findById(OsMapRef osMapRef);
    <T> RepoPage<T> findAll(final int offset, final int limit, final Class<T> projection);
}
