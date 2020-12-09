package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.config.testdata.TestDataLoaderForApiTests;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BackgroundStepDefs {

    @Value("${spring.data.web.pageable.default-page-size}")
    private int DEFAULT_PAGE_SIZE;

    @Autowired
    private TestDataLoaderForApiTests testDataLoader;

    @Given("^Great Gable exists$")
    public void fellExists() {
        testDataLoader.addGreatGable();
    }

    @Given("^Great Gable, Helvellyn and Scafell Pike exist$")
    public void fellsExist() {
        testDataLoader.addGreatGable();
        testDataLoader.addHelvellyn();
        testDataLoader.addScafellPike();
    }

    @And("^each page of results is configured to display a single result$")
    public void eachPageDisplaysSingleFellSummary() {
        String errorMsg = String.format("Default page size should be 1 but was [%d]", DEFAULT_PAGE_SIZE);
        assertThat(errorMsg, DEFAULT_PAGE_SIZE, is(1));
    }

}
