package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.application_interfaces.ApiClockService;
import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.GreatGableFellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.assembler.DomainToEntityAssembler;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.FellEntityRepository;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class FellRepositoryImplTests {
    private static final String NOW = "2018-12-10T13:45:00.000Z";

    @Mock private FellEntityRepository fellEntityRepository;
    @Mock private DomainToEntityAssembler domainToEntityAssembler;
    @Mock private ApiClockService apiClockService;

    private FellRepository fellRepository;
    private Fell expectedFell;
    private ApiProperties apiProperties;

    @Before
    public void setUp() {
        apiProperties = new TestApiProperties();
        fellRepository = new FellRepositoryImpl(fellEntityRepository, domainToEntityAssembler, apiClockService, apiProperties);
        expectedFell = new HelvellynFell();

        FellEntity helvellynFellEntity = new HelvellynFellEntity();
        Mockito.when(fellEntityRepository.findById(helvellynFellEntity.getOsMapRef())).thenReturn(Optional.of(helvellynFellEntity));
        Mockito.when(domainToEntityAssembler.toDomain(helvellynFellEntity)).thenReturn(expectedFell);
        Mockito.when(apiClockService.now()).thenReturn(NOW);
    }

    @Test
    public void given_os_map_ref_when_finding_fell_by_its_id_then_correct_fell_is_returned() {
        Fell actualFell = fellRepository.findById(expectedFell.getOsMapRef());
        assertThat(actualFell, is(equalTo(expectedFell)));
    }

    @Test
    public void will_throw_when_fell_not_found() {
        try {
            fellRepository.findById(new OsMapRef("NY000000"));
            fail("Expected method under test to throw FellNotFoundException but it didn't");
        }
        catch (FellNotFoundException ex) {
            assertThat(ex.getMessage(), is("Fell was not found for {os map ref=NY000000}"));
            assertThat(ex.getTimestamp(), is(NOW));
        }
    }

    @Test
    public void will_throw_when_getting_fell_with_blank_id() {
        try {
            fellRepository.findById(new OsMapRef(""));
            fail("Expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("OsMapRef cannot be blank"));
        }
    }

    @Test
    public void will_throw_when_getting_fell_with_null_id() {
        try {
            fellRepository.findById(new OsMapRef(null));
            fail("Expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is("OsMapRef cannot be null"));
        }
    }

    @Test
    public void given_no_fells_then_empty_repo_result_is_returned() {
        final int offset = 0;
        final int limit = 1;
        Mockito.when(fellEntityRepository.findAll(PageRequest.of(offset, limit)))
            .thenReturn(Page.empty(PageRequest.of(offset, limit)));

        RepoPage<SimpleFell> repoPage = fellRepository.findAll(offset, limit);

        assertThat(repoPage.getItems(), is(Collections.EMPTY_SET));
        assertThat(repoPage.getTotalItemsAvailable(), is(0));
        assertThat(repoPage.getTotalPages(), is(1));
        assertThat(repoPage.getItemsCount(), is(0));
    }

    @Test
    public void given_fell_exists_then_fell_repo_result_is_returned() {
        int offset = 0;
        int limit = 1;
        long totalItemsAvailable = 3; // imagine three fells in the db
        int totalPages = 3; // three fells at one page limit == three pages
        Mockito.when(fellEntityRepository.findAll(PageRequest.of(offset, limit)))
            .thenReturn(new PageImpl<>(List.of(new GreatGableFellEntity()), PageRequest.of(offset, limit), totalItemsAvailable));
        Set<SimpleFell> expectedSimpleFells = Set.of(new SimpleFell(
            new FellName("Great Gable"),
            new RegionName("Central Lake District"),
            Set.of(new Link(LinkRel.SELF, String.format("%s/fells/%s", apiProperties.getBaseUrl(), new GreatGableFellEntity().getOsMapRef())))));

        RepoPage<SimpleFell> repoPage = fellRepository.findAll(offset, limit);

        assertEquals(expectedSimpleFells, repoPage.getItems());
        assertThat(repoPage.getTotalItemsAvailable(), is((int) totalItemsAvailable));
        assertThat(repoPage.getTotalPages(), is(totalPages));
        assertThat(repoPage.getItemsCount(), is(limit));
    }
}
