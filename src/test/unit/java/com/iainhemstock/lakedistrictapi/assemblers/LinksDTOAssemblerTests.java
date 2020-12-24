package com.iainhemstock.lakedistrictapi.assemblers;

import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.dtos.LinksDTO;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LinksDTOAssembler;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LinksDTOAssemblerTests {

    @Test
    public void will_map_links_to_dto() {
        LinksDTOAssembler linksDTOAssembler = new LinksDTOAssemblerImpl();
        Links links = new Links(
            new Link(LinkRel.SELF, "http://localhost:8080/api/v1/fells/NY123456"),
            new Link(LinkRel.PARENT, "http://localhost:8080/api/v1/fells/NY987654"));

        LinksDTO linksDTO = linksDTOAssembler.toDTO(links);

        assertThat(linksDTO.getLinks(),
            is(Map.of(
                "self", "http://localhost:8080/api/v1/fells/NY123456",
                "parent", "http://localhost:8080/api/v1/fells/NY987654")));
    }

    @Test
    public void given_zero_links_when_mapping_then_links_map_is_empty() {
        LinksDTOAssembler linksDTOAssembler = new LinksDTOAssemblerImpl();
        LinksDTO linksDTO = linksDTOAssembler.toDTO(new Links());
        assertTrue(linksDTO.getLinks().isEmpty());
    }
}
