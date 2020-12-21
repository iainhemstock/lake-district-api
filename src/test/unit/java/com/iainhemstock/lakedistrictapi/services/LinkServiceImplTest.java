package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.domain.LinkRel;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LinkService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class LinkServiceImplTest {

    private ApiProperties apiProperties;
    private LinkService linkService;

    @Before
    public void setUp() {
        apiProperties = new TestApiProperties();
        linkService = new LinkServiceImpl(apiProperties);
    }

    @Test
    public void will_generate_link_for_resource_with_id() {
        assertThat(linkService.buildForResourceWithIdAndRel("fells", "NY123456", LinkRel.SELF),
            is(equalTo(new Link(LinkRel.SELF, apiProperties.getBaseUrl() + "/fells/NY123456"))));
    }

    @Test
    public void will_throw_when_attempting_to_generate_link_for_null_resource() {
        try {
            linkService.buildForResourceWithIdAndRel(null, "NY123456", LinkRel.SELF);
            fail("Expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Argument 'resource' cannot be null")));
        }
    }

    @Test
    public void will_throw_when_attempting_to_generate_link_with_invalid_resource_id() {
        try {
            linkService.buildForResourceWithIdAndRel("resource", "", LinkRel.SELF);
            fail("Expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("Argument 'resourceId' cannot be blank")));
        }
    }
}
