package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.attributes.FellSimplifiedAttributes;
import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ItemsStepDefs {

    @Autowired private CommonTestState commonState;

    @And("^the body will contain a simplified fell with name (.*)$")
    public void theBodyWillContainASimplifiedFell(final String fellName) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            FellSimplifiedAttributes.FELL_NAME.valueAt(0), is(fellName)));
    }

    @And("^self href (.*)$")
    public void andSelfHref(final String url) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            FellSimplifiedAttributes.FELL_SELF_HREF.valueAt(0), is(url)));
    }

}
