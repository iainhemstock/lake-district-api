package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.ApiClockService;
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
public class FellEntityServiceImplTests {

    private FellEntityServiceImpl fellEntityService;
    private FellEntity helvellynFellEntity;
    private TestApiProperties apiProperties;

    @Mock private ApiClockService apiClockService;
    @Mock private FellRepository fellRepository;
    @Mock private FellSimplifiedPagedCollectionMapper fellSimplifiedPagedCollectionMapper;

    @Before
    public void setUp() {
        apiProperties = new TestApiProperties();
        helvellynFellEntity = new HelvellynFellEntity();

        fellEntityService = new FellEntityServiceImpl(
            fellRepository,
            apiClockService,
            fellSimplifiedPagedCollectionMapper,
            apiProperties);

    }

    @Test
    public void get_fell_by_id() {
        Mockito.when(fellRepository.findById(helvellynFellEntity.getOsMapRef()))
            .thenReturn(Optional.of(helvellynFellEntity));
        FellEntity actualFellEntity = fellEntityService.getById(new OsMapRef(helvellynFellEntity.getOsMapRef()));
        assertThat(actualFellEntity, is(helvellynFellEntity));
    }

    @Test
    public void will_throw_when_fell_not_found() {
        try {
            fellEntityService.getById(new OsMapRef("NY000000"));
            fail("Expected method under test to throw FellNotFoundException but it didn't");
        }
        catch (FellNotFoundException ex) {
            assertThat(ex.getMessage(), is("Fell was not found for {id=NY000000}"));
        }
    }

    @Test
    public void will_throw_when_getting_fell_with_blank_id() {
        try {
            fellEntityService.getById(new OsMapRef(""));
            fail("Expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("OsMapRef cannot be blank"));
        }
    }

    @Test
    public void will_throw_when_getting_fell_with_null_id() {
        try {
            fellEntityService.getById(new OsMapRef(null));
            fail("Expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is("OsMapRef cannot be null"));
        }
    }

//    @Test
//    public void gets_detailed_fell() {
//        Mockito.when(fellRepository.findById(helvellynFellEntity.getOsMapRef()))
//            .thenReturn(Optional.of(helvellynFellEntity));
//        Mockito.when(meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(helvellynFellEntity.getHeightMeters())))
//            .thenReturn(new Feet(3117));
//        Mockito.when(meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(helvellynFellEntity.getProminenceMeters())))
//            .thenReturn(new Feet(2336));
//        Mockito.when(latLongToDmsConversionService.getDegrees())
//            .thenReturn(new Degrees(0), new Degrees(0));
//        Mockito.when(latLongToDmsConversionService.getMinutes())
//            .thenReturn(new Minutes(0), new Minutes(0));
//        Mockito.when(latLongToDmsConversionService.getSeconds())
//            .thenReturn(new Seconds(0), new Seconds(0));
//        Mockito.when(latLongToDmsConversionService.getHemisphere())
//            .thenReturn(new Hemisphere(""), new Hemisphere(""));
//        Mockito.when(endpointGeneratorService.generateForResourceWithId("fells", helvellynFellEntity.getOsMapRef()))
//            .thenReturn(new Link(String.format("%s/fells/%s", apiProperties.getBaseUrl(), helvellynFellEntity.getOsMapRef())));
//        Mockito.when(endpointGeneratorService.generateForResourceWithId("fells", helvellynFellEntity.getParentPeak().getOsMapRef()))
//            .thenReturn(new Link(String.format("%s/fells/%s", apiProperties.getBaseUrl(), helvellynFellEntity.getParentPeak().getOsMapRef())));
//
//        assertThat(fellEntityService.getDetailedFell(new OsMapRef(helvellynFellEntity.getOsMapRef())),
//            is(new DetailedFell(helvellynFellEntity,
//                meterToFeetConversionService,
//                latLongToDmsConversionService,
//                endpointGeneratorService)));
//    }

//    @Test
//    public void will_throw_when_fell_not_found() {
//        try {
//            fellEntityService.getDetailedFell(new OsMapRef("NY000000"));
//            fail("Expected method under test to throw FellNotFoundException but it didn't");
//        }
//        catch (FellNotFoundException ex) {
//            assertThat(ex.getMessage(), is(String.format("Fell was not found for {id=%s}", "NY000000")));
//        }
//    }



//    @Test
//    public void get_paged_collection() {
//        int offset = 25;
//        int limit = 1;
//        PagedCollection expectedPagedCollection = new PagedCollection(offset, limit, List.of(helvellynFellEntity), fellPage.getTotalPages(), fellPage.getTotalElements());
//        expectedPagedCollection.setFirstLink(new Link("http://localhost:8080/api/v1/fells?offset=0&limit=1"));
//        expectedPagedCollection.setPrevLink(new Link("http://localhost:8080/api/v1/fells?offset=24&limit=1"));
//        expectedPagedCollection.setSelfLink(new Link("http://localhost:8080/api/v1/fells?offset=25&limit=1"));
//        expectedPagedCollection.setNextLink(new Link("http://localhost:8080/api/v1/fells?offset=26&limit=1"));
//        expectedPagedCollection.setLastLink(new Link("http://localhost:8080/api/v1/fells?offset=213&limit=1"));
//        expectedPagedCollection.setOffset(offset);
//        expectedPagedCollection.setLimit(limit);
//        expectedPagedCollection.setTotalItems(214);
//        expectedPagedCollection.setItems(new SummarisedFells(
//            new SummarisedFell(new FellName("Helvellyn"), new Link("http://localhost:8080/api/v1/fells/NY342151"))));
//        Mockito.when(fellRepository.findAll(PageRequest.of(offset, limit)))
//            .thenReturn(fellPage);
//        Mockito.when(fellPage.toList())
//            .thenReturn(List.of(helvellynFellEntity));
//        Mockito.when(fellPage.getTotalElements())
//            .thenReturn(214L);
//        Mockito.when(fellPage.getTotalPages())
//            .thenReturn(213);
//
//        PagedCollection actualPagedCollection = fellService.getSummarisedFells2(offset, limit);
//
//        assertThat(actualPagedCollection,
//            is(expectedPagedCollection));
//    }
}
