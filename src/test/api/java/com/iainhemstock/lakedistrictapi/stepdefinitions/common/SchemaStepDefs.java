package com.iainhemstock.lakedistrictapi.stepdefinitions.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.cucumber.java.en.And;
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
        JsonNode responseBodyJsonNode = getResponseBodyAsJsonNode();
        JsonSchema schema = getJsonSchemaFromClasspath(schemaFilename);
        validate(schema, responseBodyJsonNode);
    }

    private JsonNode getResponseBodyAsJsonNode() throws IOException {
        String responseBodyString = commonState.getResult().andReturn().getResponse().getContentAsString();
        return getJsonNodeFromStringContent(responseBodyString);
    }

    private void validate(final JsonSchema schema, final JsonNode responseBodyJsonNode) {
        Set<ValidationMessage> errors = schema.validate(responseBodyJsonNode);
        if (errors.size() > 0)
            fail("Does not conform to json schema: " + errors.toString());
    }

    private JsonSchema getJsonSchemaFromClasspath(String name) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        InputStream is = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream(name);
        if (is == null)
            fail(String.format("Schema file load failed: could not not find specified file [filename = %s]", name));
        return factory.getSchema(is);
    }

    private JsonNode getJsonNodeFromStringContent(String content) throws IOException {
        return objectMapper.readTree(content);
    }

}
