package com.iainhemstock.lakedistrictapi.assemblers;

import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.DetailedFellAssembler;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.MeterToFeetConversionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class DetailedFellAssemblerTests {
    private DetailedFellAssembler detailedFellAssembler;
    private FellEntity helvellynFellEntity;
    private DetailedFell detailedFell;

    @Mock private MeterToFeetConversionService meterToFeetConversionService;
    @Mock private LatLongToDmsConversionService latLongToDmsConversionService;

    @Before
    public void setUp() {
        detailedFellAssembler = new DetailedFellAssemblerImpl(meterToFeetConversionService, latLongToDmsConversionService);
        helvellynFellEntity = new HelvellynFellEntity();
        detailedFell = detailedFellAssembler.toDetailedFell(helvellynFellEntity);
    }

    @Test
    public void will_map_fell_name() {
        assertThat(detailedFell.getFellName(),
            is(new FellName(helvellynFellEntity.getName().toString())));
    }

    @Test
    public void will_map_os_map_ref() {
        assertThat(detailedFell.getOsMapRef(),
            is(new OsMapRef(helvellynFellEntity.getOsMapRef())));
    }

    @Test
    public void will_map_height_in_meters() {
        assertThat(detailedFell.getHeightMeters(),
            is(new Meters(helvellynFellEntity.getHeightMeters().toInt())));
    }

    @Test
    public void will_map_height_in_feet() {
        Mockito.when(meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(helvellynFellEntity.getHeightMeters().toInt())))
            .thenReturn(new Feet(3117));
        detailedFell = detailedFellAssembler.toDetailedFell(helvellynFellEntity);
        assertThat(detailedFell.getHeightFeet(),
            is(new Feet(3117)));
    }

    @Test
    public void will_map_prominence_in_meters() {
        assertThat(detailedFell.getProminenceMeters(),
            is(new Meters(helvellynFellEntity.getProminenceMeters().toInt())));
    }

    @Test
    public void will_map_prominence_in_feet() {
        Mockito.when(meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(helvellynFellEntity.getProminenceMeters().toInt())))
            .thenReturn(new Feet(2336));
        detailedFell = detailedFellAssembler.toDetailedFell(helvellynFellEntity);
        assertThat(detailedFell.getProminenceFeet(),
            is(new Feet(2336)));
    }

    @Test
    public void will_map_latitude() {
        assertThat(detailedFell.getLatitude(),
            is(new Latitude(helvellynFellEntity.getLatitude().toDouble())));
    }

    @Test
    public void will_map_longitude() {
        assertThat(detailedFell.getLongitude(),
            is(new Longitude(helvellynFellEntity.getLongitude())));
    }

    @Test
    public void will_map_dms_converted_from_latitude() {
        Mockito.when(latLongToDmsConversionService.getDegrees()).thenReturn(new Degrees(10));
        Mockito.when(latLongToDmsConversionService.getMinutes()).thenReturn(new Minutes(11));
        Mockito.when(latLongToDmsConversionService.getSeconds()).thenReturn(new Seconds(12));
        Mockito.when(latLongToDmsConversionService.getHemisphere()).thenReturn(new Hemisphere("N"));
        detailedFell = detailedFellAssembler.toDetailedFell(helvellynFellEntity);
        assertThat(detailedFell.getConvertedLatitude(),
            is(new DMS(new Degrees(10), new Minutes(11), new Seconds(12), new Hemisphere("N"))));
    }

    @Test
    public void will_map_dms_converted_from_longitude() {
        Mockito.when(latLongToDmsConversionService.getDegrees()).thenReturn(new Degrees(20));
        Mockito.when(latLongToDmsConversionService.getMinutes()).thenReturn(new Minutes(21));
        Mockito.when(latLongToDmsConversionService.getSeconds()).thenReturn(new Seconds(22));
        Mockito.when(latLongToDmsConversionService.getHemisphere()).thenReturn(new Hemisphere("E"));
        detailedFell = detailedFellAssembler.toDetailedFell(helvellynFellEntity);
        assertThat(detailedFell.getConvertedLongitude(),
            is(new DMS(new Degrees(20), new Minutes(21), new Seconds(22), new Hemisphere("E"))));
    }

    @Test
    public void will_map_region() {
        assertThat(detailedFell.getRegionName(),
            is(new RegionName(helvellynFellEntity.getRegion().getName())));
    }

    @Test
    public void will_map_parent_fell() {
        assertThat(detailedFell.getParentFell(),
            is(new ParentFell(helvellynFellEntity.getParentPeak().getOsMapRef())));
    }

    @Test
    public void will_map_classifications() {
        assertThat(detailedFell.getClassificationNames(),
            is(new ClassificationNames(new ClassificationName("Marilyn"))));
    }

    @Test
    public void will_map_os_maps() {
        assertThat(detailedFell.getOsMapNames(),
            is(new OsMapNames(new OsMapName("OS Landranger 90"), new OsMapName("OS Explorer OL5"))));
    }
}
