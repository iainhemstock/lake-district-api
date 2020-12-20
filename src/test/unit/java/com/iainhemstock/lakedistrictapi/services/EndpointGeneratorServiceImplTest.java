package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.domain.Link;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.EndpointGeneratorService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(JUnitParamsRunner.class)
public class EndpointGeneratorServiceImplTest {

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    private TestApiProperties apiProperties;
    private EndpointGeneratorService endpointGeneratorService;

    @Before
    public void setUp() {
        apiProperties = new TestApiProperties();
        endpointGeneratorService = new EndpointGeneratorServiceImpl(apiProperties);
    }

    @Test
    public void will_generate_endpoint_for_resource_with_id() {
        assertThat(endpointGeneratorService.generateForResourceWithId("fells", "NY123456"),
            is(equalTo(new Link(apiProperties.getBaseUrl() + "/fells/NY123456"))));
    }

    @Test
    public void will_throw_when_attempting_to_generate_endpoint_for_null_resource() {
        try {
            endpointGeneratorService.generateForResourceWithId(null, "NY123456");
            fail("Expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Argument 'resource' cannot be null")));
        }
    }

    @Test
    @Parameters({ "" })
    public void will_throw_when_attempting_to_generate_endpoint_with_invalid_resource_id(final String invalidResourceId) {
        try {
            endpointGeneratorService.generateForResourceWithId("resource", invalidResourceId);
            fail("Expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("Argument 'resourceId' cannot be blank")));
        }
    }
}
