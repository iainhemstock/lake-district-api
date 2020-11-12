package com.iainhemstock.lakedistrictapi.cucumber.glue;

import com.fasterxml.jackson.databind.JsonNode;
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
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FindFellByIdFeatureTest extends BaseFeatureTest {

    @Autowired private MockMvc mockMvc;

    private ResultActions response;
    private int requestedFellId;

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
        response = mockMvc.perform(get(this.getEndpointUnderTest(), requestedFellId));
    }

    @When("^using one of the following unsupported http methods with endpoint$")
    public void usingUnsupportedHttpMethod(List<String> requestTypes) throws Exception {
        for (String requestType : requestTypes) {
            if ("POST".equals(requestType)) response = mockMvc.perform(post(this.getEndpointUnderTest(), 0));
            else if ("PUT".equals(requestType)) response = mockMvc.perform(put(this.getEndpointUnderTest(), 0));
            else if ("PATCH".equals(requestType)) response = mockMvc.perform(patch(this.getEndpointUnderTest(), 0));
            else if ("DELETE".equals(requestType)) response = mockMvc.perform(delete(this.getEndpointUnderTest(), 0));
            else {
                fail(String.format("Unrecognised http method submitted to test [%s]", requestType));
            }
        }
    }

    @And("^the content type will be (.*)$")
    public void theContentTypeWillBe(final String contentType) throws Exception {
        response = response.andExpect(content().contentType(contentType));
    }

    @Then("^the response will return a ([0-9]{3}) status code$")
    public void theResponseWillContainStatusCode(final int statusCode) throws Exception {
        response.andExpect(status().is(statusCode));
    }

    @And("^the response body will conform to the schema in (.*)$")
    public void theResponseWillConformToTheJsonSchema(final String schemaFilename) throws IOException {
        JsonSchema schema = this.getJsonSchemaFromClasspath(schemaFilename);
        String responseBody = response.andReturn().getResponse().getContentAsString();
        JsonNode responseAsJsonNode = this.getJsonNodeFromStringContent(responseBody);

        Set<ValidationMessage> errors = schema.validate(responseAsJsonNode);
        assertEquals(0, errors.size());
    }
}
