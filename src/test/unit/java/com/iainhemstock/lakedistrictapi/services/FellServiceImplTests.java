package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.application_interfaces.FellService;
import com.iainhemstock.lakedistrictapi.application_logic.FellServiceImpl;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.HelvellynFell;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

@RunWith(JUnitParamsRunner.class)
public class FellServiceImplTests {

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    private FellService fellService;
    private FellEntity helvellynFellEntity;
    private Fell helvellynFell;

    @Mock private FellRepository fellRepository;

    @Before
    public void setUp() {
        helvellynFellEntity = new HelvellynFellEntity();
        helvellynFell = new HelvellynFell();
        fellService = new FellServiceImpl(fellRepository);

    }

    @Test
    public void get_fell_by_id() {
        Mockito.when(fellRepository.findById(helvellynFellEntity.getOsMapRef())).thenReturn(helvellynFell);
        Fell actualFell = fellService.getById(helvellynFellEntity.getOsMapRef());
        assertThat(actualFell, is(helvellynFell));
    }

    @Test
    public void given_negative_offset_when_requesting_fells_then_exception_is_thrown() {
        try {
            int invalidOffset = -1;
            int anyLimit = 0;
            fellService.getFells(invalidOffset, anyLimit);
            fail("Expected method under test to throw InvalidArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("Offset cannot be negative"));
        }
    }

    @Test
    @Parameters({ "-1", "0" })
    public void given_invalid_limit_when_requesting_fells_then_exception_is_thrown(final int invalidLimit) {
        try {
            int anyOffset = 0;
            fellService.getFells(anyOffset, invalidLimit);
            fail("Expected method under test to throw InvalidArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("Limit cannot be negative or zero"));
        }
    }

    @Test
    public void given_no_fells_then_empty_page_is_returned() {
        final int offset = 0;
        final int limit = 1;
        Mockito.when(fellRepository.findAll(PageRequest.of(offset, limit)))
            .thenReturn(Page.empty(PageRequest.of(offset, limit)));

        Page<FellEntity> actualFellsPage = fellService.getFells(offset, limit);

        assertThat(actualFellsPage.toList(), is(equalTo(Collections.EMPTY_LIST)));
        assertThat(actualFellsPage.getTotalElements(), is(0L));
        assertThat(actualFellsPage.getTotalPages(), is(0));
        assertThat(actualFellsPage.getNumberOfElements(), is(0));
    }

    @Test
    public void given_fell_exists_then_fell_page_is_returned() {
        int offset = 0;
        int limit = 1;
        long totalElements = 3L; // imagine the db contains three fells
        int totalPages = 3; // three fells at one page limit == three pages
        Mockito.when(fellRepository.findAll(PageRequest.of(offset, limit)))
            .thenReturn(new PageImpl<>(List.of(new HelvellynFellEntity()), PageRequest.of(offset, limit), totalElements));

        Page<FellEntity> actualFellsPage = fellService.getFells(offset, limit);

        assertThat(actualFellsPage.toList(), is(List.of(new HelvellynFellEntity())));
        assertThat(actualFellsPage.getTotalElements(), is(totalElements));
        assertThat(actualFellsPage.getTotalPages(), is(totalPages));
        assertThat(actualFellsPage.getNumberOfElements(), is(limit));
    }
}
