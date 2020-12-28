package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.assembler;

import com.iainhemstock.lakedistrictapi.application_interfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.application_interfaces.MeterToFeetConversionService;
import com.iainhemstock.lakedistrictapi.application_logic.LatLongToDmsConversionServiceImpl;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.entities.classifications.MarilynClassificationEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMapEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL5ExplorerOsMapEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;

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
        entity = new HelvellynFellEntity();
        entity.setLatLongToDmsConversionService(latLongToDmsConversionService);
        domain = domainToEntityAssembler.toDomain(entity);
    }

    @Test
    public void will_map_fell_name_from_entity_to_domain() {
        assertThat(domain.getName(), is(entity.getName()));
    }

    @Test
    public void will_map_os_map_ref_from_entity_to_domain() {
        assertThat(domain.getOsMapRef(), is(entity.getOsMapRef()));
    }

    @Test
    public void will_map_height_in_meters_from_entity_to_domain() {
        assertThat(domain.getHeightMeters(), is(entity.getHeightMeters()));
    }

    @Test
    public void will_map_height_in_feet_from_entity_to_domain() {
        Mockito.when(meterToFeetConversionService.convertRoundedToNearestInteger(entity.getHeightMeters())).thenReturn(new Feet(123));
        domain = domainToEntityAssembler.toDomain(entity);
        assertThat(domain.getHeightFeet(), is(new Feet(123)));
    }

    @Test
    public void will_map_prominence_in_meters_from_entity_to_domain() {
        assertThat(domain.getProminenceMeters(), is(entity.getProminenceMeters()));
    }

    @Test
    public void will_map_prominence_in_feet_from_entity_to_domain() {
        Mockito.when(meterToFeetConversionService.convertRoundedToNearestInteger(entity.getProminenceMeters())).thenReturn(new Feet(456));
        domain = domainToEntityAssembler.toDomain(entity);
        assertThat(domain.getProminenceFeet(), is(new Feet(456)));
    }

    @Test
    public void will_map_classifications_from_entity_to_domain() {
        assertThat(domain.getClassificationNames(),
            is(new ClassificationNames(Set.of(new ClassificationName("Marilyn")))));
    }

    @Test
    public void will_map_os_maps_from_entity_to_domain() {
        assertThat(domain.getOsMapNames(),
            is(new OsMapNames(Set.of(new OsMapName("OS Landranger 90"), new OsMapName("OS Explorer OL5")))));
    }

    @Test
    public void will_map_latitude_from_entity_to_domain() {
        assertThat(domain.getLatitude(), is(entity.getLatitude()));
    }

    @Test
    public void will_map_longitude_from_entity_to_domain() {
        assertThat(domain.getLongitude(), is(entity.getLongitude()));
    }

    @Test
    public void will_map_region_from_entity_to_domain() {
        assertThat(domain.getRegionName(), is(entity.getRegionEntity().getRegionName()));
    }

    @Test
    public void will_map_dms_converted_from_latitude_from_entity_to_domain() {
        Mockito.when(latLongToDmsConversionService.getDegrees()).thenReturn(new Degrees(10));
        Mockito.when(latLongToDmsConversionService.getMinutes()).thenReturn(new Minutes(20));
        Mockito.when(latLongToDmsConversionService.getSeconds()).thenReturn(new Seconds(30));
        Mockito.when(latLongToDmsConversionService.getHemisphere()).thenReturn(new Hemisphere("N"));

        domain = domainToEntityAssembler.toDomain(entity);

        assertThat(domain.getConvertedLatitude(), is(entity.getConvertedLatitude()));
    }

    @Test
    public void will_map_dms_converted_from_longitude_from_entity_to_domain() {
        Mockito.when(latLongToDmsConversionService.getDegrees()).thenReturn(new Degrees(40));
        Mockito.when(latLongToDmsConversionService.getMinutes()).thenReturn(new Minutes(50));
        Mockito.when(latLongToDmsConversionService.getSeconds()).thenReturn(new Seconds(60));
        Mockito.when(latLongToDmsConversionService.getHemisphere()).thenReturn(new Hemisphere("E"));

        domain = domainToEntityAssembler.toDomain(entity);

        assertThat(domain.getConvertedLongitude(), is(entity.getConvertedLongitude()));
    }
}
