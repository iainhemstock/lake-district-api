package com.iainhemstock.lakedistrictapi.assemblers;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.config.modelmapper.ModelMapperConfig;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.dtos.DetailedFellDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.entities.classifications.MarilynClassification;
import com.iainhemstock.lakedistrictapi.entities.fells.ScafellPikeFellEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL5ExplorerOsMap;
import com.iainhemstock.lakedistrictapi.entities.regions.EasternRegion;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.DetailedFellDTOAssembler;
import com.iainhemstock.lakedistrictapi.services.LinkServiceImpl;
import com.iainhemstock.lakedistrictapi.services.converters.LatLongToDmsConversionServiceImpl;
import com.iainhemstock.lakedistrictapi.services.converters.MeterToFeetConversionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class ModelMapperDetailedFellDTOAssemblerTest {

    private DetailedFellDTOAssembler detailedFellDTOAssembler;
    private DetailedFellDTO dto;
    private ModelMapper mapper;
    private DetailedFell detailedFell;

    @Before
    public void setUp() {
        ModelMapperConfig config = new ModelMapperConfig();
        mapper = config.modelMapper();
        FellEntity helvellyn = new FellEntity("NY342151",
            new FellName("Helvellyn"),
            new Meters(950),
            new Meters(712),
            new Latitude(54.527232),
            new Longitude(-3.016054),
            new EasternRegion(),
            new ParentFell(new ScafellPikeFellEntity().getOsMapRef()),
            new HashSet<>(Set.of(
                new Landranger90OsMap(),
                new OL5ExplorerOsMap())),
            new HashSet<>(Set.of(new MarilynClassification())));
        detailedFell = new DetailedFell(
            helvellyn,
            new MeterToFeetConversionServiceImpl(),
            new LatLongToDmsConversionServiceImpl(),
            new LinkServiceImpl(new TestApiProperties()));

        detailedFellDTOAssembler = new ModelMapperDetailedFellDTOAssembler(mapper);
    }

    @Test
    public void will_map_fell_name() {
        dto = detailedFellDTOAssembler.toDTO(detailedFell);
        assertThat(dto.getName(),
            is("Helvellyn"));
    }

    @Test
    public void will_map_height_in_meters() {
        dto = detailedFellDTOAssembler.toDTO(detailedFell);
        assertThat(dto.getHeightMeters(),
            is("950"));
    }

    @Test
    public void will_map_height_in_feet() {
        dto = detailedFellDTOAssembler.toDTO(detailedFell);
        assertThat(dto.getHeightFeet(),
            is("3117"));
    }

    @Test
    public void will_map_prominence_in_meters() {
        dto = detailedFellDTOAssembler.toDTO(detailedFell);
        assertThat(dto.getProminenceMeters(),
            is("712"));
    }

    @Test
    public void will_map_prominence_in_feet() {
        dto = detailedFellDTOAssembler.toDTO(detailedFell);
        assertThat(dto.getProminenceFeet(),
            is("2336"));
    }

    @Test
    public void will_map_os_map_ref() {
        dto = detailedFellDTOAssembler.toDTO(detailedFell);
        assertThat(dto.getOsMapRef(),
            is("NY342151"));
    }

    @Test
    public void will_map_latitude() {
        dto = detailedFellDTOAssembler.toDTO(detailedFell);
        assertThat(dto.getLatitude(),
            is("54.527232"));
    }

    @Test
    public void will_map_longitude() {
        dto = detailedFellDTOAssembler.toDTO(detailedFell);
        assertThat(dto.getLongitude(),
            is("-3.016054"));
    }

    @Test
    public void will_map_region() {
        dto = detailedFellDTOAssembler.toDTO(detailedFell);
        assertThat(dto.getRegion(),
            is("Eastern Lake District"));
    }

    @Test
    public void will_map_os_maps() {
        dto = detailedFellDTOAssembler.toDTO(detailedFell);
        Set<String> expected = Set.of("OS Landranger 90", "OS Explorer OL5");
        Set<String> actual = dto.getOsMapNames();
        assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
    }

    @Test
    public void will_map_dms_converted_from_latitude() {
        dto = detailedFellDTOAssembler.toDTO(detailedFell);
        Map<String, String> expected = Map.of("degrees", "54", "minutes", "31","seconds", "38", "hemisphere", "N");
        Map<String, String> actual = dto.getLatitudeAsDms();
        assertThat(actual, is(expected));
    }

    @Test
    public void will_map_dms_converted_from_longitude() {
        dto = detailedFellDTOAssembler.toDTO(detailedFell);
        Map<String, String> expected = Map.of("degrees", "3","minutes", "0", "seconds", "58", "hemisphere", "W");
        Map<String, String> actual = dto.getLongitudeAsDms();
        assertThat(actual, is(expected));
    }

    @Test
    public void will_map_classifications() {
        dto = detailedFellDTOAssembler.toDTO(detailedFell);
        Set<String> expected = Set.of("Marilyn");
        Set<String> actual = dto.getClassificationNames();
        assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
    }

}
