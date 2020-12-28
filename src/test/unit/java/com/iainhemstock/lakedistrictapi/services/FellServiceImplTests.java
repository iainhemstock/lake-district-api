package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFell;
import com.iainhemstock.lakedistrictapi.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.ApiClockService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.MeterToFeetConversionService;
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
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

@RunWith(JUnitParamsRunner.class)
public class FellServiceImplTests {

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    private FellServiceImpl fellService;
    private Fell helvellynFell;
    private TestApiProperties apiProperties;

    @Mock private ApiClockService apiClockService;
    @Mock private FellRepository fellRepository;
    @Mock private MeterToFeetConversionService meterToFeetConversionService;
    @Mock private LatLongToDmsConversionService latLongToDmsConversionService;

    @Before
    public void setUp() {
        apiProperties = new TestApiProperties();
        helvellynFell = new HelvellynFell();

        fellService = new FellServiceImpl(
            fellRepository,
            apiClockService,
            apiProperties,
            meterToFeetConversionService,
            latLongToDmsConversionService);

    }

    @Test
    public void get_fell_by_id() {
        Mockito.when(fellRepository.findById(helvellynFell.getOsMapRef()))
            .thenReturn(Optional.of(helvellynFell));
        Fell actualFell = fellService.getById(helvellynFell.getOsMapRef());
        assertThat(actualFell, is(helvellynFell));
    }

    @Test
    public void will_throw_when_fell_not_found() {
        try {
            fellService.getById(new OsMapRef("NY000000"));
            fail("Expected method under test to throw FellNotFoundException but it didn't");
        }
        catch (FellNotFoundException ex) {
            assertThat(ex.getMessage(), is("Fell was not found for {id=NY000000}"));
        }
    }

    @Test
    public void will_throw_when_getting_fell_with_blank_id() {
        try {
            fellService.getById(new OsMapRef(""));
            fail("Expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("OsMapRef cannot be blank"));
        }
    }

    @Test
    public void will_throw_when_getting_fell_with_null_id() {
        try {
            fellService.getById(new OsMapRef(null));
            fail("Expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is("OsMapRef cannot be null"));
        }
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

        Page<Fell> actualFellsPage = fellService.getFells(offset, limit);

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
            .thenReturn(new PageImpl<>(List.of(new HelvellynFell()), PageRequest.of(offset, limit), totalElements));

        Page<Fell> actualFellsPage = fellService.getFells(offset, limit);

        assertThat(actualFellsPage.toList(), is(List.of(new HelvellynFell())));
        assertThat(actualFellsPage.getTotalElements(), is(totalElements));
        assertThat(actualFellsPage.getTotalPages(), is(totalPages));
        assertThat(actualFellsPage.getNumberOfElements(), is(limit));
    }
}
