package com.iainhemstock.lakedistrictapi.application_logic;

import com.iainhemstock.lakedistrictapi.application_interfaces.FellService;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
    public Page<FellEntity> getFells(final int offset, final int limit) {
        if (offset < 0)
            throw new IllegalArgumentException("Offset cannot be negative");

        if (limit <= 0)
            throw new IllegalArgumentException("Limit cannot be negative or zero");

        return fellRepository.findAll(PageRequest.of(offset, limit));
    }


}
