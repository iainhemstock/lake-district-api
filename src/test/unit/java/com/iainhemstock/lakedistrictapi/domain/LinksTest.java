package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

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
}
