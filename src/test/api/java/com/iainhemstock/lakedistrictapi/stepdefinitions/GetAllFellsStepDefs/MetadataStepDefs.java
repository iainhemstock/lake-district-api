package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.attributes.PagedCollectionAttributes;
import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class MetadataStepDefs {

    @Autowired private CommonTestState commonState;

    @And("^the body will contain the offset ([0-9]*)$")
    public void theBodyWillContainTheOffset(final String expectedOffset) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            PagedCollectionAttributes.OFFSET.value(), is(expectedOffset)));
    }

    @And("^the limit ([0-9]*)$")
    public void theLimit(final String expectedLimit) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            PagedCollectionAttributes.LIMIT.value(), is(expectedLimit)));
    }

    @And("^the total items ([0-9]*)$")
    public void theTotalItems(final String expectedTotalItems) throws Exception {
        commonState.getResult().andExpect(jsonPath(
            PagedCollectionAttributes.TOTAL_ITEMS.value(), is(expectedTotalItems)));
    }

}
