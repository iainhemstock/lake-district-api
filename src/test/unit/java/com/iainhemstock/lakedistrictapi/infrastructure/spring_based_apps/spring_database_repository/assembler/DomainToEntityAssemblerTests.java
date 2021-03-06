package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.assembler;

import com.iainhemstock.lakedistrictapi.application_interfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.application_interfaces.MeterToFeetConversionService;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.testdatafactories.FellEntityTestDataFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@RunWith(MockitoJUnitRunner.class)
public class DomainToEntityAssemblerTests {

    @Mock private LatLongToDmsConversionService latLongToDmsConversionService;
    @Mock private MeterToFeetConversionService meterToFeetConversionService;

    private DomainToEntityAssembler domainToEntityAssembler;
    private FellEntity entity;
    private Fell domain;

    @Before
    public void setUp() {
        domainToEntityAssembler = new DomainToEntityAssembler(latLongToDmsConversionService, meterToFeetConversionService);
        entity = FellEntityTestDataFactory.helvellynFellEntity();
        domain = domainToEntityAssembler.toDomain(entity);
    }

    @Test
    public void will_map_fell_name_from_entity_to_domain() {
        assertThat(domain.getName(), is(new FellName(entity.getName())));
    }

    @Test
    public void will_map_os_map_ref_from_entity_to_domain() {
        assertThat(domain.getOsMapRef(), is(new OsMapRef(entity.getOsMapRef())));
    }

    @Test
    public void will_map_parent_os_map_ref_from_entity_to_domain() {
        assertThat(domain.getParentOsMapRef(), is(new OsMapRef(entity.getParentOsMapRef())));
    }

    @Test
    public void will_not_map_parent_os_map_ref_from_entity_to_domain_when_entity_has_no_parent() {
        Fell scafellPikeFell = domainToEntityAssembler.toDomain(FellEntityTestDataFactory.scafellPike());
        assertThat(scafellPikeFell.getParentOsMapRef(), is(nullValue()));
    }

    @Test
    public void will_map_height_in_meters_from_entity_to_domain() {
        assertThat(domain.getHeightMeters(), is(new Meters(entity.getHeightMeters())));
    }

    @Test
    public void will_map_height_in_feet_from_entity_to_domain() {
        Mockito.when(meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(entity.getHeightMeters()))).thenReturn(new Feet(123));
        domain = domainToEntityAssembler.toDomain(entity);
        assertThat(domain.getHeightFeet(), is(new Feet(123)));
    }

    @Test
    public void will_map_prominence_in_meters_from_entity_to_domain() {
        assertThat(domain.getProminenceMeters(), is(new Meters(entity.getProminenceMeters())));
    }

    @Test
    public void will_map_prominence_in_feet_from_entity_to_domain() {
        Mockito.when(meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(entity.getProminenceMeters()))).thenReturn(new Feet(456));
        domain = domainToEntityAssembler.toDomain(entity);
        assertThat(domain.getProminenceFeet(), is(new Feet(456)));
    }

    @Test
    public void will_map_classifications_from_entity_to_domain() {
        assertThat(domain.getImmutableClassificationNames(),
            is(Set.of(new ClassificationName("Marilyn"))));
    }

    @Test
    public void will_map_os_maps_from_entity_to_domain() {
        assertThat(domain.getImmutableOsMapNames(),
            is(Set.of(new OsMapName("OS Landranger 90"), new OsMapName("OS Explorer OL5"))));
    }

    @Test
    public void will_map_latitude_from_entity_to_domain() {
        assertThat(domain.getLatitude(), is(new Latitude(entity.getLatitude())));
    }

    @Test
    public void will_map_longitude_from_entity_to_domain() {
        assertThat(domain.getLongitude(), is(new Longitude(entity.getLongitude())));
    }

    @Test
    public void will_map_region_from_entity_to_domain() {
        assertThat(domain.getRegionName(), is(new RegionName(entity.getRegionEntity().getName())));
    }

    @Test
    public void will_map_dms_converted_from_latitude_from_entity_to_domain() {
        Mockito.when(latLongToDmsConversionService.getDegrees()).thenReturn(new Degrees(10));
        Mockito.when(latLongToDmsConversionService.getMinutes()).thenReturn(new Minutes(20));
        Mockito.when(latLongToDmsConversionService.getSeconds()).thenReturn(new Seconds(30));
        Mockito.when(latLongToDmsConversionService.getHemisphere()).thenReturn(new Hemisphere("N"));

        domain = domainToEntityAssembler.toDomain(entity);

        assertThat(domain.getConvertedLatitude(), is(new DMS(
            new Degrees(10), new Minutes(20), new Seconds(30), new Hemisphere("N"))));
    }

    @Test
    public void will_map_dms_converted_from_longitude_from_entity_to_domain() {
        Mockito.when(latLongToDmsConversionService.getDegrees()).thenReturn(new Degrees(40));
        Mockito.when(latLongToDmsConversionService.getMinutes()).thenReturn(new Minutes(50));
        Mockito.when(latLongToDmsConversionService.getSeconds()).thenReturn(new Seconds(60));
        Mockito.when(latLongToDmsConversionService.getHemisphere()).thenReturn(new Hemisphere("E"));

        domain = domainToEntityAssembler.toDomain(entity);

        assertThat(domain.getConvertedLongitude(), is(new DMS(
            new Degrees(40), new Minutes(50), new Seconds(60), new Hemisphere("E"))));
    }
}
