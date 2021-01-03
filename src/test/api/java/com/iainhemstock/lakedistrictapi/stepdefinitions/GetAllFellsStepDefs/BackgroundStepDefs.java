package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.config.testdata.TestDataLoaderForApiTests;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BackgroundStepDefs {

    @Value("${api.pageable.default-page-offset}")
    private int DEFAULT_PAGE_OFFSET;

    @Value("${spring.data.web.pageable.default-page-size}")
    private int DEFAULT_PAGE_SIZE;

    @Autowired
    private TestDataLoaderForApiTests testDataLoader;

    @Given("^Great Gable exists$")
    public void fellExists() {
        testDataLoader.addGreatGable();
    }

    @Given("^Scafell Pike exists$")
    public void scafellPikeExists() {
        testDataLoader.addScafellPike();
    }

    @Given("^Great Gable, Helvellyn and Scafell Pike exist$")
    public void fellsExist() {
        testDataLoader.addGreatGable();
        testDataLoader.addHelvellyn();
        testDataLoader.addScafellPike();
    }

    @And("^the offset of items returned in the response is ([0-9]+) by default$")
    public void theOffsetOfItemsReturnedInTheResponseIsByDefault(final int defaultOffset) {
        assertThat(DEFAULT_PAGE_OFFSET, is(defaultOffset));
    }

    @And("^the limit of the number of items returned in the response is ([0-9]+) by default$")
    public void theLimitOfTheNumberOfItemsReturnedInTheResponseIsByDefault(final int defaultLimit) {
        assertThat(DEFAULT_PAGE_SIZE, is(defaultLimit));
    }
}
