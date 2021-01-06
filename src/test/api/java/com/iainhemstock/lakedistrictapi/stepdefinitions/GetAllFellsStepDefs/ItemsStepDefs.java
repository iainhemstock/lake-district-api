package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.attributes.FellSimplifiedAttributes;
import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.ApiProperties;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ItemsStepDefs {

    @Autowired private CommonTestState commonState;
    @Autowired private ApiProperties apiProperties;

    @And("^the body will contain a fell name (.*)$")
    public void theBodyWillContainASimplifiedFell(final String fellName) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            FellSimplifiedAttributes.FELL_NAME.valueAt(0), is(fellName)));
    }

    @And("^fell self href (.*)$")
    public void andSelfHref(final String url) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            FellSimplifiedAttributes.FELL_SELF_HREF.valueAt(0),
            is(String.format("%s%s", apiProperties.getBaseUrl(), url))));
    }

    @And("^the number of fells contained in the page is ([0-9]*)$")
    public void theNumberOfFellsReturnedInThePageIs(final int expectedNumberOfFells) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.items", hasSize(expectedNumberOfFells)));
    }

}
