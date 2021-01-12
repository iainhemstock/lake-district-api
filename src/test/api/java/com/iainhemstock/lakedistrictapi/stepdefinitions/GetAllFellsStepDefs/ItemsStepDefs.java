package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.ApiProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ItemsStepDefs {

    @Autowired private CommonTestState commonState;
    @Autowired private ApiProperties apiProperties;

    @Then("the body will contain the following fells in order")
    public void theBodyWillContainTheFollowingFells(final List<Map<String, String>> expectedFells) throws Exception {
        System.out.println(expectedFells);
        for (int i=0; i < expectedFells.size(); ++i) {
            String expectedName = expectedFells.get(i).get("name");
            String expectedHeightMeters = expectedFells.get(i).get("height meters");
            String expectedHeightFeet = expectedFells.get(i).get("height feet");
            String expectedHref = String.format("%s%s", apiProperties.getBaseUrl(), expectedFells.get(i).get("href"));
            commonState.getResult().andExpect(jsonPath(String.format("$.items[%d].name", i), is(expectedName)));
            commonState.getResult().andExpect(jsonPath(String.format("$.items[%d].height.meters", i), is(expectedHeightMeters)));
            commonState.getResult().andExpect(jsonPath(String.format("$.items[%d].height.feet", i), is(expectedHeightFeet)));
            commonState.getResult().andExpect(jsonPath(String.format("$.items[%d].links.self.href", i), is(expectedHref)));
        }
    }

    @And("^the number of fells contained in the page is ([0-9]*)$")
    public void theNumberOfFellsReturnedInThePageIs(final int expectedNumberOfFells) throws Exception {
        commonState.getResult().andExpect(jsonPath("$.items", hasSize(expectedNumberOfFells)));
    }
}
