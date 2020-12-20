package com.iainhemstock.lakedistrictapi.config.modelmapper;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.domain.DetailedFell;
import com.iainhemstock.lakedistrictapi.dtos.DetailedFellDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.entities.classifications.MarilynClassification;
import com.iainhemstock.lakedistrictapi.entities.fells.ScafellPikeFellEntity;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL5ExplorerOsMap;
import com.iainhemstock.lakedistrictapi.entities.regions.EasternRegion;
import com.iainhemstock.lakedistrictapi.services.EndpointGeneratorServiceImpl;
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

public class ModelMapperConfigTest {

    private DetailedFellDTO dto;
    private ModelMapper mapper;
    private TestApiProperties apiProperties;
    private DetailedFell detailedFell;

    @Before
    public void setUp() {
        apiProperties = new TestApiProperties();
        ModelMapperConfig config = new ModelMapperConfig(apiProperties);
        mapper = config.modelMapper();
        FellEntity helvellyn = new FellEntity("NY342151",
            "Helvellyn",
            950,
            712,
            54.527232,
            -3.016054,
            new EasternRegion(),
            new ParentFell(new ScafellPikeFellEntity().getOsMapRef()),
            new HashSet<>(Set.of(
                new Landranger90OsMap(),
                new OL5ExplorerOsMap())),
            new HashSet<>(Set.of(new MarilynClassification())));

        detailedFell = new DetailedFell(helvellyn,
            new MeterToFeetConversionServiceImpl(),
            new LatLongToDmsConversionServiceImpl(),
            new EndpointGeneratorServiceImpl(apiProperties));
    }

    @Test
    public void will_map_fell_name() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        assertThat(dto.getName(),
            is("Helvellyn"));
    }

    @Test
    public void will_map_height_in_meters() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        assertThat(dto.getHeightMeters(),
            is("950"));
    }

    @Test
    public void will_map_height_in_feet() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        assertThat(dto.getHeightFeet(),
            is("3117"));
    }

    @Test
    public void will_map_prominence_in_meters() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        assertThat(dto.getProminenceMeters(),
            is("712"));
    }

    @Test
    public void will_map_prominence_in_feet() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        assertThat(dto.getProminenceFeet(),
            is("2336"));
    }

    @Test
    public void will_map_os_map_ref() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        assertThat(dto.getOsMapRef(),
            is("NY342151"));
    }

    @Test
    public void will_map_latitude() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        assertThat(dto.getLatitude(),
            is("54.527232"));
    }

    @Test
    public void will_map_longitude() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        assertThat(dto.getLongitude(),
            is("-3.016054"));
    }

    @Test
    public void will_map_region() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        assertThat(dto.getRegion(),
            is("Eastern Lake District"));
    }

    @Test
    public void will_map_os_maps() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        Set<String> expected = Set.of("OS Landranger 90", "OS Explorer OL5");
        Set<String> actual = dto.getOsMapNames();
        assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
    }

    @Test
    public void will_map_dms_converted_from_latitude() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        Map<String, String> expected = Map.of("degrees", "54", "minutes", "31","seconds", "38", "hemisphere", "N");
        Map<String, String> actual = dto.getLatitudeAsDms();
        assertThat(actual, is(expected));
    }

    @Test
    public void will_map_dms_converted_from_longitude() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        Map<String, String> expected = Map.of("degrees", "3","minutes", "0", "seconds", "58", "hemisphere", "W");
        Map<String, String> actual = dto.getLongitudeAsDms();
        assertThat(actual, is(expected));
    }

    @Test
    public void will_map_classifications() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        Set<String> expected = Set.of("Marilyn");
        Set<String> actual = dto.getClassificationNames();
        assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
    }

    @Test
    public void will_map_self_href() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        assertThat(dto.getSelfLink(),
            is(String.format("%s/fells/NY342151", apiProperties.getBaseUrl())));
    }

    @Test
    public void will_map_parent_href() {
        dto = mapper.map(detailedFell, DetailedFellDTO.class);
        assertThat(dto.getParentLink(),
            is(String.format("%s/fells/NY215072", apiProperties.getBaseUrl())));
    }

}
