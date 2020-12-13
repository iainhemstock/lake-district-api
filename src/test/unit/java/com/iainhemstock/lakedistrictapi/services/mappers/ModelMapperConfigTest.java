package com.iainhemstock.lakedistrictapi.services.mappers;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.dtos.FellDetailedDTO;
import com.iainhemstock.lakedistrictapi.dtos.RegionDTO;
import com.iainhemstock.lakedistrictapi.entities.*;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFell;
import com.iainhemstock.lakedistrictapi.services.converters.LatLongToDmsConverter;
import com.iainhemstock.lakedistrictapi.services.converters.MeterToFootConverter;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class ModelMapperConfigTest {

    private FellDetailedDTO dto;
    private ModelMapper mapper;
    private HelvellynFell helvellyn;

    @Before
    public void setUp() {
        mapper = new ModelMapper();
        Converter<Integer, String> meterToFeetConverter = new AbstractConverter<>() {
            @Override
            protected String convert(final Integer integer) {
                MeterToFootConverter m2fConverter = new MeterToFootConverter();
                return String.valueOf(m2fConverter.convertRoundedToNearestInteger(integer));
            }
        };
        Converter<Set<OsMap>, Set<String>> osMapConverter = new AbstractConverter<>() {
            @Override
            protected Set<String> convert(final Set<OsMap> osMaps) {
                return osMaps.stream()
                    .map(OsMap::getName)
                    .collect(Collectors.toSet());
            }
        };
        Converter<Double, Map<String, String>> dmsLatitudeConverter = new AbstractConverter<>() {
            @Override
            protected Map<String, String> convert(final Double latitude) {
                LatLongToDmsConverter c = new LatLongToDmsConverter();
                c.convert(latitude, LatLongToDmsConverter.CoordType.LATITUDE);
                return Map.of("degrees", String.valueOf(c.getDegrees()),
                    "minutes", String.valueOf(c.getMinutes()),
                    "seconds", String.valueOf(c.getSeconds()),
                    "hemisphere", String.valueOf(c.getHemisphere()));
            }
        };
        Converter<Double, Map<String, String>> dmsLongitudeConverter = new AbstractConverter<>() {
            @Override
            protected Map<String, String> convert(final Double latitude) {
                LatLongToDmsConverter c = new LatLongToDmsConverter();
                c.convert(latitude, LatLongToDmsConverter.CoordType.LONGITUDE);
                return Map.of("degrees", String.valueOf(c.getDegrees()),
                    "minutes", String.valueOf(c.getMinutes()),
                    "seconds", String.valueOf(c.getSeconds()),
                    "hemisphere", String.valueOf(c.getHemisphere()));
            }
        };
        Converter<Set<Classification>, Set<String>> classificationsConverter = new AbstractConverter<>() {
            @Override
            protected Set<String> convert(final Set<Classification> classifications) {
                return classifications.stream()
                    .map(Classification::getName)
                    .collect(Collectors.toSet());
            }
        };
        Converter<String, String> hrefConverter = new AbstractConverter<>() {
            @Override
            protected String convert(final String osMapRef) {
                return String.format("%s/fells/%s", TestApiProperties.API_BASE_URL, osMapRef);
            }
        };
        Converter<ParentFell, String> parentHrefConverter = new AbstractConverter<>() {
            @Override
            protected String convert(final ParentFell parent) {
                return String.format("%s/fells/%s", TestApiProperties.API_BASE_URL, parent.getOsMapRef());
            }
        };
        mapper.createTypeMap(Fell.class, FellDetailedDTO.class)
            .addMappings(mapper -> {
                mapper.using(meterToFeetConverter).map(Fell::getHeightMeters, (dest, v) -> dest.getHeight().setFeet((String) v));
                mapper.using(meterToFeetConverter).map(Fell::getProminenceMeters, (dest, v) -> dest.getProminence().setFeet((String) v));
                mapper.map(Fell::getLatitude, (dest, v) -> dest.getLocation().setLatitude((String) v));
                mapper.map(Fell::getLongitude, (dest, v) -> dest.getLocation().setLongitude((String) v));
                mapper.map(helvellynFell -> helvellynFell.getRegion().getName(), (dest, v) -> dest.getLocation().setRegion((String) v));
                mapper.map(Fell::getOsMapRef, (dest, v) -> dest.getLocation().setOs_map_ref((String) v));
                mapper.using(osMapConverter).map(Fell::getOsMaps, (dest, v) -> dest.getLocation().setOs_maps((Set) v));
                mapper.using(dmsLatitudeConverter).map(Fell::getLatitude, (dest, v) -> dest.getLocation().setDmsLatitude((Map) v));
                mapper.using(dmsLongitudeConverter).map(Fell::getLongitude, (dest, v) -> dest.getLocation().setDmsLongitude((Map) v));
                mapper.using(classificationsConverter).map(Fell::getClassifications, (dest, v) -> dest.setClassifications((Set) v));
                mapper.using(hrefConverter).map(Fell::getOsMapRef, (dest, v) -> dest.getLinks().getSelf().setHref((String) v));
                mapper.using(parentHrefConverter).map(Fell::getParentPeak, (dest, v) -> dest.getLinks().getParent().setHref((String) v));
            }).include(HelvellynFell.class, FellDetailedDTO.class);
        helvellyn = new HelvellynFell();
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
        Map<String, String> expected = Map.of("degrees", "54", "minutes", "31", "seconds", "38", "hemisphere", "N");
        Map<String, String> actual = dto.getLocation().getDmsLatitude();
        assertThat(actual, is(expected));
    }

    @Test
    public void will_map_dms_converted_from_longitude() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        Map<String, String> expected = Map.of("degrees", "3", "minutes", "0", "seconds", "58", "hemisphere", "W");
        Map<String, String> actual = dto.getLocation().getDmsLongitude();
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
        assertThat(dto.getLinks().getSelf().getHref(),
            is("http://localhost:8080/api/v1/fells/NY342151"));
    }

    @Test
    public void will_map_parent_href() {
        dto = mapper.map(helvellyn, FellDetailedDTO.class);
        assertThat(dto.getLinks().getParent().getHref(),
            is("http://localhost:8080/api/v1/fells/NY215072"));
    }

    @Test
    public void map() {
        Converter<String, String> toUppercase = new AbstractConverter<>() {
            @Override
            protected String convert(final String s) {
                return String.format("%s %s", s.toUpperCase(Locale.ROOT), "REGION");
            }
        };
        Converter<String, String> toAbbr = new AbstractConverter<>() {
            @Override
            protected String convert(final String s) {
                return s.substring(0, 7);
            }
        };
        PropertyMap<Region, RegionDTO> mapping = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setUuid(source.getId());
                using(toUppercase).map().setLocation(source.getName());
                using(toAbbr).map().setAbbr(source.getName());
                map().setCategory(18);
                map(source.getName()).getHeight().setFeet(null);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(mapping);
        RegionDTO regionDTO = mapper.map(new Region(5, "Central Lake District"), RegionDTO.class);
        assertThat(regionDTO.getUuid(), is(5));
        assertThat(regionDTO.getLocation(), is("CENTRAL LAKE DISTRICT REGION"));
        assertThat(regionDTO.getAbbr(), is("Central"));
        assertThat(regionDTO.getCategory(), is(18));
        assertThat(regionDTO.getHeight().getFeet(), is("Central Lake District"));
        System.out.println(regionDTO);
    }
}
