package com.iainhemstock.lakedistrictapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;

public class BaseFeatureTest {

    @Autowired protected ObjectMapper objectMapper;
    private String endpointUnderTest;

    public void setEndpointUnderTest(final String endpoint) {
        this.endpointUnderTest = endpoint;
    }

    public String getEndpointUnderTest() {
        return endpointUnderTest;
    }

    protected JsonSchema getJsonSchemaFromClasspath(String name) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        InputStream is = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream(name);
        return factory.getSchema(is);
    }

    protected JsonNode getJsonNodeFromStringContent(String content) throws IOException {
        return objectMapper.readTree(content);
    }

}
