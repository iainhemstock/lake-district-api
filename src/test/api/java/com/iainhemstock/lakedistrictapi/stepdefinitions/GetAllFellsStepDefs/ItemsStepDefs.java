package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.ApiProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ItemsStepDefs {

    @Autowired private CommonTestState commonState;
    @Autowired private ApiProperties apiProperties;

    @Then("the body will contain the following fells")
    public void theBodyWillContainTheFollowingFells(final List<Map<String, String>> expectedFells) throws Exception {
        for (int i=0; i< expectedFells.size()-1; ++i) {
            commonState.getResult().andExpect(
                jsonPath(
                    String.format("$.items[?(@.name=='%s' && @.links.self.href=='%s')]",
                        expectedFells.get(i).get("name"), apiProperties.getBaseUrl() + expectedFells.get(i).get("href"))).exists());
        }
    }

    @And("^the number of fells contained in the page is ([0-9]*)$")
    public void theNumberOfFellsReturnedInThePageIs(final int expectedNumberOfFells) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.items", hasSize(expectedNumberOfFells)));
    }
}
