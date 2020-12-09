package com.iainhemstock.lakedistrictapi.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@Data
@Component
public class CommonTestState {
    @Autowired private MockMvc mockMvc;
    private ResultActions result;
    private String endpointUnderTest;

    public void setEndpointUnderTest(final String endpoint) {
        this.endpointUnderTest = endpoint;
    }

    public String getEndpointUnderTest() {
        return endpointUnderTest;
    }
}
