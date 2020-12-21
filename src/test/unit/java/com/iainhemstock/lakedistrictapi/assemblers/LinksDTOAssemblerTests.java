package com.iainhemstock.lakedistrictapi.assemblers;

import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.dtos.LinksDTO;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LinksDTOAssembler;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
}
