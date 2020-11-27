package com.iainhemstock.lakedistrictapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.iainhemstock.lakedistrictapi.config.TestApiConfiguration;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = TestApiConfiguration.class)
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FindFellByIdFeatureTest extends BaseFeatureTest {

    @Autowired private MockMvc mockMvc;
    private ResultActions result;

    @Given("^an endpoint (.*)$")
    public void anEndpoint(final String endpoint) {
        this.setEndpointUnderTest(endpoint);
    }

    @When("^requesting a fell with id ([0-9]*)$")
    public void whenRequestingFellWithId(final int fellId) throws Exception {
        result = mockMvc.perform(get(this.getEndpointUnderTest(), fellId));
    }

    @And("^the response content type will be (.*)$")
    public void theContentTypeWillBe(final String expectedContentType) throws Exception {
        result.andExpect(content().contentType(expectedContentType));
    }

    @Then("^the response status code will be ([0-9]{3})$")
    public void theResponseWillContainStatusCode(final int expectedStatusCode) throws Exception {
        result.andExpect(status().is(expectedStatusCode));
    }

    @Then("^the response headers will confirm only GET requests are allowed on this endpoint$")
    public void theResponseHeadersWillContainAllowHeaderSetToGet() throws Exception {
        result.andExpect(header().string("Allow", "GET"));
    }

    @And("^the response body will conform to the schema in (.*)$")
    public void theResponseWillConformToTheJsonSchema(final String schemaFilename) throws IOException {
        JsonSchema schema = this.getJsonSchemaFromClasspath(schemaFilename);
        String responseBodyString = result.andReturn().getResponse().getContentAsString();
        JsonNode responseBodyJsonNode = this.getJsonNodeFromStringContent(responseBodyString);

        Set<ValidationMessage> errors = schema.validate(responseBodyJsonNode);
        if (errors.size() > 0)
            fail("Does not conform to json schema: " + errors.toString());
    }

    @And("^the response body will contain the fell name (.*)$")
    public void theReponseBodyWillContainTheFellName(final String expectedFellName) throws Exception {
        result.andExpect(jsonPath("$.name", is(expectedFellName)));
    }

    @And("^the response body will contain the region (.*)$")
    public void theResponseBodyWillContainTheRegion(final String expectedRegion) throws Exception {
        result.andExpect(jsonPath("$.location.region", is(expectedRegion)));
    }

    @And("^the response body will contain the latitude (.*)$")
    public void theResponseBodyWillContainTheLatitude(final String expectedLatitude) throws Exception {
        result.andExpect(jsonPath("$.location.latitude", is(expectedLatitude)));
    }

    @And("^the response body will contain the longitude (.*)$")
    public void theResponseBodyWillContainTheLongitude(final String expectedLongitude) throws Exception {
        result.andExpect(jsonPath("$.location.longitude", is(expectedLongitude)));
    }

    @And("^the response body will contain the os map reference (.*)$")
    public void theResponseBodyWillContainTheOsMapRef(final String expectedOsMapRef) throws Exception {
        result.andExpect(jsonPath("$.location.os_map_ref", is(expectedOsMapRef)));
    }

    @And("^the response body will contain the url to itself (.*)$")
    public void theResponseBodyWillContainTheUrl(final String expectedUrl) throws Exception {
        result.andExpect(jsonPath("$.url", is(expectedUrl)));
    }

    @And("^the response body will contain the parent peak url (.*)$")
    public void theResponseBodyWillContainTheParentPeakUrl(final String expectedParentPeakUrl) throws Exception {
        result.andExpect(jsonPath("$.parent_peak", is(expectedParentPeakUrl)));
    }

    @And("^the response body will contain the height in feet (.*)$")
    public void theResponseBodyWillContainTheFellHeightInFeet(final String expectedHeightInFeet) throws Exception {
        result.andExpect(jsonPath("$.height.feet", is(expectedHeightInFeet)));
    }

    @And("^the response body will contain the height in meters (.*)$")
    public void theResponseBodyWillContainTheFellHeightInMeters(final String expectedHeightInMeters) throws Exception {
        result.andExpect(jsonPath("$.height.meters", is(expectedHeightInMeters)));
    }

    @And("^the response body will contain the prominence in feet (.*)$")
    public void theResponseBodyWillContainTheFellProminenceInFeet(final String expectedProminenceInFeet) throws Exception {
        result.andExpect(jsonPath("$.prominence.feet", is(expectedProminenceInFeet)));
    }

    @And("^the response body will contain the prominence in meters (.*)$")
    public void theResponseBodyWillContainTheFellProminenceInMeters(final String expectedProminenceInMeters) throws Exception {
        result.andExpect(jsonPath("$.prominence.meters", is(expectedProminenceInMeters)));
    }

    @And("^the response body will contain the following classifications$")
    public void theResponseBodyWillContainTheFollowingClassifications(final List<String> expectedClassifications) throws Exception {
        result.andExpect(jsonPath("$.classifications", containsInAnyOrder(expectedClassifications.toArray())));
    }

    @And("^the response body will contain the following maps that this fell appears in$")
    public void theResponseBodyWillContainTheFollowingMaps(final List<String> expectedMaps) throws Exception {
        result.andExpect(jsonPath("$.location.os_maps", containsInAnyOrder(expectedMaps.toArray())));
    }

    @And("^the response body will contain the following dms coordinates equivalent to the latitude$")
    public void theResponseBodyWillContainTheFollowingNorthernDmsCoordinates(final Map<String, String> expectedDms) throws Exception {
        result.andExpect(jsonPath("$.location.dms[0].degrees", is(expectedDms.get("degrees"))))
            .andExpect(jsonPath("$.location.dms[0].minutes", is(expectedDms.get("minutes"))))
            .andExpect(jsonPath("$.location.dms[0].seconds", is(expectedDms.get("seconds"))))
            .andExpect(jsonPath("$.location.dms[0].hemisphere", is(expectedDms.get("hemisphere"))));
    }

    @And("^the response body will contain the following dms coordinates equivalent to the longitude$")
    public void theResponseBodyWillContainTheFollowingWesternDmsCoordinates(final Map<String, String> expectedDms) throws Exception {
        result.andExpect(jsonPath("$.location.dms[1].degrees", is(expectedDms.get("degrees"))))
            .andExpect(jsonPath("$.location.dms[1].minutes", is(expectedDms.get("minutes"))))
            .andExpect(jsonPath("$.location.dms[1].seconds", is(expectedDms.get("seconds"))))
            .andExpect(jsonPath("$.location.dms[1].hemisphere", is(expectedDms.get("hemisphere"))));
    }

    @When("^sending unsupported (.*) request with fell id ([0-9]*) to endpoint$")
    public void usingHttpMethodWithEndpoint(final String unsupportedHttpMethod, final int fellId) throws Exception {
        if ("POST".equals(unsupportedHttpMethod))
            result = mockMvc.perform(post(this.getEndpointUnderTest(), fellId));
        else if ("PUT".equals(unsupportedHttpMethod))
            result = mockMvc.perform(put(this.getEndpointUnderTest(), fellId));
        else if ("PATCH".equals(unsupportedHttpMethod))
            result = mockMvc.perform(patch(this.getEndpointUnderTest(), fellId));
        else if ("DELETE".equals(unsupportedHttpMethod))
            result = mockMvc.perform(delete(this.getEndpointUnderTest(), fellId));
        else {
            fail(String.format("Unrecognised http method submitted to test [%s]", unsupportedHttpMethod));
        }
    }

    @And("^the response body will contain the status code ([0-9]{3})$")
    public void theResponseBodyWillContainStatusCode(final int expectedStatusCode) throws Exception {
        result.andExpect(jsonPath("$.status", is(String.valueOf(expectedStatusCode))));
    }

    @And("^the response body will contain the message (.*)$")
    public void theResponseBodyWillContainMessage(final String expectedMessage) throws Exception {
        result.andExpect(jsonPath("$.message", is(expectedMessage)));
    }

    @And("^the response body will contain the path (.*)$")
    public void theResponseBodyWillContainThePath(final String expectedPath) throws Exception {
        result.andExpect(jsonPath("$.path", is(expectedPath)));
    }

    @And("^the response body will contain the timestamp (.*)$")
    public void theResponseBodyWillContainTheTimestamp(final String expectedTimestamp) throws Exception {
        result.andExpect(jsonPath("$.timestamp", is(expectedTimestamp)));
    }
}
