package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.assembler.DomainToEntityAssembler;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.FellEntityRepository;
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
        expectedFell = new Fell();
        expectedFell.setOsMapRef(new OsMapRef("NY342151"));
        expectedFell.setName(new FellName("Helvellyn"));
        expectedFell.setHeightMeters(new Meters(950));
        expectedFell.setHeightFeet(new Feet(3117));
        expectedFell.setProminenceMeters(new Meters(712));
        expectedFell.setProminenceFeet(new Feet(2336));
        expectedFell.setClassificationNames(new ClassificationNames(Set.of(new ClassificationName("Marilyn"))));
        expectedFell.setOsMapNames(new OsMapNames(Set.of(new OsMapName("OS Landranger 90"), new OsMapName("OS Explorer OL5"))));
        expectedFell.setLatitude(new Latitude(54.527232));
        expectedFell.setLongitude(new Longitude(-3.016054));
        expectedFell.setRegionName(new RegionName("Eastern Lake District"));
        expectedFell.setConvertedLatitude(new DMS(new Degrees(0), new Minutes(0), new Seconds(0), new Hemisphere("")));
        expectedFell.setConvertedLongitude(new DMS(new Degrees(0), new Minutes(0), new Seconds(0), new Hemisphere("")));

        FellEntity helvellynFellEntity = new HelvellynFellEntity();
        Mockito.when(fellEntityRepository.findById(helvellynFellEntity.getOsMapRef())).thenReturn(Optional.of(helvellynFellEntity));
        Mockito.when(domainToEntityAssembler.toDomain(helvellynFellEntity)).thenReturn(expectedFell);
    }

    @Test
    public void given_os_map_ref_when_finding_fell_by_its_id_then_correct_fell_is_returned() {
        Optional<Fell> actualFell = fellRepository.findFellById(expectedFell.getOsMapRef());
        assertThat(actualFell, is(equalTo(Optional.of(expectedFell))));
    }
}
