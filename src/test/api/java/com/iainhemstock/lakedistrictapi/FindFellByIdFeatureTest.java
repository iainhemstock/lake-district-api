package com.iainhemstock.lakedistrictapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iainhemstock.lakedistrictapi.dtos.FellDTO;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FindFellByIdFeatureTest extends BaseFeatureTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    private int requestedFellId;
    private MvcResult mvcResult;

    @Given("^an endpoint (.*)$")
    public void anEndpoint(final String endpoint) {
        this.setEndpointUnderTest(endpoint);
    }

    @Given("^a fell id of ([0-9]*)$")
    public void aFellIdOf(final int fellId) {
        requestedFellId = fellId;
    }

    @When("^requesting a fell with that id$")
    public void whenRequestingFellWithThatsId() throws Exception {
        mvcResult = mockMvc.perform(get(this.getEndpointUnderTest(), requestedFellId)).andReturn();
    }

    @When("^using one of the following unsupported http methods with endpoint$")
    public void usingUnsupportedHttpMethod(List<String> expectedRequestTypes) throws Exception {
        for (String requestType : expectedRequestTypes) {
            if ("POST".equals(requestType)) mvcResult = mockMvc.perform(post(this.getEndpointUnderTest(), 0)).andReturn();
            else if ("PUT".equals(requestType)) mvcResult = mockMvc.perform(put(this.getEndpointUnderTest(), 0)).andReturn();
            else if ("PATCH".equals(requestType)) mvcResult = mockMvc.perform(patch(this.getEndpointUnderTest(), 0)).andReturn();
            else if ("DELETE".equals(requestType)) mvcResult = mockMvc.perform(delete(this.getEndpointUnderTest(), 0)).andReturn();
            else {
                fail(String.format("Unrecognised http method submitted to test [%s]", requestType));
            }
        }
    }

    @Then("^the response will return a ([0-9]{3}) status code$")
    public void theResponseWillContainStatusCode(final int expectedStatusCode) {
        assertEquals(expectedStatusCode, mvcResult.getResponse().getStatus());
    }

    @And("^the content type will be (.*)$")
    public void theContentTypeWillBe(final String expectedContentType) {
        assertEquals(expectedContentType, mvcResult.getResponse().getContentType());
    }

    @And("^the response body will conform to the schema in (.*)$")
    public void theResponseWillConformToTheJsonSchema(final String schemaFilename) throws IOException {
        JsonSchema schema = this.getJsonSchemaFromClasspath(schemaFilename);
        String responseBodyString = mvcResult.getResponse().getContentAsString();
        JsonNode responseBodyJsonNode = this.getJsonNodeFromStringContent(responseBodyString);

        Set<ValidationMessage> errors = schema.validate(responseBodyJsonNode);
        if (errors.size() > 0)
            fail("Does not conform to json schema: " + errors.toString());
    }

    @And("^the response body will contain the following values$")
    public void theResponseBodyWillContainTheFollwingValues(final Map<String, String> expectedValues) throws UnsupportedEncodingException, JsonProcessingException {
        FellDTO fellDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), FellDTO.class);

        assertEquals(expectedValues.get("name"), fellDTO.getName());
        assertEquals(expectedValues.get("height_meters"), String.valueOf(fellDTO.getHeight().getMeters()));
        assertEquals(expectedValues.get("height_feet"), String.valueOf(fellDTO.getHeight().getFeet()));
        assertEquals(expectedValues.get("prominence_meters"), String.valueOf(fellDTO.getProminence().getMeters()));
        assertEquals(expectedValues.get("prominence_feet"), String.valueOf(fellDTO.getProminence().getFeet()));
        assertEquals(expectedValues.get("region"), fellDTO.getLocation().getRegionUri());
        assertEquals(expectedValues.get("latitude"), fellDTO.getLocation().getCoords().getDecimalCoords().getLatitude());
        assertEquals(expectedValues.get("longitude"), fellDTO.getLocation().getCoords().getDecimalCoords().getLongitude());
        assertEquals(expectedValues.get("os_map_ref"), fellDTO.getLocation().getOsMapRef());
        assertEquals(expectedValues.get("url"), fellDTO.getUrl());
        assertEquals(expectedValues.get("parent_peak"), fellDTO.getParentPeakUrl());
    }

    @And("^the response body will also contain the following classifications$")
    public void theResponseBodyWillContainTheFollwingClassifications(final List<String> expectedClassifications) throws UnsupportedEncodingException, JsonProcessingException {
        FellDTO fellDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), FellDTO.class);
        assertTrue(fellDTO.getClassifications().containsAll(expectedClassifications) &&
                expectedClassifications.containsAll(fellDTO.getClassifications()));
    }

    @And("^the response body will also contain the following maps$")
    public void theResponseBodyWillContainTheFollowingMaps(final List<String> expectedMaps) throws UnsupportedEncodingException, JsonProcessingException {
        FellDTO fellDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), FellDTO.class);
        assertTrue(fellDTO.getLocation().getOsMaps().containsAll(expectedMaps) &&
            expectedMaps.containsAll(fellDTO.getLocation().getOsMaps()));
    }

    @And("^the response body will also contain the following western dms coordinates$")
    public void theResponseBodyWillContainTheFollowingWesternDmsCoordinates(final Map<String, String> expectedDms) throws UnsupportedEncodingException, JsonProcessingException {
        FellDTO fellDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), FellDTO.class);

        assertEquals(expectedDms.get("degrees"), String.valueOf(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLongitude().getDegrees()));
        assertEquals(expectedDms.get("minutes"), String.valueOf(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLongitude().getMinutes()));
        assertEquals(expectedDms.get("seconds"), String.valueOf(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLongitude().getSeconds()));
        assertEquals(expectedDms.get("hemisphere"), String.valueOf(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLongitude().getHemisphere()));
        assertEquals(expectedDms.get("formatted"), String.valueOf(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLongitude().getFormatted()));
    }

    @And("^the response body will also contain the following northern dms coordinates$")
    public void theResponseBodyWillContainTheFollowingNorthernDmsCoordinates(final Map<String, String> expectedDms) throws UnsupportedEncodingException, JsonProcessingException {
        FellDTO fellDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), FellDTO.class);

        assertEquals(expectedDms.get("degrees"), String.valueOf(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLatitude().getDegrees()));
        assertEquals(expectedDms.get("minutes"), String.valueOf(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLatitude().getMinutes()));
        assertEquals(expectedDms.get("seconds"), String.valueOf(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLatitude().getSeconds()));
        assertEquals(expectedDms.get("hemisphere"), String.valueOf(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLatitude().getHemisphere()));
        assertEquals(expectedDms.get("formatted"), String.valueOf(fellDTO.getLocation().getCoords().getDmsCoords().getConvertedLatitude().getFormatted()));
    }
}
