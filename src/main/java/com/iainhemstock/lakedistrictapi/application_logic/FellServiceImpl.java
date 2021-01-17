package com.iainhemstock.lakedistrictapi.application_logic;

import com.iainhemstock.lakedistrictapi.application_interfaces.FellService;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPage;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPageRequest;

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
    public ResultPage<Fell> getFells(final ResultPageRequest pageRequest) {
        return fellRepository.findAll(pageRequest);
    }
}
