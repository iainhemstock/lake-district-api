package com.iainhemstock.lakedistrictapi.application_interfaces;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;

public interface FellService {
    Fell getById(final OsMapRef osMapRef);
    <T> RepoPage<T> getFells(final int offset, final int limit, final Class<T> projection);
}
