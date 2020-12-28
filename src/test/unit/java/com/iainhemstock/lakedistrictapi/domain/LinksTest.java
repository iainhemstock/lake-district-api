package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LinksTest {
    private ApiProperties apiProperties;

    @Before
    public void setUp() {
        apiProperties = new TestApiProperties();
    }

    @Test
    public void will_perform_some_action_on_each_link() {
        Link expectedLink1 = new Link(LinkRel.SELF, String.format("%s/fells/NY123456", apiProperties.getBaseUrl()));
        Link expectedLink2 = new Link(LinkRel.PARENT, String.format("%s/fells/NY987654", apiProperties.getBaseUrl()));

        List<Link> linksList = new ArrayList<>();
        Links links = new Links(expectedLink1, expectedLink2);
        links.forEach(link -> linksList.add(link));

        assertThat(linksList, is(List.of(expectedLink1, expectedLink2)));
    }

    @Test
    public void when_adding_links_then_they_are_saved() {
        Link expectedLink1 = new Link(LinkRel.SELF, String.format("%s/fells/NY123456", apiProperties.getBaseUrl()));
        Link expectedLink2 = new Link(LinkRel.PARENT, String.format("%s/fells/NY987654", apiProperties.getBaseUrl()));

        Links actualLinks = new Links();
        actualLinks.add(expectedLink1);
        actualLinks.add(expectedLink2);
        Links expectedLinks = new Links(expectedLink1, expectedLink2);

        assertThat(actualLinks, is(expectedLinks));
    }

    @Test
    public void given_rel_type_link_already_exists_when_adding_same_rel_type_again_then_old_one_is_replaced_with_new_one() {
        Link selfLink = new Link(LinkRel.SELF, String.format("%s/fells/NY123456", apiProperties.getBaseUrl()));
        Link otherSelfLinkWithDifferentHref = new Link(LinkRel.SELF, String.format("%s/fells/NY987654", apiProperties.getBaseUrl()));

        Links actualLinks = new Links();
        actualLinks.add(selfLink);
        actualLinks.add(otherSelfLinkWithDifferentHref);

        Links expectedLinks = new Links(otherSelfLinkWithDifferentHref);

        assertThat(actualLinks, is(equalTo(expectedLinks)));
    }
}
