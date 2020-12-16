package com.iainhemstock.lakedistrictapi.config.modelmapper;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.dtos.DmsDTO;
import com.iainhemstock.lakedistrictapi.dtos.FellDetailedDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.entities.ParentFell;
import com.iainhemstock.lakedistrictapi.entities.classifications.MarilynClassification;
import com.iainhemstock.lakedistrictapi.entities.fells.ScafellPikeFell;
import com.iainhemstock.lakedistrictapi.entities.osmaps.Landranger90OsMap;
import com.iainhemstock.lakedistrictapi.entities.osmaps.OL5ExplorerOsMap;
import com.iainhemstock.lakedistrictapi.entities.regions.EasternRegion;
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

    private FellDetailedDTO dto;
    private ModelMapper mapper;
    private Fell helvellyn;
    private TestApiProperties apiProperties;

    @Before
    public void setUp() {
        apiProperties = new TestApiProperties();
        ModelMapperConfig config = new ModelMapperConfig(apiProperties);
        mapper = config.modelMapper();
        helvellyn = new Fell("NY342151",
            "Helvellyn",
            950,
            712,
            54.527232,
            -3.016054,
            new EasternRegion(),
            new ParentFell(new ScafellPikeFell().getOsMapRef()),
            new HashSet<>(Set.of(
                new Landranger90OsMap(),
                new OL5ExplorerOsMap())),
            new HashSet<>(Set.of(new MarilynClassification())));

    }

    @Test
    public void will_map_fell_name() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        assertThat(dto.getName(),
            is("Helvellyn"));
    }

    @Test
    public void will_map_height_in_meters() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        assertThat(dto.getHeight().getMeters(),
            is("950"));
    }

    @Test
    public void will_map_height_in_feet() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        assertThat(dto.getHeight().getFeet(),
            is("3117"));
    }

    @Test
    public void will_map_prominence_in_meters() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        assertThat(dto.getProminence().getMeters(),
            is("712"));
    }

    @Test
    public void will_map_prominence_in_feet() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        assertThat(dto.getProminence().getFeet(),
            is("2336"));
    }

    @Test
    public void will_map_os_map_ref() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        assertThat(dto.getLocation().getOs_map_ref(),
            is("NY342151"));
    }

    @Test
    public void will_map_latitude() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        assertThat(dto.getLocation().getLatitude(),
            is("54.527232"));
    }

    @Test
    public void will_map_longitude() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        assertThat(dto.getLocation().getLongitude(),
            is("-3.016054"));
    }

    @Test
    public void will_map_region() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        assertThat(dto.getLocation().getRegion(),
            is("Eastern Lake District"));
    }

    @Test
    public void will_map_os_maps() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        Set<String> expected = Set.of("OS Landranger 90", "OS Explorer OL5");
        Set<String> actual = dto.getLocation().getOs_maps();
        assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
    }

    @Test
    public void will_map_dms_converted_from_latitude() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        DmsDTO expected = new DmsDTO("54","31","38","N");
        DmsDTO actual = dto.getLocation().getDms_latitude();
        assertThat(actual, is(expected));
    }

    @Test
    public void will_map_dms_converted_from_longitude() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        DmsDTO expected = new DmsDTO("3","0", "58", "W");
        DmsDTO actual = dto.getLocation().getDms_longitude();
        assertThat(actual, is(expected));
    }

    @Test
    public void will_map_classifications() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        Set<String> expected = Set.of("Marilyn");
        Set<String> actual = dto.getClassifications();
        assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
    }

    @Test
    public void will_map_self_href() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        assertThat(dto.getLinks().getSelf().toString(),
            is(String.format("%s/fells/NY342151", apiProperties.getBaseUrl())));
    }

    @Test
    public void will_map_parent_href() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        assertThat(dto.getLinks().getParent().toString(),
            is(String.format("%s/fells/NY215072", apiProperties.getBaseUrl())));
    }

}
