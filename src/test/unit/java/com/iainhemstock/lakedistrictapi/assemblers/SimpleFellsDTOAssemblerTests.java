package com.iainhemstock.lakedistrictapi.assemblers;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.assemblers.SimpleFellsDTOAssemblerImpl;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.SimpleFellDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.SimpleFellsDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.entities.fells.GreatGableFell;
import com.iainhemstock.lakedistrictapi.entities.fells.HelvellynFell;
import com.iainhemstock.lakedistrictapi.application_interfaces.SimpleFellsDTOAssembler;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

public class SimpleFellsDTOAssemblerTests {

    private SimpleFellsDTO dto;
    private TestApiProperties apiProperties;
    private SimpleFellsDTOAssembler mapper;

    @Before
    public void setUp() {
        apiProperties = new TestApiProperties();
        mapper = new SimpleFellsDTOAssemblerImpl(apiProperties);
        Set<Fell> fells = new LinkedHashSet<>(List.of(new GreatGableFell(), new HelvellynFell()));
        dto = mapper.toDTO(fells);
    }

    @Test
    public void will_map_fell_name() {
        Iterator<SimpleFellDTO> iterator = dto.getFells().iterator();
        assertThat(iterator.next().getName(), is("Great Gable"));
        assertThat(iterator.next().getName(), is("Helvellyn"));
    }

    @Test
    public void will_map_fell_region() {
        Iterator<SimpleFellDTO> iterator = dto.getFells().iterator();
        assertThat(iterator.next().getRegion(), is("Central Lake District"));
        assertThat(iterator.next().getRegion(), is("Eastern Lake District"));
    }

    @Test
    public void will_map_self_href() {
        Iterator<SimpleFellDTO> iterator = dto.getFells().iterator();
        assertThat(iterator.next().getLinks().getLinks().get("self"),
            is(String.format("%s/fells/NY211104", apiProperties.getBaseUrl())));
        assertThat(iterator.next().getLinks().getLinks().get("self"),
            is(String.format("%s/fells/NY342151", apiProperties.getBaseUrl())));
    }

    @Test
    public void will_throw_when_mapping_null_set_of_fells() {
        try {
            mapper.toDTO(null);
            fail("Expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is("Set of [Fell] cannot be null"));
        }
    }

    @Test
    public void will_throw_when_mapping_set_of_null_fells() {
        try {
            mapper.toDTO(new LinkedHashSet<>(Arrays.asList(null, null)));
            fail("Expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is("Cannot map null Fell"));
        }
    }
}
