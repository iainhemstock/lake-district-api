package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import com.iainhemstock.lakedistrictapi.pageobjects.FellsCollectionPageObject;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CollectionStepDefs {

    @Autowired private CommonTestState commonState;

    @And("^the body will contain the ([0-9]*), ([0-9]*) and ([0-9]*)$")
    public void theBodyWillContainTheCurrentPageNumberTotalPagesAndTotalResults(
                                                        final String expectedCurrentPageNumber,
                                                        final String expectedTotalPages,
                                                        final String expectedTotalResults) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            FellsCollectionPageObject.currentPageNumber(), is(expectedCurrentPageNumber)));

        commonState.getResult().andExpect(jsonPath(
            FellsCollectionPageObject.totalPages(), is(expectedTotalPages)));

        commonState.getResult().andExpect(jsonPath(
            FellsCollectionPageObject.totalResources(), is(expectedTotalResults)));
    }

    @And("^the body will contain a simplified fell with name (.*)$")
    public void theBodyWillContainASimplifiedFell(final String fellName) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            FellsCollectionPageObject.fellNameAt(0), is(fellName)));
    }

    @And("^region (.*)$")
    public void andRegion(final String region) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            FellsCollectionPageObject.fellRegionAt(0), is(region)));
    }

    @And("^url (.*)$")
    public void andUrl(final String url) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            FellsCollectionPageObject.fellSelfLinkAt(0), is(url)));
    }

}
