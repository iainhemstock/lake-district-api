package com.iainhemstock.lakedistrictapi.stepdefinitions.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static org.junit.Assert.fail;

public class SchemaStepDefs {

    @Autowired
    CommonTestState commonState;

    @Autowired
    protected ObjectMapper objectMapper;

    @And("^the body will conform to the schema in (.*)$")
    public void theResponseWillConformToTheJsonSchema(final String schemaFilename) throws IOException {
        JsonNode responseBodyAsJsonNode = getResponseBodyAsJsonNode();
        JsonSchema schema = loadJsonSchemaFromClasspath(schemaFilename);
        validate(schema, responseBodyAsJsonNode);
    }

    @Then("^the pagination attributes will conform to the schema in (.*)$")
    public void thePaginationAttributesWillConformToTheJsonSchema(final String schemaFilename) throws IOException {
        this.theResponseWillConformToTheJsonSchema(schemaFilename);
    }

    @And("^the items attribute will conform to the schema in (.*)$")
    public void theItemsAttributeWillConformToTheSchemaIn(final String schemaFilename) throws Exception {
        JsonNode responseBodyJsonNode = getResponseBodyAsJsonNode();
        JsonSchema schema = loadJsonSchemaFromClasspath(schemaFilename);
        validate(schema, responseBodyJsonNode.findValue("items"));
    }

    private JsonNode getResponseBodyAsJsonNode() throws IOException {
        String responseBodyString = commonState.getResult().andReturn().getResponse().getContentAsString();
        return getJsonNodeFromStringContent(responseBodyString);
    }

    private void validate(final JsonSchema schema, final JsonNode jsonNode) {
        Set<ValidationMessage> errors = schema.validate(jsonNode);
        if (errors.size() > 0)
            fail("Does not conform to json schema: " + errors.toString());
    }

    private JsonSchema loadJsonSchemaFromClasspath(String name) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        InputStream is = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream(name);
        if (is == null)
            fail(String.format("Schema file load failed: could not load specified file [filename = %s]", name));
        return factory.getSchema(is);
    }

    private JsonNode getJsonNodeFromStringContent(String json) throws IOException {
        return objectMapper.readTree(json);
    }

}
