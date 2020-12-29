package com.iainhemstock.lakedistrictapi.application_interfaces;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.domain.SimpleFell;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;

public interface FellService {
    Fell getById(final OsMapRef osMapRef);
    RepoPage<SimpleFell> getSimpleFells(int offset, int limit);
}
