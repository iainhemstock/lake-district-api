package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.FellEntityRepository;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.ApiProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BackgroundStepDefs {

    @Autowired private ApiProperties apiProperties;
    @Autowired private FellEntityRepository fellEntityRepository;

    @Given("^Great Gable exists$")
    public void greatGableExists() {
        assertTrue(fellEntityRepository.existsById("NY211104"));
    }

    @Given("^Scafell Pike exists$")
    public void scafellPikeExists() {
        assertTrue(fellEntityRepository.existsById("NY215072"));
    }

    @Given("^Great Gable, Helvellyn and Scafell Pike exist$")
    public void fellsExist() {
        assertTrue(fellEntityRepository.existsById("NY211104"));
        assertTrue(fellEntityRepository.existsById("NY342151"));
        assertTrue(fellEntityRepository.existsById("NY215072"));
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

    @And("^the default page sort is (.*)$")
    public void theDefaultPageSortIsHeightDesc(final String expectedPageSort) {
        apiProperties.setPageSort(expectedPageSort);
        assertThat(apiProperties.getPageSort(), is(expectedPageSort));
    }
}
