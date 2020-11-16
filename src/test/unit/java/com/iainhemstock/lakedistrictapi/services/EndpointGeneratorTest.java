package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(JUnitParamsRunner.class)
public class EndpointGeneratorTest {

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @Mock private ApiProperties apiProperties;
    private EndpointGenerator endpointGenerator;

    @Before
    public void setUp() {
        Mockito.when(apiProperties.getBaseUrl()).thenReturn(TestApiProperties.API_BASE_URL);
        endpointGenerator = new EndpointGenerator(apiProperties);
    }

    @Test
    public void will_generate_endpoint_for_resource_with_id() {
        assertThat(endpointGenerator.generateForResourceWithId("fells", 12),
            is(equalTo(TestApiProperties.API_BASE_URL + "/fells/12")));
    }

    @Test
    public void will_throw_when_attempting_to_generate_endpoint_for_null_resource() {
        try {
            endpointGenerator.generateForResourceWithId(null, 12);
            fail("Expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Argument 'resource' cannot be null")));
        }
    }

    @Test
    @Parameters({ "-1", "0" })
    public void will_throw_when_attempting_to_generate_endpoint_with_invalid_resource_id(final int invalidResourceId) {
        try {
            endpointGenerator.generateForResourceWithId("resource", invalidResourceId);
            fail("Expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("Argument 'resourceId' cannot be negative")));
        }
    }
}
