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
import com.iainhemstock.lakedistrictapi.services.mappers.FellSimplifiedPagedCollectionMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class FellServiceImplTests {

    private FellServiceImpl fellService;
    private Fell helvellynFell;
    private TestApiProperties apiProperties;

    @Mock private ApiClockService apiClockService;
    @Mock private FellRepository fellRepository;
    @Mock private FellSimplifiedPagedCollectionMapper fellSimplifiedPagedCollectionMapper;
    @Mock private MeterToFeetConversionService meterToFeetConversionService;
    @Mock private LatLongToDmsConversionService latLongToDmsConversionService;

    @Before
    public void setUp() {
        apiProperties = new TestApiProperties();
        helvellynFell = new HelvellynFell();

        fellService = new FellServiceImpl(
            fellRepository,
            apiClockService,
            fellSimplifiedPagedCollectionMapper,
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
}
