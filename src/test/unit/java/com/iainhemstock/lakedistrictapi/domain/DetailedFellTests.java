package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFellEntity;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LinkService;
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
public class DetailedFellTests {

    @Mock private MeterToFeetConversionService meterToFeetConversionService;
    @Mock private LatLongToDmsConversionServiceImpl latLongToDmsConverter;
    @Mock private LinkService linkService;

    private final FellEntity helvellyn = new HelvellynFellEntity();

    @Test
    public void get_fell_name() {
        DetailedFell detailedFell = new DetailedFell(helvellyn, meterToFeetConversionService, latLongToDmsConverter, linkService);
        assertThat(detailedFell.getFellName(),
            is(new FellName("Helvellyn")));
    }

    @Test
    public void get_height_in_meters() {
        DetailedFell detailedFell = new DetailedFell(helvellyn, meterToFeetConversionService, latLongToDmsConverter, linkService);
        assertThat(detailedFell.getHeightMeters(),
            is((new Meters(950))));
    }

    @Test
    public void get_height_in_feet() {
        Mockito.when(meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(950)))
            .thenReturn(new Feet(3117));
        DetailedFell detailedFell = new DetailedFell(helvellyn, meterToFeetConversionService, latLongToDmsConverter, linkService);
        assertThat(detailedFell.getHeightFeet(),
            is((new Feet(3117))));
    }

    @Test
    public void get_prominence_meters() {
        DetailedFell detailedFell = new DetailedFell(helvellyn, meterToFeetConversionService, latLongToDmsConverter, linkService);
        assertThat(detailedFell.getProminenceMeters(),
            is(new Meters(712)));
    }

    @Test
    public void get_prominence_feet() {
        Mockito.when(meterToFeetConversionService.convertRoundedToNearestInteger(new Meters(helvellyn.getProminenceMeters().toInt())))
            .thenReturn(new Feet(2335));
        DetailedFell detailedFell = new DetailedFell(helvellyn, meterToFeetConversionService, latLongToDmsConverter, linkService);
        assertThat(detailedFell.getProminenceFeet(),
            is(new Feet(2335)));
    }

    @Test
    public void get_latitude() {
        DetailedFell detailedFell = new DetailedFell(helvellyn, meterToFeetConversionService, latLongToDmsConverter, linkService);
        assertThat(detailedFell.getLatitude(),
            is(new Latitude(54.527232)));
    }

    @Test
    public void get_longitude() {
        DetailedFell detailedFell = new DetailedFell(helvellyn, meterToFeetConversionService, latLongToDmsConverter, linkService);
        assertThat(detailedFell.getLongitude(),
            is(new Longitude(-3.016054)));
    }

    @Test
    public void get_region_name() {
        DetailedFell detailedFell = new DetailedFell(helvellyn, meterToFeetConversionService, latLongToDmsConverter, linkService);
        assertThat(detailedFell.getRegionName(),
            is(new RegionName("Eastern Lake District")));
    }

    @Test
    public void get_os_map_ref() {
        DetailedFell detailedFell = new DetailedFell(helvellyn, meterToFeetConversionService, latLongToDmsConverter, linkService);
        assertThat(detailedFell.getOsMapRef(),
            is(new OsMapRef("NY342151")));
    }

    @Test
    public void get_dms_converted_from_latitude() {
        Mockito.when(latLongToDmsConverter.getDegrees()).thenReturn(new Degrees(1));
        Mockito.when(latLongToDmsConverter.getMinutes()).thenReturn(new Minutes(2));
        Mockito.when(latLongToDmsConverter.getSeconds()).thenReturn(new Seconds(3));
        Mockito.when(latLongToDmsConverter.getHemisphere()).thenReturn(new Hemisphere("N"));
        DetailedFell detailedFell = new DetailedFell(helvellyn, meterToFeetConversionService, latLongToDmsConverter, linkService);
        assertThat(detailedFell.getConvertedLatitude(),
            is(new DMS(new Degrees(1), new Minutes(2), new Seconds(3), new Hemisphere("N"))));
    }



    @Test
    public void get_dms_converted_from_longitude() {
        Mockito.when(latLongToDmsConverter.getDegrees()).thenReturn(new Degrees(4));
        Mockito.when(latLongToDmsConverter.getMinutes()).thenReturn(new Minutes(5));
        Mockito.when(latLongToDmsConverter.getSeconds()).thenReturn(new Seconds(6));
        Mockito.when(latLongToDmsConverter.getHemisphere()).thenReturn(new Hemisphere("E"));
        DetailedFell detailedFell = new DetailedFell(helvellyn, meterToFeetConversionService, latLongToDmsConverter, linkService);
        assertThat(detailedFell.getConvertedLongitude(),
            is(new DMS(new Degrees(4), new Minutes(5), new Seconds(6), new Hemisphere("E"))));
    }

    @Test
    public void get_classifications() {
        DetailedFell detailedFell = new DetailedFell(helvellyn, meterToFeetConversionService, latLongToDmsConverter, linkService);
        assertThat(detailedFell.getClassificationNames(),
            is(new ClassificationNames(new ClassificationName("Marilyn"))));
    }

    @Test
    public void get_os_maps() {
        DetailedFell detailedFell = new DetailedFell(helvellyn, meterToFeetConversionService, latLongToDmsConverter, linkService);
        assertThat(detailedFell.getOsMapNames(),
            is(new OsMapNames(new OsMapName("OS Explorer OL5"), new OsMapName("OS Landranger 90"))));
    }
}
