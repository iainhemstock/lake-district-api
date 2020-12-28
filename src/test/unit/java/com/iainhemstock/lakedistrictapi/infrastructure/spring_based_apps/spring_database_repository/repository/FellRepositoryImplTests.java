package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.FellEntityRepository;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class FellRepositoryImplTests {
    @Mock private FellEntityRepository fellEntityRepository;

    private FellRepository fellRepository;
    private Fell expectedFell;

    @Before
    public void setUp() {
        fellRepository = new FellRepositoryImpl(fellEntityRepository);
        expectedFell = new Fell();
    }

    @Test
    public void given_os_map_ref_when_finding_fell_by_its_id_then_correct_fell_is_returned() {
        Optional<Fell> actualFell = fellRepository.findFellById(expectedFell.getOsMapRef());
        assertThat(actualFell, is(equalTo(expectedFell)));
    }
}
