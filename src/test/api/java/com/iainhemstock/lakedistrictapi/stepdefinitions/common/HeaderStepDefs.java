package com.iainhemstock.lakedistrictapi.stepdefinitions.common;

import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HeaderStepDefs {

    @Autowired
    private CommonTestState commonState;

    @Then("^the status code will be ([0-9]{3})$")
    public void theResponseWillContainStatusCode(final int expectedStatusCode) throws Exception {
        commonState.getResult().andExpect(
            status().is(expectedStatusCode));
    }

    @And("^the content type will be application/json$")
    public void theContentTypeWillBe() throws Exception {
        commonState.getResult().andExpect(
            content().contentType(MediaType.APPLICATION_JSON));
    }

    @Then("the headers will confirm only GET, HEAD AND OPTIONS methods are allowed")
    public void theResponseWillConfirmOnlyGETRequestsAreAllowed() throws Exception {
        commonState.getResult().andExpect(
            header().string("Allow", "GET,HEAD,OPTIONS"));
    }
}
