package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import com.iainhemstock.lakedistrictapi.pageobjects.FellSimplifiedAttributes;
import com.iainhemstock.lakedistrictapi.pageobjects.PagedCollectionAttributes;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ItemsStepDefs {

    @Autowired private CommonTestState commonState;

    @And("^the body will contain a simplified fell with name (.*)$")
    public void theBodyWillContainASimplifiedFell(final String fellName) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            FellSimplifiedAttributes.FELL_NAME.at(0), is(fellName)));
    }

    @And("^region (.*)$")
    public void andRegion(final String region) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            FellSimplifiedAttributes.FELL_REGION.at(0), is(region)));
    }

    @And("^self href (.*)$")
    public void andSelfHref(final String url) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            FellSimplifiedAttributes.FELL_SELF_HREF.at(0), is(url)));
    }

}
