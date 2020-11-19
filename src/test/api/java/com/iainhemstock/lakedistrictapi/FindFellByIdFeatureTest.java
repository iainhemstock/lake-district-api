package com.iainhemstock.lakedistrictapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iainhemstock.lakedistrictapi.config.TestApiConfiguration;
import com.iainhemstock.lakedistrictapi.dtos.DmsDto;
import com.iainhemstock.lakedistrictapi.dtos.ErrorDto;
import com.iainhemstock.lakedistrictapi.dtos.FellDto;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ContextConfiguration(classes = TestApiConfiguration.class)
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FindFellByIdFeatureTest extends BaseFeatureTest {

    private static final int LATITUDE = 0;
    private static final int LONGITUDE = 1;

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    private MvcResult mvcResult;
    private FellDto fellDto;

    @Given("^an endpoint (.*)$")
    public void anEndpoint(final String endpoint) {
        this.setEndpointUnderTest(endpoint);
    }

    @When("^requesting a fell with id ([0-9]*)$")
    public void whenRequestingFellWithThatsId(final int fellId) throws Exception {
        mvcResult = mockMvc.perform(get(this.getEndpointUnderTest(), fellId)).andReturn();
        fellDto = mapSuccessfulResponseToDto();
    }

    @And("^the response content type will be (.*)$")
    public void theContentTypeWillBe(final String expectedContentType) throws UnsupportedEncodingException {
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertThat(mvcResult.getResponse().getContentType(),
            is(equalTo(expectedContentType)));
    }

    @Then("^the response status code will be ([0-9]{3})$")
    public void theResponseWillContainStatusCode(final int expectedStatusCode) {
        assertThat(mvcResult.getResponse().getStatus(),
            is(equalTo(expectedStatusCode)));
    }

    @Then("^the response headers will confirm only GET requests are allowed on this endpoint$")
    public void theResponseHeadersWillContainAllowHeaderSetToGet() {
        assertThat(mvcResult.getResponse().getHeaders("Allow"),
            is(equalTo(List.of("GET"))));
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

    @And("^the response body will contain the fell name (.*)$")
    public void theReponseBodyWillContainTheFellName(final String fellName) throws IOException {
        assertThat(fellDto.getName(),
            is(equalTo(fellName)));
    }

    @And("^the response body will contain the region (.*)$")
    public void theResponseBodyWillContainTheRegion(final String region) throws IOException {
        assertThat(fellDto.getLocation().getRegion(),
            is(equalTo(region)));
    }

    @And("^the response body will contain the latitude (.*)$")
    public void theResponseBodyWillContainTheLatitude(final String latitude) throws IOException {
        assertThat(fellDto.getLocation().getDecimalCoords().getLatitude(),
            is(equalTo(latitude)));
    }

    @And("^the response body will contain the longitude (.*)$")
    public void theResponseBodyWillContainTheLongitude(final String longitude) throws IOException {
        assertThat(fellDto.getLocation().getDecimalCoords().getLongitude(),
            is(equalTo(longitude)));
    }

    @And("^the response body will contain the os map reference (.*)$")
    public void theResponseBodyWillContainTheOsMapRef(final String osMapRef) throws IOException {
        assertThat(fellDto.getLocation().getOsMapRef(),
            is(equalTo(osMapRef)));
    }

    @And("^the response body will contain the url (.*)$")
    public void theResponseBodyWillContainTheUrl(final String url) throws IOException {
        assertThat(fellDto.getUrl(),
            is(equalTo(url)));
    }

    @And("^the response body will contain the parent peak url (.*)$")
    public void theResponseBodyWillContainTheParentPeakUrl(final String parentPeakUrl) throws IOException {
        assertThat(fellDto.getParentPeakUrl(),
            is(equalTo(parentPeakUrl)));
    }

    @And("^the response body will contain the height in feet (.*)$")
    public void theResponseBodyWillContainTheFellHeightInFeet(final String heightInFeet) throws IOException {
        assertThat(fellDto.getHeight().getFeet(),
            is(equalTo(heightInFeet)));
    }

    @And("^the response body will contain the height in meters (.*)$")
    public void theResponseBodyWillContainTheFellHeightInMeters(final String heightInMeters) throws IOException {
        assertThat(fellDto.getHeight().getMeters(),
            is(equalTo(heightInMeters)));
    }

    @And("^the response body will contain the prominence in feet (.*)$")
    public void theResponseBodyWillContainTheFellProminenceInFeet(final String prominenceInFeet) throws IOException {
        assertThat(fellDto.getProminence().getFeet(),
            is(equalTo(prominenceInFeet)));
    }

    @And("^the response body will contain the prominence in meters (.*)$")
    public void theResponseBodyWillContainTheFellProminenceInMeters(final String prominenceInMeters) throws IOException {
        assertThat(fellDto.getProminence().getMeters(),
            is(equalTo(prominenceInMeters)));
    }

    @And("^the response body will contain the following classification urls$")
    public void theResponseBodyWillContainTheFollwingClassifications(final List<String> expectedClassifications) throws IOException {
        assertTrue(fellDto.getClassifications().containsAll(expectedClassifications) &&
                expectedClassifications.containsAll(fellDto.getClassifications()));
    }

    @And("^the response body will contain the following maps that this fell appears in$")
    public void theResponseBodyWillContainTheFollowingMaps(final List<String> expectedMaps) throws IOException {
        assertTrue(fellDto.getLocation().getOsMaps().containsAll(expectedMaps) &&
            expectedMaps.containsAll(fellDto.getLocation().getOsMaps()));
    }

    @And("^the response body will contain the following dms coordinates equivalent to the latitude$")
    public void theResponseBodyWillContainTheFollowingNorthernDmsCoordinates(final Map<String, String> expectedDms) throws IOException {
        assertThatActualDmsMatchesExpectedDms(expectedDms, LATITUDE);
    }

    @And("^the response body will contain the following dms coordinates equivalent to the longitude$")
    public void theResponseBodyWillContainTheFollowingWesternDmsCoordinates(final Map<String, String> expectedDms) throws IOException {
        assertThatActualDmsMatchesExpectedDms(expectedDms, LONGITUDE);
    }

    private void assertThatActualDmsMatchesExpectedDms(final Map<String, String> expectedDms, int latOrLong) {
        DmsDto dmsDto = fellDto.getLocation().getDmsCoords().get(latOrLong);

        assertThat(dmsDto.getDegrees(),
            is(equalTo(expectedDms.get("degrees"))));
        assertThat(dmsDto.getMinutes(),
            is(equalTo(expectedDms.get("minutes"))));
        assertThat(dmsDto.getSeconds(),
            is(equalTo(expectedDms.get("seconds"))));
        assertThat(dmsDto.getHemisphere(),
            is(equalTo(expectedDms.get("hemisphere"))));
        assertThat(dmsDto.getFormatted(),
            is(equalTo(expectedDms.get("formatted"))));
    }

    @When("^sending unsupported (.*) request with fell id ([0-9]*) to endpoint$")
    public void usingHttpMethodWithEndpoint(final String unsupportedHttpMethod, final int fellId) throws Exception {
        if ("POST".equals(unsupportedHttpMethod)) mvcResult = mockMvc.perform(post(this.getEndpointUnderTest(), fellId)).andReturn();
        else if ("PUT".equals(unsupportedHttpMethod)) mvcResult = mockMvc.perform(put(this.getEndpointUnderTest(), fellId)).andReturn();
        else if ("PATCH".equals(unsupportedHttpMethod)) mvcResult = mockMvc.perform(patch(this.getEndpointUnderTest(), fellId)).andReturn();
        else if ("DELETE".equals(unsupportedHttpMethod)) mvcResult = mockMvc.perform(delete(this.getEndpointUnderTest(), fellId)).andReturn();
        else {
            fail(String.format("Unrecognised http method submitted to test [%s]", unsupportedHttpMethod));
        }
    }

    @And("^the response body will contain the status code ([0-9]{3})$")
    public void theResponseBodyWillContainStatusCode(final int statusCode) throws IOException {
        ErrorDto errorDto = mapUnsuccessfulResposeToDto();
        assertThat(errorDto.getStatus(),
            is(equalTo(String.valueOf(statusCode))));
    }

    @And("^the response body will contain the message (.*)$")
    public void theResponseBodyWillContainMessage(final String message) throws IOException {
        ErrorDto errorDto = mapUnsuccessfulResposeToDto();
        assertThat(errorDto.getMessage(),
            is(equalTo(message)));
    }

    @And("^the response body will contain the path (.*)$")
    public void theResponseBodyWillContainThePath(final String path) throws IOException {
        ErrorDto errorDto = mapUnsuccessfulResposeToDto();
        assertThat(errorDto.getPath(),
            is(equalTo(path)));
    }

    @And("^the response body will contain the timestamp (.*)$")
    public void theResponseBodyWillContainTheTimestamp(final String timestamp) throws IOException {
        ErrorDto errorDto = mapUnsuccessfulResposeToDto();
        assertThat(errorDto.getTimestamp(),
            is(equalTo(timestamp)));
    }

    private FellDto mapSuccessfulResponseToDto() throws IOException {
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), FellDto.class);
    }

    private ErrorDto mapUnsuccessfulResposeToDto() throws IOException {
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorDto.class);
    }
}
