package com.iainhemstock.lakedistrictapi.entities;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.MeterToFeetConversionService;
import com.iainhemstock.lakedistrictapi.services.converters.LatLongToDmsConversionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class FellEntityTests {

    @Mock private LatLongToDmsConversionService latLongToDmsConversionService;
    @Mock private MeterToFeetConversionService meterToFeetConversionService;

    @Test
    public void will_return_DMS_converted_from_latitude() {
        Mockito.when(latLongToDmsConversionService.getDegrees()).thenReturn(new Degrees(10));
        Mockito.when(latLongToDmsConversionService.getMinutes()).thenReturn(new Minutes(20));
        Mockito.when(latLongToDmsConversionService.getSeconds()).thenReturn(new Seconds(30));
        Mockito.when(latLongToDmsConversionService.getHemisphere()).thenReturn(new Hemisphere("N"));

        FellEntity helvellynFellEntity = new HelvellynFellEntity();
        helvellynFellEntity.setLatLongToDmsConversionService(latLongToDmsConversionService);
        assertThat(helvellynFellEntity.getConvertedLatitude(),
            is(new DMS(new Degrees(10), new Minutes(20), new Seconds(30), new Hemisphere("N"))));
    }

    @Test
    public void will_return_DMS_converted_from_longitude() {
        Mockito.when(latLongToDmsConversionService.getDegrees()).thenReturn(new Degrees(40));
        Mockito.when(latLongToDmsConversionService.getMinutes()).thenReturn(new Minutes(50));
        Mockito.when(latLongToDmsConversionService.getSeconds()).thenReturn(new Seconds(60));
        Mockito.when(latLongToDmsConversionService.getHemisphere()).thenReturn(new Hemisphere("E"));

        FellEntity helvellynFellEntity = new HelvellynFellEntity();
        helvellynFellEntity.setLatLongToDmsConversionService(latLongToDmsConversionService);
        assertThat(helvellynFellEntity.getConvertedLongitude(),
            is(new DMS(new Degrees(40), new Minutes(50), new Seconds(60), new Hemisphere("E"))));
    }

    @Test
    public void will_return_height_in_feet() {
        FellEntity helvellynFellEntity = new HelvellynFellEntity();
        helvellynFellEntity.setMeterToFeetConversionService(meterToFeetConversionService);
        Mockito.when(meterToFeetConversionService.convertRoundedToNearestInteger(helvellynFellEntity.getHeightMeters()))
            .thenReturn(new Feet(3117));
        assertThat(helvellynFellEntity.getHeightFeet(),
            is(new Feet(3117)));
    }

    @Test
    public void will_return_prominence_in_feet() {
        FellEntity helvellynFellEntity = new HelvellynFellEntity();
        helvellynFellEntity.setMeterToFeetConversionService(meterToFeetConversionService);
        Mockito.when(meterToFeetConversionService.convertRoundedToNearestInteger(helvellynFellEntity.getProminenceMeters()))
            .thenReturn(new Feet(2336));
        assertThat(helvellynFellEntity.getProminenceFeet(),
            is(new Feet(2336)));
    }
}