package com.iainhemstock.lakedistrictapi.stepdefinitions.GetAllFellsStepDefs;

import com.iainhemstock.lakedistrictapi.common.CommonTestState;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static com.iainhemstock.lakedistrictapi.pageobjects.FellsCollectionPageObject.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PaginationStepDefs {

    @Autowired
    private CommonTestState commonState;

    @Then("^the body may contain the following (true|false), (true|false), (true|false), (true|false) and (true|false) links$")
    public void pageWillMayContainFirstPrevCurrentNextAndLastLinks(final boolean firstLinkIncluded,
                                                                   final boolean prevLinkIncluded,
                                                                   final boolean currentLinkIncluded,
                                                                   final boolean nextLinkIncluded,
                                                                   final boolean lastLinkIncluded) throws Exception {


        switch (String.valueOf(firstLinkIncluded)) {
            case "true" -> expectLinkInResponse(firstPageLink());
            case "false" -> doNotExpectLinkInResponse(firstPageLink());
        }

        switch (String.valueOf(prevLinkIncluded)) {
            case "true" -> expectLinkInResponse(prevPageLink());
            case "false" -> doNotExpectLinkInResponse(prevPageLink());
        }

        switch (String.valueOf(currentLinkIncluded)) {
            case "true" -> expectLinkInResponse(selfPageLink());
            case "false" -> doNotExpectLinkInResponse(selfPageLink());
        }

        switch (String.valueOf(nextLinkIncluded)) {
            case "true" -> expectLinkInResponse(nextPageLink());
            case "false" -> doNotExpectLinkInResponse(nextPageLink());
        }

        switch (String.valueOf(lastLinkIncluded)) {
            case "true" -> expectLinkInResponse(lastPageLink());
            case "false" -> doNotExpectLinkInResponse(lastPageLink());
        }
    }

    private void doNotExpectLinkInResponse(final String link) throws Exception {
        commonState.getResult().andExpect(jsonPath(link).doesNotExist());
    }

    private void expectLinkInResponse(final String link) throws Exception {
        commonState.getResult().andExpect(jsonPath(link).exists());
    }
}
