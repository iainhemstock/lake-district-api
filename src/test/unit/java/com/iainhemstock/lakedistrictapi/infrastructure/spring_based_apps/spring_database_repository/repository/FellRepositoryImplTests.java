package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.assembler.DomainToEntityAssembler;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.FellEntityRepository;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.exception_handling.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class FellRepositoryImplTests {
    @Mock private FellEntityRepository fellEntityRepository;
    @Mock private DomainToEntityAssembler domainToEntityAssembler;

    private FellRepository fellRepository;
    private Fell expectedFell;

    @Before
    public void setUp() {
        fellRepository = new FellRepositoryImpl(fellEntityRepository, domainToEntityAssembler);
        expectedFell = new HelvellynFell();

        FellEntity helvellynFellEntity = new HelvellynFellEntity();
        Mockito.when(fellEntityRepository.findById(helvellynFellEntity.getOsMapRef())).thenReturn(Optional.of(helvellynFellEntity));
        Mockito.when(domainToEntityAssembler.toDomain(helvellynFellEntity)).thenReturn(expectedFell);
    }

    @Test
    public void given_os_map_ref_when_finding_fell_by_its_id_then_correct_fell_is_returned() {
        Fell actualFell = fellRepository.findFellById(expectedFell.getOsMapRef());
        assertThat(actualFell, is(equalTo(expectedFell)));
    }

    @Test
    public void will_throw_when_fell_not_found() {
        try {
            fellRepository.findFellById(new OsMapRef("NY000000"));
            fail("Expected method under test to throw FellNotFoundException but it didn't");
        }
        catch (FellNotFoundException ex) {
            assertThat(ex.getMessage(), is("Fell was not found for {id=NY000000}"));
        }
    }

    @Test
    public void will_throw_when_getting_fell_with_blank_id() {
        try {
            fellRepository.findFellById(new OsMapRef(""));
            fail("Expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("OsMapRef cannot be blank"));
        }
    }

    @Test
    public void will_throw_when_getting_fell_with_null_id() {
        try {
            fellRepository.findFellById(new OsMapRef(null));
            fail("Expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is("OsMapRef cannot be null"));
        }
    }
}
