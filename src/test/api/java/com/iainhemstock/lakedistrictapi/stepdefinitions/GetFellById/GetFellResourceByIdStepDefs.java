package com.iainhemstock.lakedistrictapi.stepdefinitions.GetFellById;

import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import com.iainhemstock.lakedistrictapi.config.TestApiConfiguration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ContextConfiguration(classes = TestApiConfiguration.class)
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GetFellResourceByIdStepDefs {

    @Autowired
    private CommonTestState commonState;

    @And("^the body will contain the fell name (.*)$")
    public void theResponseBodyWillContainTheFellName(final String expectedFellName) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.name", is(expectedFellName)));
    }

    @And("^the body will contain the region (.*)$")
    public void theResponseBodyWillContainTheRegion(final String expectedRegion) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.location.region", is(expectedRegion)));
    }

    @And("^the body will contain the latitude (.*)$")
    public void theResponseBodyWillContainTheLatitude(final String expectedLatitude) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.location.latitude", is(expectedLatitude)));
    }

    @And("^the body will contain the longitude (.*)$")
    public void theResponseBodyWillContainTheLongitude(final String expectedLongitude) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.location.longitude", is(expectedLongitude)));
    }

    @And("^the body will contain the os map reference ([A-Z]{2}[0-9]{6})$")
    public void theResponseBodyWillContainTheOsMapRef(final String expectedOsMapRef) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.location.os_map_ref", is(expectedOsMapRef)));
    }

    @And("^the body will contain the url to itself (.*)$")
    public void theResponseBodyWillContainTheUrl(final String expectedUrl) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.url", is(expectedUrl)));
    }

    @And("^the body will contain the parent peak url (.*)$")
    public void theResponseBodyWillContainTheParentPeakUrl(final String expectedParentPeakUrl) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.parent_peak", is(expectedParentPeakUrl)));
    }

    @And("^the body will contain the height in feet (.*)$")
    public void theResponseBodyWillContainTheFellHeightInFeet(final String expectedHeightInFeet) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.height.feet", is(expectedHeightInFeet)));
    }

    @And("^the body will contain the height in meters (.*)$")
    public void theResponseBodyWillContainTheFellHeightInMeters(final String expectedHeightInMeters) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.height.meters", is(expectedHeightInMeters)));
    }

    @And("^the body will contain the prominence in feet (.*)$")
    public void theResponseBodyWillContainTheFellProminenceInFeet(final String expectedProminenceInFeet) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.prominence.feet", is(expectedProminenceInFeet)));
    }

    @And("^the body will contain the prominence in meters (.*)$")
    public void theResponseBodyWillContainTheFellProminenceInMeters(final String expectedProminenceInMeters) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.prominence.meters", is(expectedProminenceInMeters)));
    }

    @And("^the body will contain the following classifications$")
    public void theResponseBodyWillContainTheFollowingClassifications(final List<String> expectedClassifications) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.classifications", containsInAnyOrder(expectedClassifications.toArray())));
    }

    @And("^the body will contain the following maps that this fell appears in$")
    public void theResponseBodyWillContainTheFollowingMaps(final List<String> expectedMaps) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.location.os_maps", containsInAnyOrder(expectedMaps.toArray())));
    }

    @And("^the body will contain the following dms coordinates equivalent to the latitude$")
    public void theResponseBodyWillContainTheFollowingNorthernDmsCoordinates(final Map<String, String> expectedDms) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.location.dms[0].degrees", is(expectedDms.get("degrees"))))
            .andExpect(jsonPath("$.location.dms[0].minutes", is(expectedDms.get("minutes"))))
            .andExpect(jsonPath("$.location.dms[0].seconds", is(expectedDms.get("seconds"))))
            .andExpect(jsonPath("$.location.dms[0].hemisphere", is(expectedDms.get("hemisphere"))));
    }

    @And("^the body will contain the following dms coordinates equivalent to the longitude$")
    public void theResponseBodyWillContainTheFollowingWesternDmsCoordinates(final Map<String, String> expectedDms) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.location.dms[1].degrees", is(expectedDms.get("degrees"))))
            .andExpect(jsonPath("$.location.dms[1].minutes", is(expectedDms.get("minutes"))))
            .andExpect(jsonPath("$.location.dms[1].seconds", is(expectedDms.get("seconds"))))
            .andExpect(jsonPath("$.location.dms[1].hemisphere", is(expectedDms.get("hemisphere"))));
    }

    @When("^sending unsupported (.*) request to (.*)")
    public void usingHttpMethodWithEndpoint(final String unsupportedHttpMethod,
                                            final String endpoint) throws Exception {
        commonState.setEndpointUnderTest(endpoint);

        if ("POST".equals(unsupportedHttpMethod))
            commonState.setResult(commonState.getMockMvc().perform(post(commonState.getEndpointUnderTest())));
        else if ("PUT".equals(unsupportedHttpMethod))
            commonState.setResult(commonState.getMockMvc().perform(put(commonState.getEndpointUnderTest())));
        else if ("PATCH".equals(unsupportedHttpMethod))
            commonState.setResult(commonState.getMockMvc().perform(patch(commonState.getEndpointUnderTest())));
        else if ("DELETE".equals(unsupportedHttpMethod))
            commonState.setResult(commonState.getMockMvc().perform(delete(commonState.getEndpointUnderTest())));
        else {
            fail(String.format("Unrecognised http method submitted to test [%s]", unsupportedHttpMethod));
        }
    }

    @And("^the body will contain the status code ([0-9]{3})$")
    public void theResponseBodyWillContainStatusCode(final int expectedStatusCode) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.status", is(String.valueOf(expectedStatusCode))));
    }

    @And("^the body will contain the message (.*)$")
    public void theResponseBodyWillContainMessage(final String expectedMessage) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.message", is(expectedMessage)));
    }

    @And("^the body will contain the path (.*)$")
    public void theResponseBodyWillContainThePath(final String expectedPath) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.path", is(expectedPath)));
    }

    @And("^the body will contain the timestamp (.*)$")
    public void theResponseBodyWillContainTheTimestamp(final String expectedTimestamp) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.timestamp", is(expectedTimestamp)));
    }
}
