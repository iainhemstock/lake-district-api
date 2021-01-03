package com.iainhemstock.lakedistrictapi.application_logic;

import com.iainhemstock.lakedistrictapi.application_interfaces.FellService;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellSummaryProjection;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.HelvellynFellEntity;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
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
        Mockito.when(fellRepository.findById(new OsMapRef(helvellynFellEntity.getOsMapRef()))).thenReturn(helvellynFell);
        Fell actualFell = fellService.getById(new OsMapRef(helvellynFellEntity.getOsMapRef()));
        assertThat(actualFell, is(helvellynFell));
    }

    @Test
    public void given_negative_offset_when_requesting_fells_then_exception_is_thrown() {
        try {
            int invalidOffset = -1;
            int anyLimit = 0;
            fellService.getFells(invalidOffset, anyLimit, FellSummaryProjection.class);
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
            fellService.getFells(anyOffset, invalidLimit, FellSummaryProjection.class);
            fail("Expected method under test to throw InvalidArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("Limit cannot be negative or zero"));
        }
    }

}
