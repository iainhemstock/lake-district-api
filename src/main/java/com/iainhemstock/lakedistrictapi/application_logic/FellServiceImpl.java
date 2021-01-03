package com.iainhemstock.lakedistrictapi.application_logic;

import com.iainhemstock.lakedistrictapi.application_interfaces.FellService;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;

public class FellServiceImpl implements FellService {
    private final FellRepository fellRepository;

    public FellServiceImpl(final FellRepository fellRepository) {
        this.fellRepository = fellRepository;
    }

    @Override
    public Fell getById(final OsMapRef osMapRef) {
        return fellRepository.findById(osMapRef);
    }

    @Override
    public <T> RepoPage<T> getFells(final int offset, final int limit, final Class<T> projection) {
        if (offset < 0)
            throw new IllegalArgumentException("Offset cannot be negative");

        if (limit <= 0)
            throw new IllegalArgumentException("Limit cannot be negative or zero");

        return fellRepository.findAll(offset, limit, projection);
    }
}
