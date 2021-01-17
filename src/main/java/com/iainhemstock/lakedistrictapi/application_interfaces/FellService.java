package com.iainhemstock.lakedistrictapi.application_interfaces;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPageRequest;

public interface FellService {
    Fell getById(final OsMapRef osMapRef);
    ResultPage<Fell> getFells(final int offset, final int limit, final String sort);
    ResultPage<Fell> getFells(final ResultPageRequest pageRequest);
}
