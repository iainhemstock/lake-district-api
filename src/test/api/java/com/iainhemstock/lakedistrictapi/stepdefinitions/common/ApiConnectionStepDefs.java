package com.iainhemstock.lakedistrictapi.stepdefinitions.common;

import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.ApiProperties;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class ApiConnectionStepDefs {

    @Autowired private CommonTestState commonState;
    @Autowired private ApiProperties apiProperties;

    @When("^making \\ban?\\b (GET|PUT|POST|PATCH|DELETE|OPTIONS) request to (/fells.*)$")
    public void makingRequestToEndpoint(final String requestMethod, String pageUrl) throws Exception {
        commonState.setEndpointUnderTest(String.format("%s%s", apiProperties.getBaseUrl(), pageUrl));
        commonState.setResult(switch (requestMethod) {
            case "GET" -> commonState.getMockMvc().perform(get(commonState.getEndpointUnderTest()));
            case "PUT" -> commonState.getMockMvc().perform(put(commonState.getEndpointUnderTest()));
            case "POST" -> commonState.getMockMvc().perform(post(commonState.getEndpointUnderTest()));
            case "PATCH" -> commonState.getMockMvc().perform(patch(commonState.getEndpointUnderTest()));
            case "DELETE" -> commonState.getMockMvc().perform(delete(commonState.getEndpointUnderTest()));
            case "OPTIONS" -> commonState.getMockMvc().perform(options(commonState.getEndpointUnderTest()));
            default -> fail(String.format("Unrecognised http method submitted to test [%s]", requestMethod));
        });
        System.out.println(commonState.getResult().andReturn().getResponse().getContentAsString());
    }

}
