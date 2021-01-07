package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.config.testdata.TestDataLoaderForApiTests;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.ApiProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BackgroundStepDefs {

    @Autowired private TestDataLoaderForApiTests testDataLoader;
    @Autowired private ApiProperties apiProperties;

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

    @And("^the default page offset is ([0-9]+)$")
    public void theOffsetOfItemsReturnedInTheResponseIsByDefault(final int expectedPageOffset) {
        apiProperties.setPageOffset(expectedPageOffset);
        assertThat(apiProperties.getPageOffset(), is(expectedPageOffset));
    }

    @And("^the default page size is ([0-9]+)$")
    public void theLimitOfTheNumberOfItemsReturnedInTheResponseIsByDefault(final int expectedPageSize) {
        apiProperties.setPageSize(expectedPageSize);
        assertThat(apiProperties.getPageSize(), is(expectedPageSize));
    }
}
